package hello.currencyconverter.application.user.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserCreate {
    private String email;
    private String password;
    private LocalDate birthday;
    private Role role;
}
