package hello.currencyconverter.application.user.infrastructure;

import hello.currencyconverter.application.common.BaseEntity;
import hello.currencyconverter.application.user.domain.Role;
import hello.currencyconverter.application.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@SuperBuilder
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

    public UserEntity(BaseEntityBuilder<?, ?> b, Long id, String email, String password, LocalDate birthday, LocalDate lastLoginDate, Role role) {
        super(b);
        this.id = id;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.lastLoginDate = lastLoginDate;
        this.role = role;
    }

    public UserEntity() {
        super();
    }

    public User toModel() {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .birthday(birthday)
                .lastLoginDate(lastLoginDate)
                .role(role)
                .createDate(getCreateDate())
                .updateDate(getUpdateDate())
                .build();
    }

    public static UserEntity fromModel(User user) {
        return UserEntity.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .birthday(user.getBirthday())
                .lastLoginDate(user.getLastLoginDate())
                .role(user.getRole())
                .createDate(user.getCreateDate())
                .updateDate(user.getUpdateDate())
                .build();
    }

}
