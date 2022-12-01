package com.weatherservice.webclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.weatherservice.model.Weather;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.weatherservice.webclient.WeatherClient.API_KEY;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WireMockTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WeatherRestApiIntegrationTest {
    @Value("${api_host}")
    private String apiHost;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private final static WireMockServer wireMockServer = new WireMockServer(9090);
    String date = "2022-11-16";

    @BeforeAll
    static void startServer() {
        wireMockServer.start();
        configureFor(wireMockServer.port());
        //System.out.println("WireMock Server is running on port " + wireMockServer.getOptions().portNumber());
    }

    @org.junit.jupiter.api.Test
    void should_get_best_weather_conditions() throws Exception {
        // given

        // Jastarnia
        stubFor(WireMock.get(urlPathEqualTo(apiHost + "/v2.0/forecast/daily"))
                .withQueryParam("lat", equalTo(String.valueOf(54.69606)))
                .withQueryParam("lon", equalTo(String.valueOf(18.67873)))
                .withQueryParam("key", equalTo(String.valueOf(API_KEY)))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/jastarnia-response.json")));

        stubFor(WireMock.get(urlPathEqualTo("/v2.0/forecast/daily"))
                .withQueryParam("lat", equalTo(String.valueOf(54.69606)))
                .withQueryParam("lon", equalTo(String.valueOf(18.67873)))
                .withQueryParam("key", equalTo(String.valueOf(API_KEY)))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/jastarnia-response.json")));

        // Bridgetown
        stubFor(WireMock.get(urlPathEqualTo(apiHost + "/v2.0/forecast/daily"))
                .withQueryParam("lat", equalTo(String.valueOf(13.10732)))
                .withQueryParam("lon", equalTo(String.valueOf(-59.62021)))
                .withQueryParam("key", equalTo(String.valueOf(API_KEY)))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/bridgetown-response.json")));

        stubFor(WireMock.get(urlPathEqualTo("/v2.0/forecast/daily"))
                .withQueryParam("lat", equalTo(String.valueOf(13.10732)))
                .withQueryParam("lon", equalTo(String.valueOf(-59.62021)))
                .withQueryParam("key", equalTo(String.valueOf(API_KEY)))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/bridgetown-response.json")));

        // Fortaleza
        stubFor(WireMock.get(urlPathEqualTo(apiHost + "/v2.0/forecast/daily"))
                .withQueryParam("lat", equalTo(String.valueOf(-3.71722)))
                .withQueryParam("lon", equalTo(String.valueOf(-38.54306)))
                .withQueryParam("key", equalTo(String.valueOf(API_KEY)))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/fortelaza-response.json")));

        stubFor(WireMock.get(urlPathEqualTo("/v2.0/forecast/daily"))
                .withQueryParam("lat", equalTo(String.valueOf(-3.71722)))
                .withQueryParam("lon", equalTo(String.valueOf(-38.54306)))
                .withQueryParam("key", equalTo(String.valueOf(API_KEY)))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/fortelaza-response.json")));

        // Pissoúri
        stubFor(WireMock.get(urlPathEqualTo(apiHost + "/v2.0/forecast/daily"))
                .withQueryParam("lat", equalTo(String.valueOf(34.66942)))
                .withQueryParam("lon", equalTo(String.valueOf(32.70132)))
                .withQueryParam("key", equalTo(String.valueOf(API_KEY)))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/pissoúri-response.json")));

        stubFor(WireMock.get(urlPathEqualTo("/v2.0/forecast/daily"))
                .withQueryParam("lat", equalTo(String.valueOf(34.66942)))
                .withQueryParam("lon", equalTo(String.valueOf(32.70132)))
                .withQueryParam("key", equalTo(String.valueOf(API_KEY)))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/pissoúri-response.json")));

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/weather/" + date))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        // then
        Weather response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(
                StandardCharsets.UTF_8), Weather.class);

        verify(getRequestedFor(urlPathEqualTo("/v2.0/forecast/daily")));

        System.out.println(response);
    }

    /*@org.junit.jupiter.api.Test
    void should_call_get_weather_forecast_and_return_an_error_due_not_found_request() throws Exception {

        stubFor(WireMock.get(urlPathEqualTo(apiHost + "/v2.0/forecast/daily"))
                .withQueryParam("lat", equalTo(String.valueOf(34.66942)))
                .withQueryParam("lon", equalTo(String.valueOf(32.70132)))
                .withQueryParam("key", equalTo(String.valueOf(API_KEY)))
                .willReturn(notFound()));

        stubFor(WireMock.get(urlPathEqualTo("/v2.0/forecast/daily"))
                .willReturn(notFound()));

        mockMvc.perform(get("/weather/" + date))
                .andDo(print()).andExpect(status().isNotFound());

        verify(getRequestedFor(urlPathEqualTo("/v2.0/forecast/daily")));
    }*/


    /*@AfterAll
    static void stopServer() {
        wireMockServer.stop();
    }*/
}
