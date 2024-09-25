package hello.currencyconverter.application.currency.service.port;

import hello.currencyconverter.application.currency.domain.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepository {

    Currency save(Currency currency);
    Optional<Currency> findByCode(String currencyCode);
    List<Currency> findAll();


}
