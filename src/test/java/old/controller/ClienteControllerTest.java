package old.controller;

import org.example.old.controller.ClienteController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

//Teste integrado
class ClienteControllerTest {

    @Test
    void deveSalvarUmCliente() {
        // Given - Arrange
        var input = new ClienteController.Input("Bruno", "bruno@gmail.com", "123456", "ATIVO");
        // When - Act
        var clienteController = new ClienteController();
        var output = clienteController.salvar(input);
        // Then - Assertion
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals(201, output.status());
        assertNull(output.mensagem()); //Se tiver mensagem, deu ruim.
    }

    @Test
    void naoDeveSalvarUmClienteSemNome() {
        // Given - Arrange
        var input = new ClienteController.Input("", "bruno@gmail.com", "123456", "ATIVO");
        // When - Act
        ClienteController clienteController = new ClienteController();
        ClienteController.Output output = clienteController.salvar(input);
        // Then - Assertion
        assertNotNull(output);
        assertEquals(400, output.status());
        assertEquals("Nome é obrigatório.", output.mensagem());
    }

    @Test
    void naoDeveSalvarUmClienteSemEmail() {
        // Given - Arrange
        var input = new ClienteController.Input("Bruno", "", "123456", "ATIVO");
        // When - Act
        ClienteController clienteController = new ClienteController();
        ClienteController.Output output = clienteController.salvar(input);
        // Then - Assertion
        assertNotNull(output);
        assertEquals(400, output.status());
        assertEquals("Email é obrigatório.", output.mensagem());
    }

    @Test
    void deveConsultarOsDadosDeUmCliente() {
        // Given - Arrange
        var inputSalvar = new ClienteController.Input("Bruno", "bruno@gmail.com", "123456", "ATIVO");
        // When - Act
        var clienteController = new ClienteController();
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


//MVC - Model(entidades de dominio), View(Tela), Controller(Controloador da tela)
//Entities ou Domains

// JSP e JSF
//cliente.jsf (View) -> ClienteCOntroller.java (Controller) -> Cliente.java (Model)
//                                                          -> Repository (Repository)