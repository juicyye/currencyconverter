package hello.currencyconverter.application.exchangerates.domain;

import hello.currencyconverter.application.currency.domain.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ExchangeRates {
    private Long id;
    private Double exchangeRate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private Currency baseCurrency;
    private Currency targetCurrency;

}
