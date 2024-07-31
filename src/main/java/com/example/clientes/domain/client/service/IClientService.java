package com.example.clientes.domain.client.service;

import com.example.clientes.domain.client.entity.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface IClientService {

    List<ClientEntity> getAllClients();
    Optional<ClientEntity> getClientById(Long id);
    ClientEntity createClient(ClientEntity client);
    Optional<ClientEntity> updateClient(Long id, ClientEntity client);
    boolean deleteClient(Long id);
}
