package br.com.leonardoraupp.apibancaria.config;

import br.com.leonardoraupp.apibancaria.application.*;
import br.com.leonardoraupp.apibancaria.application.service.AccountService;
import br.com.leonardoraupp.apibancaria.application.service.AuthService;
import br.com.leonardoraupp.apibancaria.application.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfig {

    @Bean
    public OpenAccountUseCase openAccountUseCase(AccountService service) {
        return new OpenAccountUseCase(service);
    }

    @Bean
    public GetBalanceUseCase getBalanceUseCase(AccountService service) {
        return new GetBalanceUseCase(service);
    }

    @Bean
    public AccountDepositUseCase accountDepositUseCase(AccountService accountService) {
        return new AccountDepositUseCase(accountService);
    }

    @Bean
    public AccountWithdrawUseCase accountWithdrawUseCase(AccountService accountService) {
        return new AccountWithdrawUseCase(accountService);
    }

    @Bean
    public AccountTransferenceUseCase accountTransferenceUseCase(AccountService accountService) {
        return new AccountTransferenceUseCase(accountService);
    }

    @Bean
    public CreateUserUseCase createUserUseCase(UserService userService) {
        return new CreateUserUseCase(userService);
    }

    @Bean
    public AuthenticateUserUseCase authenticateUserUseCase(AuthService authService) {
        return new AuthenticateUserUseCase(authService);
    }
}
