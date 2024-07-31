package com.example.clientes.infraestructure.person.controller;

import com.example.clientes.domain.person.entity.PersonEntity;
import com.example.clientes.domain.person.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personas")
public class PersonController {
    @Autowired
    private IPersonService personService;

    @GetMapping
    public List<PersonEntity> findAll() {
        return personService.findAll();
    }

    @PostMapping
    public PersonEntity save(@RequestBody PersonEntity person) {
        return personService.save(person);
    }

    @PutMapping
    public PersonEntity update(@RequestBody PersonEntity person) {
        return personService.save(person);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<PersonEntity> findById(@PathVariable Long id) {
        return personService.findById(id);
    }
}
