package hello.currencyconverter.application.exchangerates.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.checkerframework.common.value.qual.ArrayLen;

@Data
@Builder
@AllArgsConstructor
public class ExchangeRateRequest {
    @NotEmpty
    private String baseCurrencyCode;
    @NotEmpty
    private String targetCurrencyCode;
    @Positive
    private Double amount;
}
