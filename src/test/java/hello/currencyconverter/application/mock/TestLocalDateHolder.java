package hello.currencyconverter.application.mock;

import hello.currencyconverter.application.common.service.port.LocalDateHolder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;


public record TestLocalDateHolder(LocalDate localDate) implements LocalDateHolder {
}
