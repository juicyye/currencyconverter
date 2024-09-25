package hello.currencyconverter.application.exchangerates.service;

import hello.currencyconverter.application.currency.domain.Currency;
import hello.currencyconverter.application.exchangerates.domain.ExchangeRates;
import hello.currencyconverter.application.mock.FakeExchangeRatesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class ExchangeRateServiceImplTest {

    private ExchangeRateServiceImpl exchangeRateService;
    private FakeExchangeRatesRepository fakeExchangeRatesRepository = new FakeExchangeRatesRepository();

    @BeforeEach
    void setUp() {
        exchangeRateService = exchangeRateService.builder()
                .exchangeRateRepository(fakeExchangeRatesRepository)
                .build();
    }

    @Test
    @DisplayName("원하는 코드를 넣어 환율을 찾는다")
    void getRate() throws Exception {
        // given
        Currency baseCurrency = createCurrency("원화", "krw", "|", LocalDateTime.of(2024, 9, 25, 14, 42, 55), LocalDateTime.of(2024, 9, 25, 14, 42, 55));
        Currency targetCurrency = createCurrency("원화", "krw", "|", LocalDateTime.of(2024, 9, 25, 14, 42, 55), LocalDateTime.of(2024, 9, 25, 14, 42, 55));
        ExchangeRates exchangeRates = createExchangeRates(baseCurrency, targetCurrency, 1000.0);
        fakeExchangeRatesRepository.save(exchangeRates);

        // when
        ExchangeRates result = fakeExchangeRatesRepository.findLastExchangeRate(baseCurrency.getCode(), targetCurrency.getCode()).get();

        // then
        assertAll(() -> {
            assertThat(result.getBaseCurrency()).isEqualTo(baseCurrency);
            assertThat(result.getTargetCurrency()).isEqualTo(targetCurrency);
            assertThat(result.getExchangeRate()).isEqualTo(1000.0);
        });
    }

    @Test
    @DisplayName("코드가 없으면 오류를 내뿜는다")
    void getRateWithoutCode() throws Exception {
        // given
        Currency baseCurrency = createCurrency("원화", "krw", "|", LocalDateTime.of(2024, 9, 25, 14, 42, 55), LocalDateTime.of(2024, 9, 25, 14, 42, 55));
        Currency targetCurrency = createCurrency("원화", "krw", "|", LocalDateTime.of(2024, 9, 25, 14, 42, 55), LocalDateTime.of(2024, 9, 25, 14, 42, 55));
        ExchangeRates exchangeRates = createExchangeRates(baseCurrency, targetCurrency, 1000.0);
        fakeExchangeRatesRepository.save(exchangeRates);

        // when


        // then
        assertThatThrownBy(() -> fakeExchangeRatesRepository.findLastExchangeRate("123", "2345").get())
                .isInstanceOf(NoSuchElementException.class);

    }

    @Test
    @DisplayName("원하는 환율의 전체 기간을 알 수 있다")
    void getAllRate() throws Exception {
        // given
        Currency baseCurrency = createCurrency("원화", "krw", "|", LocalDateTime.of(2024, 9, 25, 14, 42, 55), LocalDateTime.of(2024, 9, 25, 14, 42, 55));
        Currency targetCurrency = createCurrency("원화", "krw", "|", LocalDateTime.of(2024, 9, 25, 14, 42, 55), LocalDateTime.of(2024, 9, 25, 14, 42, 55));
        ExchangeRates exchangeRates = createExchangeRates(baseCurrency, targetCurrency, 1000.0);
        fakeExchangeRatesRepository.save(exchangeRates);

        // when
        List<ExchangeRates> result = fakeExchangeRatesRepository.findAllExchangeRates(baseCurrency.getCode(), targetCurrency.getCode());

        // then
        assertThat(result).hasSize(1)
                .extracting("exchangeRate", "baseCurrency", "targetCurrency")
                .contains(tuple(1000.0,baseCurrency,targetCurrency));
    }

    private static ExchangeRates createExchangeRates(Currency baseCurrency, Currency targetCurrency, double exchangeRate) {
        return ExchangeRates.builder()
                .exchangeRate(exchangeRate)
                .createDate(LocalDateTime.of(2024, 9, 25, 14, 42, 55))
                .updateDate(LocalDateTime.of(2024, 9, 25, 14, 42, 55))
                .baseCurrency(baseCurrency)
                .targetCurrency(targetCurrency)
                .build();
    }

    private static Currency createCurrency(String name, String code, String symbol, LocalDateTime createDate, LocalDateTime updateDate) {
        return Currency.builder()
                .name(name)
                .code(code)
                .symbol(symbol)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }



}