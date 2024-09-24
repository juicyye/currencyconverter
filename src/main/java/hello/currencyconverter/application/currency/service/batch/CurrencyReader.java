package hello.currencyconverter.application.currency.service.batch;

import hello.currencyconverter.application.common.service.port.LocalDateHolder;
import hello.currencyconverter.application.currency.infrastructure.dto.CurrencyApiDto;
import hello.currencyconverter.application.currency.service.port.CurrencyClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class CurrencyReader implements ItemStreamReader<CurrencyApiDto> {
    private final CurrencyClient currencyClient;
    private final LocalDateHolder localDateHolder;
    private Iterator<CurrencyApiDto> iterator;
    private final String CURRENT_KEY = "current_key";
    private int currentNumber = 0;

    @Value("${currency.api.key}")
    private String apiKey;

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        Iterator<CurrencyApiDto> iterator = currencyClient.getCurrencies(apiKey, localDateHolder.localDate().toString()).iterator();
        if (executionContext.containsKey(CURRENT_KEY)) {
            currentNumber = executionContext.getInt(CURRENT_KEY);
        }
        for (int i = 0; i < currentNumber && iterator.hasNext(); i++) {
            iterator.next();
        }
    }

    @Override
    public CurrencyApiDto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(iterator != null && iterator.hasNext()) {
            currentNumber++;
            return iterator.next();

        }
        return null;
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        executionContext.putInt(CURRENT_KEY, currentNumber);
    }

    @Override
    public void close() throws ItemStreamException {
        ItemStreamReader.super.close();
    }
}
