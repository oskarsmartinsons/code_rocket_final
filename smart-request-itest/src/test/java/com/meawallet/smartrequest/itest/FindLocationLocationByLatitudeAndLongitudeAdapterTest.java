package com.meawallet.smartrequest.itest;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static com.github.springtestdbunit.annotation.DatabaseOperation.DELETE_ALL;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@DatabaseTearDown(value = "classpath:dbunit/empty_dataset.xml", type = DELETE_ALL)
public class FindLocationLocationByLatitudeAndLongitudeAdapterTest extends BaseIntegrationTest {
    private static WireMockServer wireMockServer;

    @BeforeAll
    public static void setup() {
        wireMockServer = new WireMockServer(options().port(20000));
        wireMockServer.start();
    }
    @Test
    @DatabaseSetup(value = "classpath:dbunit/temperature.xml")
    @ExpectedDatabase(value = "classpath:dbunit/temperature.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    void shouldFindLocationByLatitudeAndLongitude() throws Exception {
        var weatherApiResponse = readJson("weatherApiResponseSuccess.json");

        stubExternalApiResponse(weatherApiResponse, 200);

        mvc.perform(MockMvcRequestBuilders.get("/weather?lat=11.11&lon=33.33"))
                .andExpect(status().isOk());
    }

    private static void stubExternalApiResponse(String weatherApiResponse, int status) {
        wireMockServer.stubFor(get(urlEqualTo("/random")).willReturn(
                aResponse()
                        .withStatus(status)
                        .withBody(weatherApiResponse)
                        .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")
        ));
    }
}
