package com.example.clientes.infraestructure.account.controller;

import com.example.clientes.domain.account.entity.AccountEntity;
import com.example.clientes.domain.account.service.IAccountService;
import com.example.clientes.domain.common.exception.AccountNoFoundException;
import com.example.clientes.domain.motion.dto.MotionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class AccountController {

    private static final String ACOUNT_NO_FOUNT= "Cuenta no encontrada";
    private static final String SUCCESSFUL_MOVEMENT= "Movimiento existo";



    @Autowired
    private IAccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountEntity>> getAllAccounts() {
        List<AccountEntity> cuentas = accountService.getAllAccounts();
        return ResponseEntity.ok(cuentas);
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<AccountEntity> getCuentaByNumero(@PathVariable Long numeroCuenta) {
        AccountEntity account = accountService.getAccountByNumber(numeroCuenta)
                .orElseThrow(() -> new AccountNoFoundException(ACOUNT_NO_FOUNT));;
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<AccountEntity> createCuenta(@RequestBody AccountEntity AccountEntity) {
        AccountEntity newAccount = accountService.createAccount(AccountEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
    }

    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<AccountEntity> updateCuenta(@PathVariable Long accountNumber, @RequestBody AccountEntity AccountEntity) {
        AccountEntity updateAccount = accountService.updateAccount(accountNumber, AccountEntity);
        return ResponseEntity.ok(updateAccount);
    }

    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long accountNumber) {
        accountService.deleteCuenta(accountNumber);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/movimiento")
    public ResponseEntity<String> realizarMovimiento(@RequestBody MotionDTO motion) {
        accountService.actualizarSaldo(motion);
        return ResponseEntity.ok(SUCCESSFUL_MOVEMENT);
    }
}
