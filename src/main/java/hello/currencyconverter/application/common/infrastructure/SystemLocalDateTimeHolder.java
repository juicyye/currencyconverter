package hello.currencyconverter.application.common.infrastructure;

import hello.currencyconverter.application.common.service.port.LocalDateHolder;
import hello.currencyconverter.application.common.service.port.LocalDateTimeHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class SystemLocalDateTimeHolder implements LocalDateTimeHolder {
    @Override
    public LocalDateTime localDateTime() {
        return LocalDateTime.now();
    }
}
