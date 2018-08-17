package com.olio.aglio.market.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.olio.aglio.market.model.AuctionCode;
import com.olio.aglio.market.model.AuctionStat;
import com.olio.aglio.market.model.external.MarketDailyApiResponse;
import com.olio.aglio.market.repository.AuctionItemRepository;
import com.olio.aglio.market.repository.external.MarketApiRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MigrationService {

    private static final int FETCH_SIZE = 1000;

    @Autowired
    private MarketApiRepository marketApiRepository;

    @Autowired
    private AuctionItemRepository auctionItemRepository;

    public int migrationDaily(LocalDate date) {

        ModelMapper modelMapper = new ModelMapper();

        final AtomicInteger maxScale = new AtomicInteger();

        int from = 1;
        int to = FETCH_SIZE;
        while (true) {
            MarketDailyApiResponse response = marketApiRepository.getDailyAggregationData(date, from, to);
            if (response == null) {
                break;
            }
            if (response.getResult() != null) {
                log.error("error = {}", response.getResult());
                break;
            }
            if (response.getRoot() == null) {
                break;
            }
            if (!StringUtils.equals(response.getRoot().getResult().getCode(), "INFO-000")) {
                log.error("error = {}", response.getRoot().getResult());
                break;
            }
            if (CollectionUtils.isEmpty(response.getRoot().getRow())) {
                break;
            }

            Map<String, String> mrktCodeMap = Maps.newLinkedHashMap();
            Map<String, String> cprCodeMap = Maps.newLinkedHashMap();
            Map<String, String> prdlstCodeMap = Maps.newLinkedHashMap();
            Map<String, String> spciesCodeMap = Maps.newLinkedHashMap();
            Map<String, String> gradCodeMap = Maps.newLinkedHashMap();
            Map<String, String> stndrdCodeMap = Maps.newLinkedHashMap();

            LocalDateTime now = LocalDateTime.now();
            List<AuctionStat> actionItems = response.getRoot().getRow().stream()
                    .map(rawData -> modelMapper.map(rawData, AuctionStat.class))
                    .peek(auctionStat -> {
                        if (auctionStat.getDelngbundleQy().scale() > maxScale.intValue()) {
                            maxScale.set(auctionStat.getDelngbundleQy().scale());
                        }
                        mrktCodeMap.put(auctionStat.getPblmngWhsalMrktCd(), auctionStat.getPblmngWhsalMrktNm());
                        cprCodeMap.put(auctionStat.getCprCd(), auctionStat.getCprNm());
                        prdlstCodeMap.put(auctionStat.getPrdlstCd(), auctionStat.getPrdlstNm());
                        spciesCodeMap.put(auctionStat.getSpciesCd(), auctionStat.getSpciesNm());
                        gradCodeMap.put(auctionStat.getGradCd(), auctionStat.getGrad());
                        stndrdCodeMap.put(auctionStat.getStndrdCd(), auctionStat.getStndrd());
                        auctionStat.setCreatedAt(now);
                    }).collect(Collectors.toList());
            auctionItemRepository.insertAuctionStats(actionItems);

            List<AuctionCode> actionCodes = Lists.newLinkedList();
            actionCodes.addAll(convertToAuctionCodes(mrktCodeMap, 1));
            actionCodes.addAll(convertToAuctionCodes(cprCodeMap, 2));
            actionCodes.addAll(convertToAuctionCodes(prdlstCodeMap, 3));
            actionCodes.addAll(convertToAuctionCodes(spciesCodeMap, 4));
            actionCodes.addAll(convertToAuctionCodes(gradCodeMap, 5));
            actionCodes.addAll(convertToAuctionCodes(stndrdCodeMap, 6));
            auctionItemRepository.insertAuctionCodes(actionCodes);

            if (actionItems.size() < FETCH_SIZE) {
                break;
            }
            from += FETCH_SIZE;
            to += FETCH_SIZE;
        }
        return maxScale.intValue();
    }

    private List<AuctionCode> convertToAuctionCodes(Map<String, String> codeMap, int type) {
        return codeMap.entrySet().stream().map(e -> {
            AuctionCode code = new AuctionCode();
            code.setCode(e.getKey());
            code.setType(type);
            code.setName(e.getValue());
            return code;
        }).collect(Collectors.toList());
    }
}
