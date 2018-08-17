package com.olio.aglio.market.model.external;

import lombok.Data;

import java.util.List;

@Data
public class Root {
    private long totalCnt;
    private int startRow;
    private int endRow;
    private Result result;
    private List<RawData> row;
}
