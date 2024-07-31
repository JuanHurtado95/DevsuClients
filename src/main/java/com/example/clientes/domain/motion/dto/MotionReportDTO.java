package com.example.clientes.domain.motion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class MotionReportDTO {

    private LocalDate fecha;
    private String tipoMovimiento;
    private double valor;
}

