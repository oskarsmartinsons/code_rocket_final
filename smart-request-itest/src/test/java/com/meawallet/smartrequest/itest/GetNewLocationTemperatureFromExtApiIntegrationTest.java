package com.meawallet.smartrequest.itest;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static com.github.springtestdbunit.annotation.DatabaseOperation.DELETE_ALL;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DatabaseTearDown(value = "classpath:dbunit/empty_dataset.xml", type = DELETE_ALL)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GetNewLocationTemperatureFromExtApiIntegrationTest extends BaseIntegrationTest {
    @MockBean
    private Clock clock;

    @BeforeEach
    void setupClock() {
        Instant customInstant = Instant.parse("2023-03-27T17:00:00Z");
        given(clock.instant()).willReturn(customInstant);
        given(clock.getZone()).willReturn(ZoneId.of("UTC"));
    }

    @Test
    @DatabaseSetup(value = "classpath:dbunit/empty_dataset.xml")
    @ExpectedDatabase(value = "classpath:dbunit/temperatureFromExtApiForNewLocationSuccess_ExpectedState.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    void shouldReturnTemperatureFromExtApiForNewLocation() throws Exception {
        var weatherApiResponse = readJson("externalApiResponseSuccess.json");
        stubExternalApiResponse(weatherApiResponse, 200);

        mvc.perform(MockMvcRequestBuilders.get("/weather?lat=11.11&lon=33.33"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.temperature").value("32.5"));
    }

    private static void stubExternalApiResponse(String weatherApiResponse, int status) {
        wireMockServer.stubFor(get(urlEqualTo("/external")).willReturn(
                aResponse()
                        .withStatus(status)
                        .withBody(weatherApiResponse)
                        .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")
        ));
    }
}
