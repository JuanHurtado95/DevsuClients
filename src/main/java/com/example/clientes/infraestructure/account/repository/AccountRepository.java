package com.example.clientes.infraestructure.account.repository;

import com.example.clientes.domain.account.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByNumeroCuenta(Long numeroCuenta);

    boolean existsById(Long numeroCuenta);
}
