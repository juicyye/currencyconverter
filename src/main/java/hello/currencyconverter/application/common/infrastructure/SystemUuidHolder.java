package hello.currencyconverter.application.common.infrastructure;

import hello.currencyconverter.application.common.service.port.UuidHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SystemUuidHolder implements UuidHolder {
    @Override
    public String uuid() {
        return UUID.randomUUID().toString();
    }
}
