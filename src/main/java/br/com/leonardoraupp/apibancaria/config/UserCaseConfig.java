package br.com.leonardoraupp.apibancaria.config;

import br.com.leonardoraupp.apibancaria.application.OpenAccountUseCase;
import br.com.leonardoraupp.apibancaria.application.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserCaseConfig {

    @Bean
    public OpenAccountUseCase openAccountUseCase(AccountService service) {
        return new OpenAccountUseCase(service);
    }
}
