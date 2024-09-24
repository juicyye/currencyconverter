package hello.currencyconverter.application.middle;

import hello.currencyconverter.application.IntegrationInfraTestSupport;
import hello.currencyconverter.application.currency.infrastructure.CurrencyEntity;
import hello.currencyconverter.application.exchangerates.infrastructure.ExchangeRatesEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


class ExchangeRateRepositoryImplTest extends IntegrationInfraTestSupport {



    @Test
    @DisplayName("환율엔티티를 저장하고 id를 반환받을 수 있다")
    void ExchangesSave() throws Exception {
        // given
        CurrencyEntity currency1 = createCurrencyEntity("us 달러", "usd", "$");
        CurrencyEntity currency2 = createCurrencyEntity("원화", "krw", "+");
        CurrencyEntity targetCurrency = currencyRepository.save(currency1);
        CurrencyEntity baseCurrency = currencyRepository.save(currency2);

        exchangeRateRepository.save(createExchangeRatesEntity(baseCurrency, targetCurrency, 1200.0));


        // when
        List<ExchangeRatesEntity> result = exchangeRateRepository.findAllExchangeRates(baseCurrency.getCode(), targetCurrency.getCode());

        // then
        assertThat(result).hasSize(1)
                .extracting("exchangeRate")
                .contains(1200.0);
    }

    @Test
    @DisplayName("가장 최신의 환율를 받을 수 있다.")
    void findLatest() throws Exception {
        // given
        CurrencyEntity currency1 = createCurrencyEntity("us 달러", "usd", "$");
        CurrencyEntity currency2 = createCurrencyEntity("원화", "krw", "+");
        CurrencyEntity targetCurrency = currencyRepository.save(currency1);
        CurrencyEntity baseCurrency = currencyRepository.save(currency2);

        exchangeRateRepository.save(createExchangeRatesEntity(baseCurrency, targetCurrency, 1200.0));
        exchangeRateRepository.save(createExchangeRatesEntity(baseCurrency, targetCurrency, 1300.0));

        // when
        ExchangeRatesEntity result = exchangeRateRepository.findLatest(baseCurrency.getCode(), targetCurrency.getCode()).get();

        // then
        assertAll(() -> {
            assertThat(result.getId()).isEqualTo(2L);
            assertThat(result.getExchangeRate()).isEqualTo(1300.0);
        });
    }

    private static ExchangeRatesEntity createExchangeRatesEntity(CurrencyEntity baseCurrency, CurrencyEntity targetCurrency, double exchangeRate) {
        return ExchangeRatesEntity.builder()
                .exchangeRate(exchangeRate)
                .baseCurrency(baseCurrency)
                .targetCurrency(targetCurrency)
                .build();
    }

    private static CurrencyEntity createCurrencyEntity(String name, String code, String symbol) {
        return CurrencyEntity.builder()
                .name(name)
                .code(code)
                .symbol(symbol)
                .build();
    }

}