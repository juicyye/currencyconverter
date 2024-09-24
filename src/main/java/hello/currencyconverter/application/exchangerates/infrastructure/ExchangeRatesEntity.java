package hello.currencyconverter.application.exchangerates.infrastructure;

import hello.currencyconverter.application.common.BaseEntity;
import hello.currencyconverter.application.currency.infrastructure.CurrencyEntity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRatesEntity extends BaseEntity {
    private Long id;
    private Double exchangeRate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_currency_id")
    private CurrencyEntity baseCurrency;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_currency_id")
    private CurrencyEntity targetCurrency;
}
