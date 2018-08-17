package com.olio.aglio.market.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AuctionStat {
    private LocalDate aucngDe; // 경락일
    private int seqNo; // 순번
    private String pblmngWhsalMrktNm; // 공영 도매시장명
    private String pblmngWhsalMrktCd; // 공영 도매시장 코드
    private String cprNm; // 법인명
    private String cprCd; // 법인코드
    private String prdlstNm; // 품목명
    private String prdlstCd; // 품목코드
    private String spciesNm; // 품종명
    private String spciesCd; // 품종코드
    private String grad; // 등급
    private String gradCd; // 등급코드
    private long delngbundleQy; // 거래단량
    private String stndrd; // 규격
    private String stndrdCd; // 규격코드
    private int delngQy; // 거래량
    private long mummAmt; // 최소가
    private long avrgAmt; // 평균가
    private long mxmmAmt; // 최대가
    private int aucCo; // 경매건수
    private LocalDateTime createdAt;
}
