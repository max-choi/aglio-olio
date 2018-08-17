package com.olio.aglio.market.service;

import com.olio.aglio.market.MarketApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@Slf4j
@ActiveProfiles("cloud")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MarketApplication.class)
public class MigrationServiceTest {

    @Autowired
    private MigrationService migrationService;

    @Test
    public void 마이그레이션() {
        LocalDate from = LocalDate.of(2018, 4, 26);
        while (true) {
            log.info("localDate = {}", from);
            int maxScale = migrationService.migrationDaily(from);
            log.info("maxScale = {}", maxScale);
            if (from.isEqual(LocalDate.of(2017, 1, 1))) {
                log.info("done!!");
                break;
            }
            from = from.minusDays(1);
        }
    }

}
