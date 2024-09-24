package hello.currencyconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CurrencyconverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyconverterApplication.class, args);
	}

}
