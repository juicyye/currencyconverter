package hello.currencyconverter.application.exchangerates.controller;

import hello.currencyconverter.application.exchangerates.controller.port.ExchangeRateService;
import hello.currencyconverter.application.exchangerates.controller.request.ExchangeRateRequest;
import hello.currencyconverter.application.exchangerates.controller.response.ExchangeRateResponse;
import hello.currencyconverter.application.exchangerates.domain.ExchangeRates;
import hello.currencyconverter.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateService rateService;


    @GetMapping("/api/currency-converter")
    public ResponseEntity<?> findCurrencyConverter(@RequestBody ExchangeRateRequest request) {
        ExchangeRates rate = rateService.getRate(request);
        return new ResponseEntity<>(new ApiResponse<>(1, "환율 비교 성공", ExchangeRateResponse.from(rate,request.getAmount())), HttpStatus.OK);

    }
}
