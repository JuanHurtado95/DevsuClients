package com.example.clientes;

import com.example.clientes.domain.client.entity.ClientEntity;
import com.example.clientes.domain.client.service.IClientService;
import com.example.clientes.infraestructure.client.adapter.repository.ClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientIntegrationTest {

    @Autowired
    private ClientRepository clientRepository;

    private IClientService clientService;

    @Before
    public void setUp() {
        // Limpiar la base de datos antes de cada prueba
        clientRepository.deleteAll();
    }

    @Test
    public void testCreateAndRetrieveClient() {
        // Crear una nueva entidad ClientEntity
        ClientEntity client = new ClientEntity();
        client.setIdentificacion(123456);
        client.setNombre("Juan Pérez");
        client.setGenero("Masculino");
        client.setEdad(30L);
        client.setDireccion("123 Calle Falsa");
        client.setTelefono("123-456-7890");
        client.setContrasena("password123");
        client.setEstado(true);

        // Guardar el cliente en la base de datos
        clientRepository.save(client);
        // Recuperar el cliente de la base de datos

        Optional<ClientEntity> foundClient = clientRepository.findById(client.getId());
        assertTrue(foundClient.isPresent());
        assertEquals(client.getNombre(), foundClient.get().getNombre());
    }
}