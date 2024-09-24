package hello.currencyconverter.application.currency.infrastructure;

import hello.currencyconverter.application.currency.infrastructure.dto.CurrencyApiDto;
import hello.currencyconverter.application.currency.service.port.CurrencyClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "currency-Client")
public interface CurrencyClientImpl extends CurrencyClient {

    @Override
    @GetMapping("https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?authkey={apikey}={currentDate}&data=AP01")
    List<CurrencyApiDto> getCurrencies(@PathVariable("apikey") String apiKey, @PathVariable("currentDate") String currentDate);

}
