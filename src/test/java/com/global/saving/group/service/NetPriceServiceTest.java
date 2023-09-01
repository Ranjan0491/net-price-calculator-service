package com.global.saving.group.service;

import com.global.saving.group.enums.CountryCode;
import com.global.saving.group.exceptions.BadRequestException;
import com.global.saving.group.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NetPriceServiceTest {
    @InjectMocks
    private NetPriceService service;

    @Test
    @DisplayName("Test for valid values")
    void calculateNetPrice() {
        // given
        var gross = new BigDecimal(100);
        var countryCode = CountryCode.DE;

        // when
        var result = service.calculateNetPrice(gross, countryCode);
        // then
        assertEquals(TestUtil.calculateNetPrice(gross, countryCode), result);
    }

    @Test
    @DisplayName("Test for invalid values")
    void calculateNetPrice_InvalidValues() {
        // given
        var gross = new BigDecimal(-100);
        var countryCode = CountryCode.DE;

        // when
        Executable executable = () -> service.calculateNetPrice(gross, countryCode);
        // then
        assertThrows(BadRequestException.class, executable);
    }
}