package com.global.saving.group.dto;

import com.global.saving.group.enums.CountryCode;
import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrossPriceRequestDto {
    private BigDecimal grossPrice;
    private CountryCode country;
}
