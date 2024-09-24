package hello.currencyconverter.application.mock;

import hello.currencyconverter.application.common.service.port.LocalDateTimeHolder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


public record TestLocalDateTimeHolder(LocalDateTime localDateTime) implements LocalDateTimeHolder {
}
