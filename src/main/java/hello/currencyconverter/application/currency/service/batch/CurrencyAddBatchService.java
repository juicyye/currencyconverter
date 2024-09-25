package hello.currencyconverter.application.currency.service.batch;

import feign.FeignException;
import feign.RetryableException;
import hello.currencyconverter.application.common.service.port.LocalDateHolder;
import hello.currencyconverter.application.common.service.port.LocalDateTimeHolder;
import hello.currencyconverter.application.currency.infrastructure.dto.CurrencyApiDto;
import hello.currencyconverter.application.currency.service.port.CurrencyClient;
import hello.currencyconverter.application.currency.service.port.CurrencyRepository;
import hello.currencyconverter.application.exchangerates.service.port.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class CurrencyAddBatchService {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final CurrencyRepository currencyRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final CurrencyClient currencyClient;
    private final LocalDateHolder localDateHolder;
    private final LocalDateTimeHolder localDateTimeHolder;

    @Bean
    public Job addCurrencyJob(){
        return new JobBuilder("addCurrencyJob", jobRepository)
                .start(addCurrencyStep())
                .build();
    }

    @Bean
    public Step addCurrencyStep(){
        return new StepBuilder("addCurrencyStep", jobRepository)
                .<CurrencyApiDto, CurrencyApiDto>chunk(10, transactionManager)
                .reader(currencyReader())
                .writer(currencyWriter())
                .faultTolerant()
                .retryLimit(3)
                .retry(RetryableException.class)
                .retry(FeignException.class)
                .retry(Exception.class)
                .backOffPolicy(backOffPolicy())
                .build();
    }

    @Bean
    public BackOffPolicy backOffPolicy(){
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(1000); // 1초 대기 후 재시도
        backOffPolicy.setMaxInterval(10000); // 최대 10초까지 대기 시간 증가
        backOffPolicy.setMultiplier(2.0); // 재시도 시 대기 시간이 두 배로 증가
        return backOffPolicy;
    }

    @Bean
    public ItemStreamReader<CurrencyApiDto> currencyReader(){
        return new CurrencyReader(currencyClient, localDateHolder);
    }

    @Bean
    public ItemWriter<CurrencyApiDto> currencyWriter(){
        return new CurrencyWriter(localDateTimeHolder, currencyRepository, exchangeRateRepository);
    }




}
