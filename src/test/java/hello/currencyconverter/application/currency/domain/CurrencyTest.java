package hello.currencyconverter.application.currency.domain;

import hello.currencyconverter.application.mock.TestLocalDateTimeHolder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {

    @Test
    @DisplayName("CurrencyCreate로 Currency를 만들 수 있다")
    void currencyCreateToCurrencyTest() throws Exception {
        // given
        CurrencyCreate currencyCreate = CurrencyCreate.builder()
                .name("US 달러")
                .code("USD")
                .symbol("$")
                .build();

        // when
        Currency currency = Currency.create(currencyCreate,
                new TestLocalDateTimeHolder(LocalDateTime.of(2024, 9, 23, 0, 43, 33)));

        // then
        assertAll(() ->{
            assertThat(currency.getName()).isEqualTo("US 달러");
            assertThat(currency.getCode()).isEqualTo("USD");
            assertThat(currency.getSymbol()).isEqualTo("$");
            assertThat(currency.getCreateDate()).isEqualTo(LocalDateTime.of(2024, 9, 23, 0, 43, 33));
        });
    }


}