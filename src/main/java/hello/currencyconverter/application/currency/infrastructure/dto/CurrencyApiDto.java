package hello.currencyconverter.application.currency.infrastructure.dto;

import lombok.Data;

@Data
public class CurrencyApiDto {
    private int result; // 1 성공, 2 Data코드 오류, 3 인증코드 오류, 4. 일일제한 횟수 마감
    private String cur_unit; // 통화코드
    private String cur_nm; // 국가, 통화명
    private String deal_bas_r; // 매매 기준율(환율)
}
