package hello.currencyconverter.global.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.currencyconverter.application.user.controller.request.LoginReqDto;
import hello.currencyconverter.application.user.controller.response.UserResponse;
import hello.currencyconverter.global.dto.ApiResponse;
import hello.currencyconverter.global.dto.CustomUserDetails;
import hello.currencyconverter.global.util.CustomResponseUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtProcess jwtProcess;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtProcess jwtProcess) {
        super(authenticationManager);
        setFilterProcessesUrl("/api/login");
        this.authenticationManager = authenticationManager;
        this.jwtProcess = jwtProcess;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            LoginReqDto loginReqDto = mapper.readValue(request.getInputStream(), LoginReqDto.class);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginReqDto.getEmail(), loginReqDto.getPassword());
            return authenticationManager.authenticate(authToken);
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        CustomUserDetails principal = (CustomUserDetails) authResult.getPrincipal();
        String email = principal.getUsername();
        String role = principal.getUser().getRole().name();
        String token = jwtProcess.createJwt(email, role, JwtVO.EXPIRATION_TIME);
        response.addHeader(JwtVO.HEADER, JwtVO.TOKEN_PREFIX + token);

        CustomResponseUtil.success(response,new UserResponse(email));

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        CustomResponseUtil.fail(response,"Login Fail", HttpStatus.UNAUTHORIZED);
    }
}
