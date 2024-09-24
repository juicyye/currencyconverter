package hello.currencyconverter.application.currency.controller.port;

import hello.currencyconverter.application.currency.domain.Currency;
import hello.currencyconverter.application.currency.domain.CurrencyCreateDto;

import java.util.List;

public interface CurrencyService {

    Currency getByCode(String code);
    List<Currency> getAll();
    Long add(CurrencyCreateDto currencyCreateDto);
}
