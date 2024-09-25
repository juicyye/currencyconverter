package hello.currencyconverter.application.exchangerates.service;

import hello.currencyconverter.application.common.service.port.LocalDateTimeHolder;
import hello.currencyconverter.application.exchangerates.controller.port.ExchangeRateService;
import hello.currencyconverter.application.exchangerates.controller.request.ExchangeRateRequest;
import hello.currencyconverter.application.exchangerates.domain.ExchangeRates;
import hello.currencyconverter.application.exchangerates.service.port.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;
    private final LocalDateTimeHolder localDateTimeHolder;


    @Override
    public ExchangeRates getRate(ExchangeRateRequest request) {
        return null;
    }
}
