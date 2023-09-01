package com.global.saving.group.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.saving.group.dto.NetPriceResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Map;

import static com.global.saving.group.enums.CountryCode.*;
import static com.global.saving.group.util.TestUtil.calculateNetPrice;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"wiremock.server.httpsPort=-1"})
@ActiveProfiles("it-tests")
class NetPriceCalculatorControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("Test for valid country code")
    void calculateNetPrice_ValidCountryCode() throws Exception {
        // given
        var gross = new BigDecimal(100);
        var country = "ES";
        var grossPriceRequestDto = Map.ofEntries(
            Map.entry("grossPrice", gross),
            Map.entry("country", country)
        );
        var request = objectMapper.writeValueAsString(grossPriceRequestDto);
        var expectedPrice = calculateNetPrice(gross, ES);

        // when
        var response = mockMvc.perform(
                        post("/net-prices")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        var netPriceResponseDto = objectMapper.readValue(response, NetPriceResponseDto.class);

        // then
        assertEquals(expectedPrice, netPriceResponseDto.getNetPrice());
    }

    @Test
    @DisplayName("Test for invalid country code")
    void calculateNetPrice_InvalidCountryCode() throws Exception {
        // given
        var gross = new BigDecimal(100);
        var country = "XX";
        var grossPriceRequestDto = Map.ofEntries(
                Map.entry("grossPrice", gross),
                Map.entry("country", country)
        );
        var request = objectMapper.writeValueAsString(grossPriceRequestDto);

        // when
        var response = mockMvc.perform(
                        post("/net-prices")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    @DisplayName("Test for invalid gross price")
    void calculateNetPrice_InvalidGrossPrice() throws Exception {
        // given
        var gross = new BigDecimal(-100);
        var country = "DE";
        var grossPriceRequestDto = Map.ofEntries(
                Map.entry("grossPrice", gross),
                Map.entry("country", country)
        );
        var request = objectMapper.writeValueAsString(grossPriceRequestDto);

        // when
        var response = mockMvc.perform(
                        post("/net-prices")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}