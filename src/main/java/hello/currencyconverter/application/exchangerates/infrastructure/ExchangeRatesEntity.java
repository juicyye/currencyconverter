package hello.currencyconverter.application.exchangerates.infrastructure;

import hello.currencyconverter.application.common.BaseEntity;
import hello.currencyconverter.application.currency.infrastructure.CurrencyEntity;
import hello.currencyconverter.application.exchangerates.domain.ExchangeRates;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class ExchangeRatesEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double exchangeRate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_currency_id")
    private CurrencyEntity baseCurrency;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_currency_id")
    private CurrencyEntity targetCurrency;

    public static ExchangeRatesEntity FromModel(ExchangeRates domain) {
        return ExchangeRatesEntity.builder()
                .exchangeRate(domain.getExchangeRate())
                .baseCurrency(CurrencyEntity.fromModel(domain.getBaseCurrency()))
                .targetCurrency(CurrencyEntity.fromModel(domain.getTargetCurrency()))
                .build();
    }

    public ExchangeRates toModel() {
        return ExchangeRates.builder()
                .id(id)
                .exchangeRate(exchangeRate)
                .baseCurrency(baseCurrency.toModel())
                .targetCurrency(targetCurrency.toModel())
                .createDate(getCreateDate())
                .updateDate(getUpdateDate())
                .build();
    }


}
