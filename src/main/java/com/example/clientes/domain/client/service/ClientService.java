package com.example.clientes.domain.client.service;

import com.example.clientes.domain.client.entity.ClientEntity;
import com.example.clientes.domain.common.exception.CustomerIsAlreadyRegisterException;
import com.example.clientes.infraestructure.client.adapter.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {
    private static final String CUSTOMER_IS_ALREADY_REGISTRED = "Cliente ya se encuentra registrado";

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<ClientEntity> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<ClientEntity> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public ClientEntity createClient(ClientEntity client) {
        int identification = client.getIdentificacion();
        if (clientRepository.findByIdentificacion(identification).isPresent()) {
            throw new CustomerIsAlreadyRegisterException(CUSTOMER_IS_ALREADY_REGISTRED);
        }
        return clientRepository.save(client);
    }

    @Override
    public Optional<ClientEntity> updateClient(Long id, ClientEntity client) {
        return clientRepository.findById(id).map(existingClient -> {
            existingClient.setContrasena(client.getContrasena());
            existingClient.setEstado(client.getEstado());
            return clientRepository.save(existingClient);
        });
    }

    @Override
    public boolean deleteClient(Long id) {
        return clientRepository.findById(id).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
    }
}
