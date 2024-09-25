package hello.currencyconverter.global.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.currencyconverter.application.IntegrationConTestSupport;
import hello.currencyconverter.application.IntegrationInfraTestSupport;
import hello.currencyconverter.application.user.controller.request.LoginReqDto;
import hello.currencyconverter.application.user.domain.Role;
import hello.currencyconverter.application.user.infrastructure.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;

class JwtAuthenticationFilterTest extends IntegrationConTestSupport {

    
    @Test
    @DisplayName("DB에 있는 유저와 로그인의 정보가 같으면 jwt를 발급해준다")
    void successfulAuthentication_test() throws Exception {
        // given
        userRepository.save(createUserEntity("123@naver.com", Role.ROLE_USER, "{noop}1234"));
        LoginReqDto loginReqDto = new LoginReqDto();
        loginReqDto.setEmail("123@naver.com");
        loginReqDto.setPassword("1234");

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                        .content(mapper.writeValueAsString(loginReqDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());

        String token = resultActions.andReturn().getResponse().getHeader(JwtVO.HEADER);
        System.out.println("token = " + token);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Login Success"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.email").value("123@naver.com"))
        ;

        Assertions.assertTrue(token.startsWith(JwtVO.TOKEN_PREFIX));
    }

    @Test
    @DisplayName("DB정보와 로그인 정보가 다르면 오류가 나타난다")
    void unsuccessfulAuthentication_test() throws Exception {
        // given
        userRepository.save(createUserEntity("123@naver.com", Role.ROLE_USER, "{noop}1234"));
        LoginReqDto loginReqDto = new LoginReqDto();
        loginReqDto.setEmail("1234@naver.com");
        loginReqDto.setPassword("1234");

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/login")
                        .content(mapper.writeValueAsString(loginReqDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());


        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(-1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Login Fail"));
    }

    private UserEntity createUserEntity(String mail, Role role, String password) {
        return userRepository.save(UserEntity.builder()
                .email(mail)
                .role(role)
                .password(password)
                .build());
    }


}