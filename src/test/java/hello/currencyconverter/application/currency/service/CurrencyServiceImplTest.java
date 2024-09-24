package hello.currencyconverter.application.currency.service;

import hello.currencyconverter.application.currency.domain.Currency;
import hello.currencyconverter.application.currency.domain.CurrencyCreate;
import hello.currencyconverter.application.mock.FakeCurrencyRepository;
import hello.currencyconverter.application.mock.TestLocalDateTimeHolder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class CurrencyServiceImplTest {

    private CurrencyServiceImpl currencyServiceimpl;


    @BeforeEach
    void setUp() {
        currencyServiceimpl = currencyServiceimpl.builder()
                .currencyRepository(new FakeCurrencyRepository())
                .localDateTimeHolder(new TestLocalDateTimeHolder(LocalDateTime.of(2024, 9, 23, 0, 43, 33)))
                .build();
    }

    @Test
    @DisplayName("통화코드로 원하는 통화를 가져올 수 있다")
    void getByCode_test() throws Exception {
        // given
        CurrencyCreate currencyCreate = getCurrencyCreateDto("usd", "us 달러", "$");
        currencyServiceimpl.add(currencyCreate);

        // when
        Currency result = currencyServiceimpl.getByCode("usd");

        // then
        Assertions.assertAll(() -> {
            assertThat(result.getId()).isEqualTo(1L);
            assertThat(result.getCode()).isEqualTo("usd");
            assertThat(result.getName()).isEqualTo("us 달러");
            assertThat(result.getSymbol()).isEqualTo("$");
            assertThat(result.getCreateDate()).isEqualTo(LocalDateTime.of(2024, 9, 23, 0, 43, 33));
        });
    }

    @Test
    @DisplayName("createDto를 이용하여 통화를 생성할 수 있다.")
    void add_test() throws Exception {
        // given
        CurrencyCreate currencyCreate = getCurrencyCreateDto("usd", "us 달러", "$");


        // when
        currencyServiceimpl.add(currencyCreate);

        // then
        List<Currency> results = currencyServiceimpl.getAll();

        assertThat(results).hasSize(1)
                .extracting("id","code","name","symbol","createDate")
                .contains(tuple(1L,"usd","us 달러","$",LocalDateTime.of(2024, 9, 23, 0, 43, 33)));
    }



    private static CurrencyCreate getCurrencyCreateDto(String code, String name, String symbol) {
        return CurrencyCreate.builder()
                .code(code)
                .name(name)
                .symbol(symbol)
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