package hello.currencyconverter.application.user.domain;

import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class User {

    private Long id;
    private String email;
    private String password;
    private LocalDate birthday;
    private LocalDate lastLoginDate;
    private Role role;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
