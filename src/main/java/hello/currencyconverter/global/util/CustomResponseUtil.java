package hello.currencyconverter.global.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.currencyconverter.global.dto.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@Slf4j
public class CustomResponseUtil {
    public static void fail(HttpServletResponse response, String msg, HttpStatus httpStatus){
        try{
            ObjectMapper om = new ObjectMapper();
            ApiResponse<?> responseDto = new ApiResponse<>(-1, msg);
            String responseBody = om.writeValueAsString(responseDto);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(httpStatus.value());
            response.getWriter().write(responseBody);
        } catch (Exception e) {
            log.error("서버 파싱 에러 {}",e.getMessage());
        }

    }

    public static void success(HttpServletResponse response, Object dto){
        try{
            ObjectMapper om = new ObjectMapper();
            ApiResponse<?> responseDto = new ApiResponse<>(1, "Login Success",dto);
            String responseBody = om.writeValueAsString(responseDto);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().write(responseBody);
        } catch (Exception e) {
            log.error("서버 파싱 에러 {}",e.getMessage());
        }

    }
}
