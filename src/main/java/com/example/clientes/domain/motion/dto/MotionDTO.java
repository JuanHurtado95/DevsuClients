package com.example.clientes.domain.motion.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MotionDTO {

    private Long cuenta;
    private LocalDate fecha;
    private String tipoMovimiento;
    private double valor;
}
