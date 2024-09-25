package hello.currencyconverter.application.user.controller;

import hello.currencyconverter.application.user.controller.port.UserService;
import hello.currencyconverter.application.user.controller.request.LoginReqDto;
import hello.currencyconverter.application.user.controller.response.UserResponse;
import hello.currencyconverter.application.user.domain.User;
import hello.currencyconverter.application.user.domain.UserCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserCreate userCreate) {
        User user = userService.create(userCreate);
        return new ResponseEntity<>(UserResponse.from(user), HttpStatus.CREATED);
    }

}
