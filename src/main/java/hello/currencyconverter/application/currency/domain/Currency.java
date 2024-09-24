package hello.currencyconverter.application.currency.domain;

import hello.currencyconverter.application.common.service.port.LocalDateTimeHolder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class Currency {
    private Long id;
    private String code; // 통화코드 USD
    private String name; // 통화이름 미국 달러
    private String symbol; // 심볼 $
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public static Currency create(CurrencyCreate currencyCreate, LocalDateTimeHolder localDateTimeHolder) {
        return Currency.builder()
                .code(currencyCreate.getCode())
                .name(currencyCreate.getName())
                .symbol(currencyCreate.getSymbol())
                .createDate(localDateTimeHolder.localDateTime())
                .updateDate(localDateTimeHolder.localDateTime())
                .build();
    }

    public Currency update(CurrencyUpdate currencyUpdate, LocalDateTimeHolder localDateTimeHolder) {
        return Currency.builder()
                .code(currencyUpdate.getCode())
                .name(currencyUpdate.getName())
                .symbol(currencyUpdate.getSymbol())
                .createDate(createDate)
                .updateDate(localDateTimeHolder.localDateTime())
                .build();
    }


}
