package hello.currencyconverter.application.notification.domain;

import hello.currencyconverter.application.user.domain.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Notification {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private User user;
}
