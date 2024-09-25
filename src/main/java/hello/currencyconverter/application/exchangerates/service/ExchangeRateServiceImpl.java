package hello.currencyconverter.application.exchangerates.service;

import hello.currencyconverter.application.exchangerates.controller.port.ExchangeRateService;
import hello.currencyconverter.application.exchangerates.controller.request.ExchangeRateRequest;
import hello.currencyconverter.application.exchangerates.domain.ExchangeRates;
import hello.currencyconverter.application.exchangerates.service.port.ExchangeRateRepository;
import hello.currencyconverter.global.exception.CustomApiException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Builder
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;


    @Override
    public ExchangeRates getRate(ExchangeRateRequest request) {
        return exchangeRateRepository.findLastExchangeRate(request.getBaseCurrencyCode(), request.getTargetCurrencyCode())
                .orElseThrow(() -> new CustomApiException("원하시는 환율을 찾을 수 없습니다."));
    }
}
