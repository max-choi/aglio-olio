package com.olio.aglio.market.model.external;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.thymeleaf.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RawData {
    @JsonProperty("ROW_NUM")
    private int seqNo; // 출력순서
    @JsonProperty("AUCNG_DE")
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate aucngDe; // 경락일
    @JsonProperty("PBLMNG_WHSAL_MRKT_NM")
    private String pblmngWhsalMrktNm; // 공영 도매시장명
    @JsonProperty("PBLMNG_WHSAL_MRKT_CD")
    private String pblmngWhsalMrktCd; // 공영 도매시장 코드
    @JsonProperty("CPR_NM")
    private String cprNm; // 법인명
    @JsonProperty("CPR_CD")
    private String cprCd; // 법인코드
    @JsonProperty("PRDLST_NM")
    private String prdlstNm; // 품목명
    @JsonProperty("PRDLST_CD")
    private String prdlstCd; // 품목코드
    @JsonProperty("SPCIES_NM")
    private String spciesNm; // 품종명
    @JsonProperty("SPCIES_CD")
    private String spciesCd; // 품종코드
    @JsonProperty("GRAD")
    private String grad; // 등급
    @JsonProperty("GRAD_CD")
    private String gradCd; // 등급코드
    @JsonProperty("DELNGBUNDLE_QY")
    private BigDecimal delngbundleQy; // 거래단량
    @JsonProperty("STNDRD")
    private String stndrd; // 규격
    @JsonProperty("STNDRD_CD")
    private String stndrdCd; // 규격코드
    @JsonProperty("DELNG_QY")
    private int delngQy; // 거래량
    @JsonProperty("MUMM_AMT")
    private long mummAmt; // 최소가
    @JsonProperty("AVRG_AMT")
    private long avrgAmt; // 평균가
    @JsonProperty("MXMM_AMT")
    private long mxmmAmt; // 최대가
    @JsonProperty("AUC_CO")
    private int aucCo; // 경매건수

    public String getPblmngWhsalMrktNm() {
        return StringUtils.trim(pblmngWhsalMrktNm);
    }

    public String getPblmngWhsalMrktCd() {
        return StringUtils.trim(pblmngWhsalMrktCd);
    }

    public String getCprNm() {
        return StringUtils.trim(cprNm);
    }

    public String getCprCd() {
        return StringUtils.trim(cprCd);
    }

    public String getPrdlstNm() {
        return StringUtils.trim(prdlstNm);
    }

    public String getPrdlstCd() {
        return StringUtils.trim(prdlstCd);
    }

    public String getSpciesNm() {
        return StringUtils.trim(spciesNm);
    }

    public String getSpciesCd() {
        return StringUtils.trim(spciesCd);
    }

    public String getGrad() {
        return StringUtils.trim(grad);
    }

    public String getGradCd() {
        return StringUtils.trim(gradCd);
    }

    public String getStndrd() {
        return StringUtils.trim(stndrd);
    }

    public String getStndrdCd() {
        return StringUtils.trim(stndrdCd);
    }
}
