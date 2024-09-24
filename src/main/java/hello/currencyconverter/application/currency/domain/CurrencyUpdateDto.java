package hello.currencyconverter.application.currency.domain;


import lombok.Data;

@Data
public class CurrencyUpdateDto {

    private String code;
    private String name;
    private String symbol;

}
