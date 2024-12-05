package com.currency.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FixerCurrencies {
    private boolean success;
    private Long timestamp;
    private String base;
    private LocalDate date;
    private Map<String, BigDecimal> rates;

}
