package com.olio.aglio.market.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MarketDailyApiResponse {
    @JsonProperty("Grid_20141119000000000012_1")
    private Root root;
    private Result result;
}
