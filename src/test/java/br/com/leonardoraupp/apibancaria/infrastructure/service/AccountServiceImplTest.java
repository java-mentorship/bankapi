package br.com.leonardoraupp.apibancaria.infrastructure.service;

import static org.junit.jupiter.api.Assertions.*;

import br.com.leonardoraupp.apibancaria.application.exception.InvalidAccountException;
import br.com.leonardoraupp.apibancaria.application.exception.InvalidHolderException;
import br.com.leonardoraupp.apibancaria.application.service.AccountService;
import br.com.leonardoraupp.apibancaria.application.service.HolderService;
import br.com.leonardoraupp.apibancaria.domain.Account;
import br.com.leonardoraupp.apibancaria.domain.CheckingAccount;
import br.com.leonardoraupp.apibancaria.domain.Holder;
import br.com.leonardoraupp.apibancaria.infrastructure.entity.AccountEntity;
import br.com.leonardoraupp.apibancaria.infrastructure.entity.HolderEntity;
import br.com.leonardoraupp.apibancaria.infrastructure.repository.AccountRepository;
import br.com.leonardoraupp.apibancaria.infrastructure.repository.HolderRepository;
import br.com.leonardoraupp.apibancaria.utility.AccountMapper;
import br.com.leonardoraupp.apibancaria.utility.HolderMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {
    @Mock
    private HolderRepository holderRepository;
    @Mock
    private AccountRepository accountRepository;
    private AccountService accountService;
    private HolderService holderService;

    @BeforeEach
    public void setUp() {
        holderService = new HolderServiceImpl(holderRepository);
        accountService = new AccountServiceImpl(accountRepository, null, holderService);
    }

    @Test
    public void testCreateAccountSuccessHolderExists() {
//        preparação
        Account accountObject = createAccountObject();
        HolderEntity holderEntity = HolderMapper.toEntity(accountObject.getHolder());
        AccountEntity accountEntity = AccountMapper.toEntity(accountObject);
        when(holderRepository.findByCpf(any(String.class))).thenReturn(Optional.of(holderEntity));
        when(accountRepository.save(any(AccountEntity.class))).thenReturn(accountEntity);
//        execução
        Account createdAccount = null;
        try {
            createdAccount = accountService.createAccount(accountObject);
        } catch (Exception e) {
            fail(e.getMessage());
        }
//        validação
        assertNotNull(createdAccount);
        assertEquals(1972, createdAccount.getAgency());
        assertEquals(21250, createdAccount.getNumber());
        assertEquals("03402123003", createdAccount.getHolder().getCpf());
    }

    public void testCreateAccountSuccessHolderNotExists() {
    }

    private Account createAccountObject() {
        Holder holderObject = createHolderObject();
        return new CheckingAccount(holderObject, 1972, 21250);
    }

    private Holder createHolderObject() {
        return new Holder("Fulano", "Beltrano",
                "03402123003", "fulano@mail.com", LocalDate.now());
    }
}
