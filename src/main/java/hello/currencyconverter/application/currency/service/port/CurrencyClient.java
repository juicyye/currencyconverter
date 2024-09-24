package hello.currencyconverter.application.currency.service.port;

import hello.currencyconverter.application.currency.infrastructure.dto.CurrencyApiDto;

import java.util.List;

public interface CurrencyClient {
    List<CurrencyApiDto> getCurrencies(String apiKey, String currentDate);
}
