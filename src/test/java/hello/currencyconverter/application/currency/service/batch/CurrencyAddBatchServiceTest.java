package hello.currencyconverter.application.currency.service.batch;

import hello.currencyconverter.application.currency.infrastructure.CurrencyEntity;
import hello.currencyconverter.application.currency.infrastructure.CurrencyJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringBatchTest
class CurrencyAddBatchServiceTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    private CurrencyJpaRepository currencyJpaRepository;


    @Test
    @DisplayName("환율정보를 가져오면 배치를 통해 RDB에 저장을 한다")
    void CurrencyAddBatchTest() throws Exception {
        // given
        currencyJpaRepository.save(createCurrencyEntity("원화", "krw", "12"));
        JobParameters jobParameters = jobLauncherTestUtils.getUniqueJobParameters();

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        // then
        assertThat(jobExecution.getStatus()).isEqualByComparingTo(BatchStatus.COMPLETED);
    }

    private static CurrencyEntity createCurrencyEntity(String name, String code, String symbol) {
        return CurrencyEntity.builder()
                .name(name)
                .code(code)
                .symbol(symbol)
                .build();
    }
}