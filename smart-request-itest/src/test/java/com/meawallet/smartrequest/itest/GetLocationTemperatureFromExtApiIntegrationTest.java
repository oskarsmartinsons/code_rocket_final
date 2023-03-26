//package com.meawallet.smartrequest.itest;
//
//import com.github.springtestdbunit.annotation.DatabaseSetup;
//import com.github.springtestdbunit.annotation.DatabaseTearDown;
//import com.github.springtestdbunit.annotation.ExpectedDatabase;
//import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpHeaders;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import static com.github.springtestdbunit.annotation.DatabaseOperation.DELETE_ALL;
//import static com.github.tomakehurst.wiremock.client.WireMock.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//@DatabaseTearDown(value = "classpath:dbunit/empty_dataset.xml", type = DELETE_ALL)
//public class GetLocationTemperatureFromExtApiIntegrationTest extends BaseIntegrationTest {
//    @Test
//    @DatabaseSetup(value = "classpath:dbunit/temperatureFromExtApiSuccess_InitialState.xml")
// //   @ExpectedDatabase(value = "classpath:dbunit/temperatureFromExtApiNewLocationSuccess_Expected.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
//    void shouldReturnTemperatureFromExtApiIfFoundLocationHasInvalidTemperature() throws Exception {
//        var weatherApiResponse = readJson("weatherApiResponseSuccess.json");
//
//        stubExternalApiResponse(weatherApiResponse, 200);
//
//        mvc.perform(MockMvcRequestBuilders.get("/weather?lat=11.11&lon=33.33"))
//                .andExpect(status().isOk());
//    }
//
//    private static void stubExternalApiResponse(String weatherApiResponse, int status) {
//        wireMockServer.stubFor(get(urlEqualTo("/external")).willReturn(
//                aResponse()
//                        .withStatus(status)
//                        .withBody(weatherApiResponse)
//                        .withHeader(HttpHeaders.CONTENT_TYPE, "application/json")
//        ));
//    }
//}
