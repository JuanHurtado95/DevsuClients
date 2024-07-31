package com.example.clientes.infraestructure.motion.controller;

import com.example.clientes.domain.motion.dto.MotionDTO;
import com.example.clientes.domain.motion.entity.MotionEntity;
import com.example.clientes.domain.motion.service.IMotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimientos")
public class MotionController {

    @Autowired
    private IMotionService motionService;

    @GetMapping
    public List<MotionEntity> getAllMotions() {
        return motionService.getAllMotions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotionEntity> getMotionById(@PathVariable Long id) {
        Optional<MotionEntity> motion = motionService.getMotionById(id);
        return motion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createMotion(@RequestBody MotionDTO motion) {
        MotionEntity createdMotion = motionService.createMotion(motion);
        return ResponseEntity.ok(createdMotion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotionEntity> updateMotion(@PathVariable Long id, @RequestBody MotionEntity motionDetails) {
            MotionEntity updatedMotion = motionService.updateMotion(id, motionDetails);
            return ResponseEntity.ok(updatedMotion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMotion(@PathVariable Long id) {
            motionService.deleteMotion(id);
            return ResponseEntity.noContent().build();
    }
}
