package com.example.clientes.domain.account.entity;

import com.example.clientes.domain.motion.entity.MotionEntity;
import com.example.clientes.domain.report.dto.ReportDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cuentas")
@NamedNativeQuery(name = "AccountEntity.getReport",
        query = "SELECT a.numero_cuenta numeroCuenta, a.saldo , a.tipo_cuenta tipoCuenta, m.fecha, m.tipo_movimiento tipoMovimiento, m.valor " +
                "FROM cuentas as a " +
                "LEFT JOIN movimientos as m ON a.numero_cuenta = m.numero_cuenta " +
                "WHERE a.person_id = :clientId " +
                "AND (m.fecha BETWEEN :fechaInicio AND :fechaFin OR m.fecha IS NULL) ORDER BY a.numero_cuenta",
        resultSetMapping = "ReportDTO")
@SqlResultSetMapping(
        name = "ReportDTO",
        classes = @ConstructorResult(
                targetClass = ReportDTO.class,
                columns = {
                        @ColumnResult(name = "numeroCuenta", type = Long.class),
                        @ColumnResult(name = "saldo", type = Double.class),
                        @ColumnResult(name = "tipoCuenta", type = String.class),
                        @ColumnResult(name = "fecha", type = LocalDate.class),
                        @ColumnResult(name = "tipoMovimiento", type = String.class),
                        @ColumnResult(name = "valor", type = Double.class)
                }
        )
)
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_cuenta", nullable = false, columnDefinition = "BIGINT")
    private Long numeroCuenta;

    @Column(name = "person_id", nullable = false, columnDefinition = "BIGINT")
    private Long personId;

    @Column(name = "tipo_cuenta", nullable = false)
    private String tipoCuenta;

    @Column(name = "saldo", nullable = false)
    private Double saldo;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<MotionEntity> motions;

    public AccountEntity() {
        this.motions = new ArrayList<>();
    }
}