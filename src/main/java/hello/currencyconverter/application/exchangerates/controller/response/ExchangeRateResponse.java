package hello.currencyconverter.application.exchangerates.controller.response;

import hello.currencyconverter.application.exchangerates.domain.ExchangeRates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ExchangeRateResponse {
    private String baseCurrencyCode;
    private String targetCurrencyCode;
    private Double amount;
    private Double exchangeAmount;

    public static ExchangeRateResponse from(ExchangeRates rate, Double amount) {
        return ExchangeRateResponse.builder()
                .baseCurrencyCode(rate.getBaseCurrency().getCode())
                .targetCurrencyCode(rate.getTargetCurrency().getCode())
                .amount(amount)
                .exchangeAmount(amount/1000 * rate.getExchangeRate())
                .build();
    }
}
