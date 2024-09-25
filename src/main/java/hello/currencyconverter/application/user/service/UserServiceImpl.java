package hello.currencyconverter.application.user.service;

import hello.currencyconverter.application.common.service.port.LocalDateTimeHolder;
import hello.currencyconverter.application.user.controller.port.UserService;
import hello.currencyconverter.application.user.domain.Role;
import hello.currencyconverter.application.user.domain.User;
import hello.currencyconverter.application.user.domain.UserCreate;
import hello.currencyconverter.application.user.service.port.UserRepository;
import hello.currencyconverter.global.exception.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LocalDateTimeHolder localDateTimeHolder;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new CustomApiException("유저를 찾을 수 없습니다."));
    }

    @Override
    public User create(UserCreate userCreate) {
        userCreate.setPassword(passwordEncoder.encode(userCreate.getPassword()));
        return userRepository.save(User.create(userCreate, Role.ROLE_USER,localDateTimeHolder));
    }
}
