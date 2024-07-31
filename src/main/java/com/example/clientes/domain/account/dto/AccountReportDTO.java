package com.example.clientes.domain.account.dto;

import com.example.clientes.domain.motion.dto.MotionReportDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class AccountReportDTO {

    private Long numeroCuenta;
    private Double saldo;
    private String tipoCuenta;
    private List<MotionReportDTO> movimientos;
}