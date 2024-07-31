package com.example.clientes.domain.report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientReportDTO {

    private Long id;
    private int identificacion;
    private String nombre;
    private String genero;
    private Long edad;
    private String direccion;
    private String telefono;
    private String contrasena;
    private Boolean estado;
}
