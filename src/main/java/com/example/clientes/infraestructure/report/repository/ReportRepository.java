package com.example.clientes.infraestructure.report.repository;

import com.example.clientes.domain.account.entity.AccountEntity;
import com.example.clientes.domain.report.dto.ReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<AccountEntity, Long> {

    @Query("SELECT new com.example.clientes.domain.report.dto.ReportDTO(a.numeroCuenta, a.saldo , a.tipoCuenta, m.fecha, m.tipoMovimiento, m.valor) " +
            "FROM AccountEntity a " +
            "LEFT JOIN MotionEntity m ON a.numeroCuenta = m.account.numeroCuenta " +
            "WHERE a.personId = :clientId " +
            "AND (m.fecha BETWEEN :fechaInicio AND :fechaFin OR m.fecha IS NULL) ORDER BY a.numeroCuenta")
    List<ReportDTO> getReport(@Param("clientId") Long clientId,
                            @Param("fechaInicio") LocalDate fechaInicio,
                            @Param("fechaFin") LocalDate fechaFin);
}