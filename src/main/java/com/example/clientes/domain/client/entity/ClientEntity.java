package com.example.clientes.domain.client.entity;

import com.example.clientes.domain.person.entity.PersonEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(referencedColumnName="person_id")
public class ClientEntity extends PersonEntity {

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private Boolean estado;
}
