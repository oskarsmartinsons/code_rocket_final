package com.meawallet.smartrequest.itest;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static com.github.springtestdbunit.annotation.DatabaseOperation.DELETE_ALL;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DatabaseTearDown(value = "classpath:dbunit/empty_dataset.xml", type = DELETE_ALL)
public class GetExistingLocationTemperatureFromExtApiIntegrationTest extends BaseIntegrationTest {

    // Before run - update expected temperature in "temperatureFromExtApiNewLocationSuccess_ExpectedState.xml"
    //              according to expected one in "externalApiResponseSuccess.json" file
    @Test
    @DatabaseSetup(value = "classpath:dbunit/temperatureFromExtApiForExistingLocationSuccess_InitialState.xml")
    @ExpectedDatabase(value = "classpath:dbunit/temperatureFromExtApiForExistingLocationSuccess_ExpectedState.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    void shouldReturnTemperatureFromExtApiForExistingLocation() throws Exception {
        var weatherApiResponse = readJson("externalApiResponseSuccess.json");
        stubExternalApiResponse(weatherApiResponse, 200);

        mvc.perform(MockMvcRequestBuilders.get("/weather?lat=11.11&lon=33.33"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.temperature").value("27.5"));
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
