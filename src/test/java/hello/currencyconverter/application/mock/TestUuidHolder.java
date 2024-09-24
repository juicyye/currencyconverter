package hello.currencyconverter.application.mock;

import hello.currencyconverter.application.common.service.port.UuidHolder;
import lombok.RequiredArgsConstructor;


public record TestUuidHolder(String uuid) implements UuidHolder {

}
