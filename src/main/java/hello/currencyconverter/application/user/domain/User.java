package hello.currencyconverter.application.user.domain;

import hello.currencyconverter.application.common.service.port.LocalDateHolder;
import hello.currencyconverter.application.common.service.port.LocalDateTimeHolder;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class User {

    private Long id;
    private String email;
    private String password;
    private LocalDate birthday;
    private LocalDate lastLoginDate;
    private Role role;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public static User create(UserCreate userCreate, Role role, LocalDateTimeHolder localDateTimeHolder) {
        return User.builder()
                .email(userCreate.getEmail())
                .password(userCreate.getPassword())
                .birthday(userCreate.getBirthday())
                .role(userCreate.getRole())
                .createDate(localDateTimeHolder.localDateTime())
                .updateDate(localDateTimeHolder.localDateTime())
                .build();
    }
}
