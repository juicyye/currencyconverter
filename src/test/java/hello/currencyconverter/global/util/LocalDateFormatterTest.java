package hello.currencyconverter.global.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LocalDateFormatterTest {

    @Test
    @DisplayName("LocalDate를 api에 맞는 형식으로 포매팅한다")
    void formatter() throws Exception {
        // given
        LocalDate localDate = LocalDate.of(2024, 9, 25);

        // when
        String formatter = LocalDateFormatter.formatter(localDate);

        // then
        assertThat(formatter).isEqualTo("20240925");
    }

}