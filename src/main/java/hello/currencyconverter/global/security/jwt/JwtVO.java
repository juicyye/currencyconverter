package hello.currencyconverter.global.security.jwt;

public interface JwtVO {
    String HEADER = "Authorization";
    String TOKEN_PREFIX = "Bearer ";
    Long EXPIRATION_TIME = 1000 * 60 * 60 * 60 * 24 * 7L; // 일주일
}
