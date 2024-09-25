package hello.currencyconverter.application.exchangerates.controller.port;

import hello.currencyconverter.application.exchangerates.controller.request.ExchangeRateRequest;
import hello.currencyconverter.application.exchangerates.domain.ExchangeRates;

public interface ExchangeRateService {
    ExchangeRates getRate(ExchangeRateRequest request);
}
