package com.olio.aglio.market.service;

import com.olio.aglio.market.MarketApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MarketApplication.class)
public class MigrationServiceTest {

    @Autowired
    private MigrationService migrationService;

    @Test
    public void 마이그레이션() {
        LocalDate today = LocalDate.now().minusDays(1);
        migrationService.migrationDaily(today);
    }


}
