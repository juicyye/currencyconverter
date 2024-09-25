package hello.currencyconverter.application.currency.service.batch;

import hello.currencyconverter.application.common.service.port.LocalDateTimeHolder;
import hello.currencyconverter.application.currency.domain.Currency;
import hello.currencyconverter.application.currency.infrastructure.dto.CurrencyApiDto;
import hello.currencyconverter.application.currency.service.port.CurrencyRepository;
import hello.currencyconverter.application.exchangerates.domain.ExchangeRates;
import hello.currencyconverter.application.exchangerates.service.port.ExchangeRateRepository;
import hello.currencyconverter.global.exception.CustomApiException;
import hello.currencyconverter.global.util.Constraint;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

@RequiredArgsConstructor
public class CurrencyWriter implements ItemWriter<CurrencyApiDto> {
    private final LocalDateTimeHolder localDateTimeHolder;
    private final CurrencyRepository currencyRepository;
    private final ExchangeRateRepository exchangeRateRepository;

    @Override
    public void write(Chunk<? extends CurrencyApiDto> chunk) throws Exception {

        for (CurrencyApiDto currencyApiDto : chunk) {
            Currency targetCurrency = currencyApiDto.toCurrency(localDateTimeHolder);
            Currency baseCurrency = currencyRepository.findByCode(Constraint.KOREA_CODE).orElseThrow(() -> new CustomApiException("원화가 저장이 안되어있습니다."));
            currencyRepository.save(targetCurrency);
            ExchangeRates exchangeRates = currencyApiDto.toExchangeRates(localDateTimeHolder, baseCurrency, targetCurrency);
            exchangeRateRepository.save(exchangeRates);

        }


    }
}
