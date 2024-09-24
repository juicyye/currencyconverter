package hello.currencyconverter.application.common.infrastructure;

import hello.currencyconverter.application.common.service.port.LocalDateHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SystemLocalDateHolder implements LocalDateHolder {
    @Override
    public LocalDate localDate() {
        return LocalDate.now();
    }
}
