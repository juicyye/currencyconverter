package hello.currencyconverter.application.currency.service.batch;

import hello.currencyconverter.application.common.service.port.LocalDateHolder;
import hello.currencyconverter.application.common.service.port.LocalDateTimeHolder;
import hello.currencyconverter.application.currency.domain.Currency;
import hello.currencyconverter.application.currency.infrastructure.dto.CurrencyApiDto;
import hello.currencyconverter.application.currency.service.port.CurrencyClient;
import hello.currencyconverter.application.currency.service.port.CurrencyRepository;
import hello.currencyconverter.application.exchangerates.domain.ExchangeRates;
import hello.currencyconverter.application.exchangerates.service.port.ExchangeRateRepository;
import hello.currencyconverter.global.exception.CustomApiException;
import hello.currencyconverter.global.util.Constraint;
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
                .build();
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
