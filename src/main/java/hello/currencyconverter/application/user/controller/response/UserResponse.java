package hello.currencyconverter.application.user.controller.response;

import hello.currencyconverter.application.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String email;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .build();
    }

}
