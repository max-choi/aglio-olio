package com.olio.aglio.market.repository;

import com.olio.aglio.market.model.AuctionCode;
import com.olio.aglio.market.model.AuctionStat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface AuctionItemRepository {
    List<AuctionStat> getAuctionStats();

    LocalDate getFirstAuctionStatDate();

    int insertAuctionStats(List<AuctionStat> auctionStats);

    int insertAuctionCodes(List<AuctionCode> auctionCodes);

    int deleteActionStatsByDate(@Param("aucngDe") LocalDate aucngDe);
}
