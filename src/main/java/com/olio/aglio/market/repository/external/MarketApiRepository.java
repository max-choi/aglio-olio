package com.olio.aglio.market.repository.external;

import com.olio.aglio.market.model.external.MarketDailyApiResponse;
import com.olio.aglio.market.model.external.MarketHourlyApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Repository
public class MarketApiRepository {

    @Value("${api.key}")
    private String apiKey;
    @Value("${api.type}")
    private String type;
    private static final String API_URL = "http://211.237.50.150:7080/openapi/%s/%s";
    private static final String DAILY_AGGREGATION_URL = "/Grid_20141119000000000012_1/%s/%s?AUCNG_DE=%s";
    private static final String HOURLY_AGGREGATION_URL = "/Grid_20150401000000000216_1//%s/%s?AUCNG_DE=%s";

    @Autowired
    private RestTemplate restTemplate;

    public MarketDailyApiResponse getDailyAggregationData(LocalDate date, int from, int to) {
        String url = String.format(API_URL + DAILY_AGGREGATION_URL, apiKey, type, from, to, date.format(DateTimeFormatter.BASIC_ISO_DATE));
        RequestEntity<MarketDailyApiResponse> entity = new RequestEntity<>(HttpMethod.GET, URI.create(url));
        ResponseEntity<MarketDailyApiResponse> response = restTemplate.exchange(entity, MarketDailyApiResponse.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
        return response.getBody();
    }

    public MarketHourlyApiResponse getHourlyAggregationData(LocalDate date, int from, int to) {
        String url = String.format(API_URL + HOURLY_AGGREGATION_URL, apiKey, type, from, to, date.format(DateTimeFormatter.BASIC_ISO_DATE));
        RequestEntity<MarketDailyApiResponse> entity = new RequestEntity<>(HttpMethod.GET, URI.create(url));
        ResponseEntity<MarketHourlyApiResponse> response = restTemplate.exchange(entity, MarketHourlyApiResponse.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
        return response.getBody();
    }
}

