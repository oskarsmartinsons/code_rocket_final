package com.meawallet.smartrequest.itest;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GetLocationTemperatureFromCacheIntegrationTest extends  BaseIntegrationTest{
    @Autowired
    private DataSource dataSource;

    @BeforeEach
    public void setUp()  {
        var currentHour = LocalDateTime.now()
                .truncatedTo(ChronoUnit.MINUTES)
                .withMinute(0)
                .withSecond(0);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("UPDATE PUBLIC.temperatures SET temperature_at = ?", currentHour );
    }

    @Test
    @DatabaseSetup(value = "classpath:dbunit/locationWithValidTemperatureSuccess.xml")
    void shouldReturnTemperatureFromExtApi() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/weather?lat=11.11&lon=33.33"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.temperature").value("25.25"));
    }
}

