package hello.currencyconverter.application.currency.service;

import hello.currencyconverter.application.common.service.port.LocalDateTimeHolder;
import hello.currencyconverter.application.currency.controller.port.CurrencyService;
import hello.currencyconverter.application.currency.domain.Currency;
import hello.currencyconverter.application.currency.domain.CurrencyCreate;
import hello.currencyconverter.application.currency.service.port.CurrencyRepository;
import hello.currencyconverter.global.exception.CustomApiException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final LocalDateTimeHolder localDateTimeHolder;


    @Override
    public Currency getByCode(String currencyCode) {
        return currencyRepository.findByCode(currencyCode).orElseThrow(() -> new CustomApiException("없는 통화입니다."));
    }

    @Override
    public List<Currency> getAll() {
        return currencyRepository.findAll();
    }

    @Override
    public Long add(CurrencyCreate currencyCreate) {
        return currencyRepository.save(Currency.create(currencyCreate, localDateTimeHolder));
    }
}
