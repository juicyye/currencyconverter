package hello.currencyconverter.application;

import hello.currencyconverter.application.currency.infrastructure.CurrencyJpaRepository;
import hello.currencyconverter.application.exchangerates.infrastructure.ExchangeRateJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class IntegrationInfraTestSupport {

    @Autowired
    protected ExchangeRateJpaRepository exchangeRateRepository;

    @Autowired
    protected CurrencyJpaRepository currencyRepository;
}
