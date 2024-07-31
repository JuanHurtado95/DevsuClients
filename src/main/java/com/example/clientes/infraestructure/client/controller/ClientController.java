package com.example.clientes.infraestructure.client.controller;

import com.example.clientes.domain.client.entity.ClientEntity;
import com.example.clientes.domain.client.service.IClientService;
import com.example.clientes.domain.common.exception.UnregisteredCustomereException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    private static final String UNREGISTERED_CUSTOMER= "El cliente no se encuentra registrado";

    @Autowired
    private IClientService clientService;

    @GetMapping
    public List<ClientEntity> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getClientById(@PathVariable Long id) {
        ClientEntity client = clientService.getClientById(id)
                .orElseThrow(() -> new UnregisteredCustomereException(UNREGISTERED_CUSTOMER));
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<ClientEntity> createClient(@RequestBody ClientEntity client) {
        ClientEntity createdClient = clientService.createClient(client);
        return ResponseEntity.ok(createdClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientEntity> updateClient(@PathVariable Long id, @RequestBody ClientEntity client) {
        Optional<ClientEntity> updatedClient = clientService.updateClient(id, client);
        return updatedClient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        boolean deleted = clientService.deleteClient(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            throw new UnregisteredCustomereException(UNREGISTERED_CUSTOMER);
        }
    }
}
