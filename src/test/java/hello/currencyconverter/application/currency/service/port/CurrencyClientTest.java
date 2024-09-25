package hello.currencyconverter.application.currency.service.port;

import hello.currencyconverter.application.currency.infrastructure.dto.CurrencyApiDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CurrencyClientTest {

    @Value("${currency.api.key}")
    private String key;

    @Autowired
    private CurrencyClient currencyClient;

    @Test
    @DisplayName("feign클라이언트가 외부 api 데이터를 가져온다")
    void getCurrencies() throws Exception {
        // given
        System.out.println("key = " + key);

        // when
        List<CurrencyApiDto> currencies = currencyClient.getCurrencies(key, "20240925");
        System.out.println("currencies = " + currencies);
        CurrencyApiDto result = currencies.get(0);


        // then
        assertThat(result.getResult()).isEqualTo(1);
    }

    @Test
    @DisplayName("feign클라이언트가 잘못된 정보를 입력하면 result 값이 1이 아니게 된다")
    void getCurrenciesWithError() throws Exception {
        // given

        // when
        List<CurrencyApiDto> currencies = currencyClient.getCurrencies(key, "20181225");

        // then
        assertThat(currencies).isEmpty();
    }


}