package com.global.saving.group.service;

import com.global.saving.group.enums.CountryCode;
import com.global.saving.group.enums.ErrorDetail;
import com.global.saving.group.exceptions.BadRequestException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

import static com.global.saving.group.enums.CountryCode.*;

@Service
public class NetPriceService {

    private static final Map<CountryCode, String> TAX_RATE_MAP = Map.ofEntries(
            Map.entry(ES, "0.20"),
            Map.entry(DE, "0.19"),
            Map.entry(FR, "0.25"),
            Map.entry(IN, "0.10"),
            Map.entry(IT, "0.15")
    );

    public BigDecimal calculateNetPrice(BigDecimal gross, CountryCode countryIso) {
        if(gross.doubleValue() < 0) {
            throw new BadRequestException(Map.ofEntries(
                    Map.entry("grossPrice", "should not be negative")
            ));
        }
        return gross.subtract(
                gross.multiply(new BigDecimal(TAX_RATE_MAP.get(countryIso)))
        );
    }
}
