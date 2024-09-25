package hello.currencyconverter.application;

import hello.currencyconverter.application.currency.infrastructure.CurrencyJpaRepository;
import hello.currencyconverter.application.exchangerates.infrastructure.ExchangeRateJpaRepository;
import hello.currencyconverter.application.user.infrastructure.UserJpaRepository;
import hello.currencyconverter.global.security.jwt.JwtProcess;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

@SpringBootTest
@ActiveProfiles("test")
@SqlGroup(
        {
                @Sql(value = "/sql/teardown.sql",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
        }
)
public class IntegrationInfraTestSupport {

    @Autowired
    protected ExchangeRateJpaRepository exchangeRateRepository;

    @Autowired
    protected CurrencyJpaRepository currencyRepository;

    @Autowired
    protected UserJpaRepository userRepository;

    @Autowired
    protected EntityManager em;

    @Autowired
    protected JwtProcess jwtProcess;
}
