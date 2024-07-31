package com.example.clientes;

import com.example.clientes.domain.client.entity.ClientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ClientesApplicationTests {

    @Test
    public void testClientEntity() {
        ClientEntity client = new ClientEntity();

        Long personId = 1L;
        String contrasena = "password123";
        Boolean estado = true;

        client.setId(personId);
        client.setContrasena(contrasena);
        client.setEstado(estado);

        assertEquals(personId, client.getId());
        assertEquals(contrasena, client.getContrasena());
        assertEquals(estado, client.getEstado());
    }
}
