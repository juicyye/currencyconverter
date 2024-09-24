package hello.currencyconverter.application.exchangerates.infrastructure;

import hello.currencyconverter.application.currency.domain.Currency;
import hello.currencyconverter.application.exchangerates.domain.ExchangeRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ExchangeRateJpaRepository extends JpaRepository<ExchangeRatesEntity, Long> {


    @Query("select e from ExchangeRatesEntity e join fetch e.baseCurrency bc join fetch e.targetCurrency tc " +
            "where bc.code = :bc and tc.code = :tc order by e.id desc limit 1")
    Optional<ExchangeRatesEntity> findLatest(@Param("bc") String baseCode,@Param("tc") String targetCode);

    @Query("select e from ExchangeRatesEntity e join fetch e.baseCurrency bc join fetch e.targetCurrency tc " +
            "where bc.code = :bc and tc.code = :tc")
    List<ExchangeRatesEntity> findAllExchangeRates(@Param("bc") String baseCode,@Param("tc") String targetCode);

}
