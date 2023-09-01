package com.global.saving.group.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NetPriceResponseDto {
    private BigDecimal netPrice;
}
