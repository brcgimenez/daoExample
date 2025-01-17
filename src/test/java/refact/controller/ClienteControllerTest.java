package refact.controller;

import org.example.refact.controller.ClienteController;
import org.example.refact.dao.ClienteDAO;
import org.example.refact.dao.Database;
import org.example.refact.dao.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClienteControllerTest {
    @Test
    void deveSalvarUmCliente() throws SQLException {
        // Given - Arrange
        var input = new ClienteController.Input("Bruno", "bruno@gmail.com", "123456", "ATIVO");
        // When - Act
        DatabaseConnection connection = new DatabaseConnection();
        Database database = new Database(connection);
        ClienteDAO clienteDao = new ClienteDAO(database);
        ClienteController clienteController = new ClienteController(clienteDao);
        ClienteController.Output output = clienteController.salvar(input);
        // Then - Assertion
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals(201, output.status());
        assertNull(output.mensagem()); //Se tiver mensagem, deu ruim.
    }


    @Test
    void deveConsultarOsDadosDeUmCliente() throws SQLException {
        // Given - Arrange
        var inputSalvar = new ClienteController.Input("Bruno", "bruno@gmail.com", "123456", "ATIVO");
        // When - Act
        DatabaseConnection connection = new DatabaseConnection();
        Database database = new Database(connection);
        ClienteDAO clienteDao = new ClienteDAO(database);
        ClienteController clienteController = new ClienteController(clienteDao);
        var outputSalvar = clienteController.salvar(inputSalvar);
        var outputConsultar = clienteController.consultarPorId(outputSalvar.id());
        // Then - Assertion
        assertNotNull(outputConsultar);
        assertEquals(200, outputConsultar.status());
        assertNull(outputConsultar.mensagem()); //Se tiver mensagem, deu ruim.
        assertEquals(outputSalvar.id(), outputConsultar.id());
        assertEquals("Bruno", outputConsultar.nome());
        assertEquals("bruno@gmail.com", outputConsultar.email());
        assertEquals("123456", outputConsultar.senha());
        assertEquals("ATIVO", outputConsultar.situacao());
    }
}
