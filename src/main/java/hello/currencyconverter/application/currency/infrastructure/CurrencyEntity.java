package hello.currencyconverter.application.currency.infrastructure;

import hello.currencyconverter.application.common.BaseEntity;
import hello.currencyconverter.application.currency.domain.Currency;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class CurrencyEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String symbol;

    public CurrencyEntity(BaseEntityBuilder<?, ?> b, Long id, String code, String name, String symbol) {
        super(b);
        this.id = id;
        this.code = code;
        this.name = name;
        this.symbol = symbol;
    }

    public static CurrencyEntity fromModel(Currency currency) {
        return CurrencyEntity.builder()
                .id(currency.getId())
                .code(currency.getCode())
                .name(currency.getName())
                .symbol(currency.getSymbol())
                .createDate(currency.getCreateDate())
                .updateDate(currency.getUpdateDate())
                .build();

    }

    public Currency toModel() {
        return Currency.builder()
                .id(id)
                .code(code)
                .name(name)
                .symbol(symbol)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();

    }
}
