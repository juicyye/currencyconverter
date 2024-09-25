package hello.currencyconverter.application.currency.infrastructure;

import hello.currencyconverter.application.currency.domain.Currency;
import hello.currencyconverter.application.currency.service.port.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CurrencyRepositoryImpl implements CurrencyRepository {
    private final CurrencyJpaRepository currencyJpaRepository;

    @Override
    public Currency save(Currency currency) {
        return currencyJpaRepository.save(CurrencyEntity.fromModel(currency)).toModel();
    }

    @Override
    public Optional<Currency> findByCode(String currencyCode) {
        return currencyJpaRepository.findByCode(currencyCode).map(CurrencyEntity::toModel);
    }

    @Override
    public List<Currency> findAll() {
        return currencyJpaRepository.findAll().stream()
                .map(CurrencyEntity::toModel)
                .toList();
    }
}
