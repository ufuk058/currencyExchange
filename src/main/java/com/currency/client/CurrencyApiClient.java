package com.currency.client;

import com.currency.dto.response.FixerCurrencies;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url="http://data.fixer.io/api/latest", name="currency-service")
public interface CurrencyApiClient {

    @GetMapping
    FixerCurrencies getAllCurrencies(@RequestParam String access_key); // Base currency might be required, check after impl complete

   @GetMapping
    FixerCurrencies getSelectedCurrencies(@RequestParam String access_key, @RequestParam List<String> symbols);





}
