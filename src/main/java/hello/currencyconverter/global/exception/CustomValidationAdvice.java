package hello.currencyconverter.global.exception;

import hello.currencyconverter.global.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class CustomValidationAdvice {

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> handleCustomApiException(CustomApiException e) {
        log.error("[CustomApiException]", e);
        return new ResponseEntity<>(new ApiResponse<>(-1,e.getMessage()), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e) {
        log.error("[CustomApiException]", e);
        return new ResponseEntity<>(new ApiResponse<>(-1,e.getMessage()), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> bindException(BindException e) {
        String defaultMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error("[CustomApiException] {}", e.getMessage());
        return new ResponseEntity<>(new ApiResponse<>(-1,defaultMessage), HttpStatus.BAD_REQUEST);

    }
}
