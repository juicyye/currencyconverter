package hello.currencyconverter.application.exchangerates.controller.request;

import lombok.Data;

@Data
public class ExchangeRateRequest {
    private String baseCurrencyCode;
    private String targetCurrencyCode;
    private Double amount;
}
