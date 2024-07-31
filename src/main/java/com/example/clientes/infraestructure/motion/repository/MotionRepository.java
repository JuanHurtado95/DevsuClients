package com.example.clientes.infraestructure.motion.repository;


import com.example.clientes.domain.motion.entity.MotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotionRepository extends JpaRepository<MotionEntity, Long> {

    @Query("SELECT a from MotionEntity a where a.account.numeroCuenta = :clientId ")
    List<MotionEntity> getReport(@Param("clientId") Long clientId);

}