package com.global.saving.group.controller;

import com.global.saving.group.dto.GrossPriceRequestDto;
import com.global.saving.group.dto.NetPriceResponseDto;
import com.global.saving.group.service.NetPriceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/net-prices")
@AllArgsConstructor
public class NetPriceCalculatorController {

    private final NetPriceService netPriceService;

    @PostMapping
    public ResponseEntity<NetPriceResponseDto> calculate(@RequestBody final GrossPriceRequestDto grossPriceRequestDto) {
        return ResponseEntity.ok(
                NetPriceResponseDto.builder()
                        .netPrice(netPriceService.calculateNetPrice(grossPriceRequestDto.getGrossPrice(), grossPriceRequestDto.getCountry()))
                        .build()
        );
    }

}
