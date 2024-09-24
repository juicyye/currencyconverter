package hello.currencyconverter.application.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ROLE_ADMIN("관리자"), ROLE_USER("유저");
    private final String description;
}
