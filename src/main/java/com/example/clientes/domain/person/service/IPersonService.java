package com.example.clientes.domain.person.service;

import com.example.clientes.domain.person.entity.PersonEntity;

import java.util.List;
import java.util.Optional;

public interface IPersonService {

    List<PersonEntity> findAll();

    PersonEntity save(PersonEntity person);

    void delete(Long id);

    Optional<PersonEntity> findById(Long id);
}
