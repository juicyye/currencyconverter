package hello.currencyconverter.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {

    private final Integer code;  // 성공 1, 실패 -1
    private final String message;
    private final T Data;

}
