package com.example.clientes.domain.account.service;

import com.example.clientes.domain.account.entity.AccountEntity;
import com.example.clientes.domain.common.exception.AccountNoFoundException;
import com.example.clientes.domain.common.exception.NoExistClientException;
import com.example.clientes.domain.common.exception.SaldoInsuficienteException;
import com.example.clientes.domain.motion.dto.MotionDTO;
import com.example.clientes.infraestructure.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService{

    private static final String RETIRO="Retiro";
    private static final String SALDO_NO_DISPONIBLE="Saldo no disponible";
    private static final String CUENTA_NO_ENCONTRADA="Cuenta no encontrada";
    private static final String CLIENTE_NO_EXISTE="Cliente no existe";

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<AccountEntity> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<AccountEntity> getAccountByNumber(Long numberAccount) {
        return accountRepository.findById(numberAccount);
    }

    public AccountEntity createAccount(AccountEntity accountEntity) {
        if(clienteExiste(accountEntity.getPersonId())){
            return accountRepository.save(accountEntity);
        }
        throw new NoExistClientException(CLIENTE_NO_EXISTE);
    }

    public AccountEntity updateAccount(Long numberAccount, AccountEntity accountEntity) {
        if (accountRepository.existsById(numberAccount)) {
            accountEntity.setEstado(accountEntity.getEstado());
            accountEntity.setPersonId(accountEntity.getPersonId());
            accountEntity.setNumeroCuenta(numberAccount);
            return accountRepository.save(accountEntity);
        } else {
            throw new AccountNoFoundException(CUENTA_NO_ENCONTRADA);
        }
    }

    public void deleteCuenta(Long numeroCuenta) {
        accountRepository.deleteById(numeroCuenta);
    }

    @Transactional
    public AccountEntity actualizarSaldo(MotionDTO motion) {
        AccountEntity account = accountRepository.findByNumeroCuenta(motion.getCuenta());

        if (account == null) {
            throw new AccountNoFoundException(CUENTA_NO_ENCONTRADA);
        }

        double newBalance = (motion.getTipoMovimiento().equals(RETIRO))? account.getSaldo() - motion.getValor() : account.getSaldo() + motion.getValor();

        if (newBalance<0) {
            throw new SaldoInsuficienteException(SALDO_NO_DISPONIBLE);
        }

        account.setSaldo(newBalance);
        return accountRepository.save(account);
    }

    private boolean clienteExiste(Long clientId) {
        String url = "http://localhost:8080/clientes/" + clientId;
        try {
            restTemplate.getForObject(url, Void.class);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}