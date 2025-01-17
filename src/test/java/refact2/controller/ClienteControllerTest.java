package refact2.controller;

import org.example.refact2.configs.Database;
import org.example.refact2.configs.H2DatabaseConnection;
import org.example.refact2.controller.ClienteController;
import org.example.refact2.controller.ClienteRequest;
import org.example.refact2.controller.ClienteResponse;
import org.example.refact2.repository.ClienteDatabaseRepository;
import org.example.refact2.repository.ClienteInMemoryRepository;
import org.example.refact2.services.ClienteRepository;
import org.example.refact2.services.ClienteService;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClienteControllerTest {

    @Test
    void deveSalvarUmCliente() throws SQLException {
        // Given - Arrange
        var input = new ClienteRequest("Bruno", "bruno@gmail.com", "123456", "ATIVO");
        // When - Act
        Database connection = new H2DatabaseConnection();
        ClienteRepository clienteRepository = new ClienteDatabaseRepository(connection);
        ClienteService clienteService = new ClienteService(clienteRepository);
        ClienteController clienteController = new ClienteController(clienteService);
        ClienteResponse output = clienteController.salvar(input);
        // Then - Assertion
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals(201, output.status());
        assertNull(output.mensagem()); //Se tiver mensagem, deu ruim.
    }

    @Test
    void deveSalvarUmClienteInMemory() throws SQLException {
        // Given - Arrange
        var input = new ClienteRequest("Bruno", "bruno@gmail.com", "123456", "ATIVO");
        // When - Act
        ClienteRepository clienteRepository = new ClienteInMemoryRepository();
        ClienteService clienteService = new ClienteService(clienteRepository);
        ClienteController clienteController = new ClienteController(clienteService);
        ClienteResponse output = clienteController.salvar(input);
        // Then - Assertion
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals(201, output.status());
        assertNull(output.mensagem()); //Se tiver mensagem, deu ruim.
    }

//
//    @Test
//    void deveConsultarOsDadosDeUmCliente() throws SQLException {
//        // Given - Arrange
//        var inputSalvar = new ClienteController.Input("Bruno", "bruno@gmail.com", "123456", "ATIVO");
//        // When - Act
//        DatabaseConnection connection = new DatabaseConnection();
//        Database database = new Database(connection);
//        ClienteDAO clienteDao = new ClienteDAO(database);
//        ClienteController clienteController = new ClienteController(clienteDao);
//        var outputSalvar = clienteController.salvar(inputSalvar);
//        var outputConsultar = clienteController.consultarPorId(outputSalvar.id());
//        // Then - Assertion
//        assertNotNull(outputConsultar);
//        assertEquals(200, outputConsultar.status());
//        assertNull(outputConsultar.mensagem()); //Se tiver mensagem, deu ruim.
//        assertEquals(outputSalvar.id(), outputConsultar.id());
//        assertEquals("Bruno", outputConsultar.nome());
//        assertEquals("bruno@gmail.com", outputConsultar.email());
//        assertEquals("123456", outputConsultar.senha());
//        assertEquals("ATIVO", outputConsultar.situacao());
//    }
}
