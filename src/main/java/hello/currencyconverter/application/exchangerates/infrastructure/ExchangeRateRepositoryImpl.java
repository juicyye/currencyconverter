package hello.currencyconverter.application.exchangerates.infrastructure;

import hello.currencyconverter.application.exchangerates.domain.ExchangeRates;
import hello.currencyconverter.application.exchangerates.service.port.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ExchangeRateRepositoryImpl implements ExchangeRateRepository {
    private final ExchangeRateJpaRepository exchangeRateJpaRepository;
    @Override
    public Long save(ExchangeRates exchangeRates) {
        return exchangeRateJpaRepository.save(ExchangeRatesEntity.FromModel(exchangeRates)).getId();
    }

    public Optional<ExchangeRates> findLastExchangeRate(String baseCode, String targetCode) {
        return exchangeRateJpaRepository.findLatest(baseCode, targetCode).map(ExchangeRatesEntity::toModel);

    }

    @Override
    public List<ExchangeRates> findAllExchangeRates(String baseCode, String targetCode) {
        return exchangeRateJpaRepository.findAllExchangeRates(baseCode, targetCode).stream()
                .map(ExchangeRatesEntity::toModel).toList();
    }
}
