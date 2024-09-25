package hello.currencyconverter.application.user.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserUpdate {
    private String password;
    private LocalDate birthday;
    private LocalDate lastLoginDate;
    private Role role;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
