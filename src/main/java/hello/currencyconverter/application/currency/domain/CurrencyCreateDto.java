package hello.currencyconverter.application.currency.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CurrencyCreateDto {

    private String code;
    private String name;
    private String symbol;

}
