package com.example.clientes.infraestructure.client.adapter.repository;

import com.example.clientes.domain.client.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByIdentificacion(int identificacion);
}
