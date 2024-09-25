package hello.currencyconverter.application.user.service.port;

import hello.currencyconverter.application.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserByEmail(String email);

    User save(User user);



}
