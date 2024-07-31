package com.example.clientes.domain.motion.service;

import com.example.clientes.domain.account.entity.AccountEntity;
import com.example.clientes.domain.account.service.AccountService;
import com.example.clientes.domain.motion.dto.MotionDTO;
import com.example.clientes.domain.motion.entity.MotionEntity;
import com.example.clientes.infraestructure.motion.repository.MotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MotionService implements IMotionService{

    @Autowired
    private MotionRepository motionRepository;

    @Autowired
    private AccountService accountService;

    public List<MotionEntity> getAllMotions() {
        return motionRepository.findAll();
    }

    public Optional<MotionEntity> getMotionById(Long id) {
        return motionRepository.findById(id);
    }

    @Transactional
    public MotionEntity createMotion(MotionDTO motion){
        AccountEntity accountEntity = accountService.actualizarSaldo(motion);
        MotionEntity motionEntity = new MotionEntity();
        motionEntity.setAccount(accountEntity);
        motionEntity.setTipoMovimiento(motion.getTipoMovimiento());
        motionEntity.setFecha(motion.getFecha());
        motionEntity.setValor(motion.getValor());
        return motionRepository.save(motionEntity);
    }

    public MotionEntity updateMotion(Long id, MotionEntity motionDetails) {
        MotionEntity motion = motionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        motion.setAccount(motionDetails.getAccount());
        motion.setFecha(motionDetails.getFecha());
        motion.setTipoMovimiento(motionDetails.getTipoMovimiento());
        motion.setValor(motionDetails.getValor());
        return motionRepository.save(motion);
    }

    public void deleteMotion(Long id) {
        MotionEntity motion = motionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        motionRepository.delete(motion);
    }
}