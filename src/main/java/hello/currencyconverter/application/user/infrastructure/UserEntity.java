package hello.currencyconverter.application.user.infrastructure;

import hello.currencyconverter.application.common.BaseEntity;
import hello.currencyconverter.application.user.domain.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private LocalDate birthday;
    private LocalDate lastLoginDate;
    @Enumerated(EnumType.STRING)
    private Role role;
}
