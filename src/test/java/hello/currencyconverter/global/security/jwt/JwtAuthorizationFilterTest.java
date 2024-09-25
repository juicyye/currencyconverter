package hello.currencyconverter.global.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.currencyconverter.application.IntegrationConTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

class JwtAuthorizationFilterTest extends IntegrationConTestSupport {


    @Test
    @DisplayName("JWT값이 올바르면 권한있는 엔드포인트에도 갈 수 있다")
    void authorization_test() throws Exception {
        // given
        String jwt = jwtProcess.createJwt("hi@naver.com", "ROLE_USER", JwtVO.EXPIRATION_TIME);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/user/api/123")
                        .header(JwtVO.HEADER, JwtVO.TOKEN_PREFIX + jwt))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        // then
    }

    @Test
    @DisplayName("JWT값이 올바르더라도 유저권한은 어드민권한 엔드포인트로 갈 수 없다")
    void authorization_Admin_test() throws Exception {
        // given
        String jwt = jwtProcess.createJwt("hi@naver.com", "ROLE_USER", JwtVO.EXPIRATION_TIME);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/api/123")
                        .header(JwtVO.HEADER, JwtVO.TOKEN_PREFIX + jwt))
                .andExpect(MockMvcResultMatchers.status().isForbidden());

        // then
    }

    @Test
    @DisplayName("JWT값이 올바르지 않다면 권한이 설정되어 있는 엔드포인트에 갈 수 없다")
    void authorization_role_test() throws Exception {
        // given
        String jwt = jwtProcess.createJwt("hi@naver.com", "ROLE_USER", JwtVO.EXPIRATION_TIME);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/api/123"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());

        // then
    }
}