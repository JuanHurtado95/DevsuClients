package com.example.clientes.domain.report.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReportDTO {

    private Long numeroCuenta;
    private Double saldo;
    private String tipoCuenta;
    private LocalDate fecha;
    private String tipoMovimiento;
    private Double valor;

    public ReportDTO(Long numeroCuenta, Double saldo, String tipoCuenta, LocalDate fecha, String tipoMovimiento, Double valor) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
    }
}
