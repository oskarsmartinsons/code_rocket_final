package com.meawallet.smartrequest.itest;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.meawallet.smartrequest.app.SmartRequestApp;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestExecutionListeners(value = {
        TransactionalTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
}, mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)

@SpringBootTest(classes = SmartRequestApp.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@AutoConfigureMockMvc
public class BaseIntegrationTest {
    @Autowired
    protected MockMvc mvc;

    protected static WireMockServer wireMockServer = new WireMockServer(20000);

    @BeforeAll
    static void beforeAll() {
        wireMockServer.start();
    }

    @AfterAll
    static void afterAll() {
        wireMockServer.stop();
    }

    @BeforeEach
    void setUp() {
        wireMockServer.resetAll();
    }

    /*    protected String readJson(String jsonName) {
            var resource = BaseIntegrationTest.class.getResourceAsStream("/json/" + jsonName);
            try {
                return IOUtils.toString(resource, Charset.defaultCharset());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }*/
    @Test
    @DatabaseSetup(value = "classpath:dbunit/findCarByIdSuccess.xml")
    @ExpectedDatabase(value = "classpath:dbunit/findCarByIdSuccess.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    void shouldFindCarById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/cars/1"))
                .andExpect(status().isOk());
    }
}
