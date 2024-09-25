package hello.currencyconverter.application.exchangerates.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateRequest {
    @NotEmpty(message = "기준이 되는 국가를 작성해주세요")
    private String baseCurrencyCode;
    @NotEmpty(message = "원하시는 국가를 작성해주세요")
    private String targetCurrencyCode;
    @Positive(message = "0이상 값만 작성해주세요")
    private Double amount;
}
