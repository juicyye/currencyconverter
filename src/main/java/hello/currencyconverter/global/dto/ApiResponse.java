package hello.currencyconverter.global.dto;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private final Integer code;  // 성공 1, 실패 -1
    private final String message;
    private final T Data;

    public ApiResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
        Data = null;
    }

    public ApiResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        Data = data;
    }
}
