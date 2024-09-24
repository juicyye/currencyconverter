package hello.currencyconverter.application.exchangerates.domain;

import hello.currencyconverter.application.currency.domain.Currency;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ExchangeRates {
    private Long id;
    private Integer exchangeRate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private Currency baseCurrency;
    private Currency targetCurrency;

}
