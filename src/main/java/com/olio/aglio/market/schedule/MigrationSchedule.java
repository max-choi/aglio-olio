package com.olio.aglio.market.schedule;

import com.olio.aglio.market.repository.AuctionItemRepository;
import com.olio.aglio.market.service.MigrationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class MigrationSchedule {

    private static final LocalDate FIRST_DATE = LocalDate.of(2017, 1, 1);

    @Value("${spring.profiles:local}")
    private String activeProfile;
    @Autowired
    private AuctionItemRepository auctionItemRepository;
    @Autowired
    private MigrationService migrationService;

    // @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void migrate() {
        log.info("activeProfile = {}", activeProfile);
        if (StringUtils.equals(activeProfile, "cloud")) {
            LocalDate from = auctionItemRepository.getFirstAuctionStatDate();
            auctionItemRepository.deleteActionStatsByDate(from);
            while (from.isAfter(FIRST_DATE) || from.isEqual(FIRST_DATE)) {
                log.info("localDate = {}", from);
                int maxScale = migrationService.migrationDaily(from);
                log.info("maxScale = {}", maxScale);
                from = from.minusDays(1);
            }
            log.info("done!!");
        }
    }
}
