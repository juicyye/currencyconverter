package hello.currencyconverter.application.currency.controller.port;

import hello.currencyconverter.application.currency.domain.Currency;
import hello.currencyconverter.application.currency.domain.CurrencyCreate;

import java.util.List;

public interface CurrencyService {

    Currency getByCode(String code);
    List<Currency> getAll();
    Currency add(CurrencyCreate currencyCreate);
}
