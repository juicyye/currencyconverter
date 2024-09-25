package hello.currencyconverter.application.exchangerates.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.currencyconverter.application.IntegrationConTestSupport;
import hello.currencyconverter.application.currency.domain.Currency;
import hello.currencyconverter.application.exchangerates.controller.port.ExchangeRateService;
import hello.currencyconverter.application.exchangerates.controller.request.ExchangeRateRequest;
import hello.currencyconverter.application.exchangerates.domain.ExchangeRates;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


class ExchangeRateControllerTest extends IntegrationConTestSupport {



    @Test
    @DisplayName("원하는 코드를 넣어 환율을 알 수 있다")
    void findCurrencyConverter() throws Exception {
        // given
        double rate = 1200.20;
        double amount = 1000;
        Currency baseCurrency = createCurrency("원화", "krw", "|", LocalDateTime.of(2024, 9, 25, 14, 42, 55), LocalDateTime.of(2024, 9, 25, 14, 42, 55));
        Currency targetCurrency = createCurrency("us 달러", "usd", "$", LocalDateTime.of(2024, 9, 25, 14, 42, 55), LocalDateTime.of(2024, 9, 25, 14, 42, 55));
        ExchangeRateRequest exchangeRateRequest = new ExchangeRateRequest("krw", "usd", amount);
        BDDMockito.given(exchangeRateService.getRate(any())).willReturn(createExchangeRates(baseCurrency,targetCurrency, rate));
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/currency-converter")
                .content(mapper.writeValueAsString(exchangeRateRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("환율 비교 성공"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.amount").value(amount))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.exchangeAmount").value(rate))

                ;


        // then
    }

    private static ExchangeRates createExchangeRates(Currency baseCurrency, Currency targetCurrency, double exchangeRate) {
        return ExchangeRates.builder()
                .exchangeRate(exchangeRate)
                .createDate(LocalDateTime.of(2024, 9, 25, 14, 42, 55))
                .updateDate(LocalDateTime.of(2024, 9, 25, 14, 42, 55))
                .baseCurrency(baseCurrency)
                .targetCurrency(targetCurrency)
                .build();
    }

    private static Currency createCurrency(String name, String code, String symbol, LocalDateTime createDate, LocalDateTime updateDate) {
        return Currency.builder()
                .name(name)
                .code(code)
                .symbol(symbol)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }

}