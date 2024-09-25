package hello.currencyconverter.global.security.jwt;

import hello.currencyconverter.application.IntegrationInfraTestSupport;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JwtProcessTest extends IntegrationInfraTestSupport {

    @Test
    @DisplayName("HS256 알고리즘을 통해 토큰이 생성된다")
    void createToken() throws Exception {
        // given
        String jwt = jwtProcess.createJwt("hi@naver.com", "ROLE_USER", 10L);

        // when
        System.out.println("jwt = " + jwt);


        // then
        assertThat(jwt).isNotNull();
    }

    @Test
    @DisplayName("토큰 유효기간을 확인한다")
    void verifyToken() throws Exception {
        // given
        String jwt = jwtProcess.createJwt("hi@naver.com", "ROLE_USER", 1L);

        // when
        Thread.sleep(10L);
        assertThatThrownBy(() -> jwtProcess.isExpired(jwt)).isInstanceOf(ExpiredJwtException.class);

    }




}