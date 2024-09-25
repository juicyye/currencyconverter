package hello.currencyconverter.application.currency.infrastructure;

import hello.currencyconverter.application.currency.infrastructure.dto.CurrencyApiDto;
import hello.currencyconverter.application.currency.service.port.CurrencyClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "currency-Client", url = "https://www.koreaexim.go.kr" )
public interface CurrencyClientImpl extends CurrencyClient {

    @Override
    @GetMapping("/site/program/financial/exchangeJSON?authkey={apikey}&searchdate={currentDate}&data=AP01")
    List<CurrencyApiDto> getCurrencies(@PathVariable("apikey") String apiKey, @PathVariable("currentDate") String currentDate);

}
