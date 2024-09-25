package hello.currencyconverter.application.user.controller.port;

import hello.currencyconverter.application.user.domain.User;
import hello.currencyconverter.application.user.domain.UserCreate;

public interface UserService {
    User getUserByEmail(String email);

    User create(UserCreate userCreate);
}
