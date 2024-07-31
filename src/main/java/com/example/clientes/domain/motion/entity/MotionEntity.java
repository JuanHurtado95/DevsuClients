package com.example.clientes.domain.motion.entity;
import com.example.clientes.domain.account.entity.AccountEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "movimientos")
public class MotionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "numero_cuenta", nullable = false)
    @JsonIgnore
    private AccountEntity account;

    @Column(name = "fecha", nullable = false, columnDefinition = "DATETIME")
    private LocalDate fecha;

    @Column(name = "tipo_movimiento", nullable = false)
    private String tipoMovimiento;

    @Column(name = "valor", nullable = false)
    private double valor;
}