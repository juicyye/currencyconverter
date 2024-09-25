package hello.currencyconverter.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.currencyconverter.application.exchangerates.controller.ExchangeRateController;
import hello.currencyconverter.application.exchangerates.controller.port.ExchangeRateService;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;



@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationConTestSupport {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper mapper;

    @MockBean
    protected ExchangeRateService exchangeRateService;
}
