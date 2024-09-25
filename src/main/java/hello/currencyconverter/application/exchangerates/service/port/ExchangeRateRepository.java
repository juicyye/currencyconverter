package hello.currencyconverter.application.exchangerates.service.port;

import hello.currencyconverter.application.exchangerates.domain.ExchangeRates;

import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepository {

    ExchangeRates save(ExchangeRates exchangeRates);
    Optional<ExchangeRates> findLastExchangeRate(String baseCode, String targetCode);
    List<ExchangeRates> findAllExchangeRates(String baseCode, String targetCode);

}
