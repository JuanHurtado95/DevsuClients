package com.example.clientes.domain.motion.service;

import com.example.clientes.domain.motion.dto.MotionDTO;
import com.example.clientes.domain.motion.entity.MotionEntity;

import java.util.List;
import java.util.Optional;

public interface IMotionService {

    public List<MotionEntity> getAllMotions();

    public Optional<MotionEntity> getMotionById(Long id);

    public MotionEntity createMotion(MotionDTO motion);

    public MotionEntity updateMotion(Long id, MotionEntity motionDetails);

    public void deleteMotion(Long id);
}
