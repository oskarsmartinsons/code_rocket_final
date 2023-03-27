package com.meawallet.smartrequest.itest;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static com.github.springtestdbunit.annotation.DatabaseOperation.DELETE_ALL;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DatabaseTearDown(value = "classpath:dbunit/empty_dataset.xml", type = DELETE_ALL)
public class GetTemperatureNegativePathIntegrationTest extends BaseIntegrationTest{

    @Test
    @DatabaseSetup(value = "classpath:dbunit/empty_dataset.xml")
    @ExpectedDatabase(value = "classpath:dbunit/empty_dataset.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    void shouldNotCreateLocationAndTemperatureIfExternalApiReturnError() throws Exception {
        stubExternalApiResponse("", 400);

        var response = readJson("getTemperatureResponse500Error_extApi400Error.json");

        mvc.perform(MockMvcRequestBuilders.get("/weather?lat=11.11&lon=33.33"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().json(response));
    }

    @Test
    @ExpectedDatabase(value = "classpath:dbunit/empty_dataset.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    void shouldNotCreateLocationAndTemperatureIfIncorrectParameters() throws Exception {
        var response = readJson("getTemperatureResponse400Error_LongitudeConstrainViolation.json");

        mvc.perform(MockMvcRequestBuilders.get("/weather?lat=11.11&lon=333"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json(response));
    }

    private static void stubExternalApiResponse(String weatherApiResponse, int status) {
        wireMockServer.stubFor(get(urlEqualTo("/external?lat=11.11&lon=33.33")).willReturn(
                aResponse()
                        .withStatus(status)
                        .withBody(weatherApiResponse)
                        .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")
        ));
    }
}
