package com.global.saving.group.util;

import com.global.saving.group.enums.CountryCode;

import java.math.BigDecimal;
import java.util.Map;

import static com.global.saving.group.enums.CountryCode.*;

public class TestUtil {
    private static final Map<CountryCode, String> TAX_RATE_MAP = Map.ofEntries(
            Map.entry(ES, "0.20"),
            Map.entry(DE, "0.19"),
            Map.entry(FR, "0.25"),
            Map.entry(IN, "0.10"),
            Map.entry(IT, "0.15")
    );

    public static BigDecimal calculateNetPrice(BigDecimal gross, CountryCode countryIso) {
        return gross.subtract(
                gross.multiply(new BigDecimal(TAX_RATE_MAP.get(countryIso)))
        );
    }
}
