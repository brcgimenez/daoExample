package org.example.refact.controller;

import org.example.refact.dao.ClienteDAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//End-points
//@RestController
//@RestMapping("/clientes")
public class ClienteController {
//    SOLID
//    S - Single Responsibility Principle
    private final ClienteDAO clienteDAO;

    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

//    @PostMapping()
    public Output salvar(Input input) {
        if (!input.nome.isEmpty()) {
            if (!input.email.isEmpty()) {
                ClienteDAO.Input inputSalvar = new ClienteDAO.Input(input.nome, input.email, input.senha, input.situacao);
                BigDecimal clienteId = this.clienteDAO.salvar(inputSalvar);
                return new Output(clienteId, 201, null);
            } else {
                return new Output(400, "Email é obrigatório.");
            }
        } else {
            return new Output(400, "Nome é obrigatório.");
        }
    }

    public OutputConsulta consultarPorId(BigDecimal id) {
        ClienteDAO.Output outputConsulta = this.clienteDAO.consultarPorId(id);
        return new OutputConsulta(outputConsulta.id(), outputConsulta.nome(), outputConsulta.email(), outputConsulta.senha(), outputConsulta.situacao());
    }

    public record Input(String nome, String email, String senha, String situacao) {
    }

    public record Output(BigDecimal id, int status, String mensagem) {
        public Output(int status, String mensagem) {
            this(null, status, mensagem);
        }
    }

    public record OutputConsulta(BigDecimal id, String nome, String email, String senha, String situacao, int status, String mensagem) {
        public OutputConsulta(BigDecimal id, String nome, String email, String senha, String situacao) {
            this(id, nome, email, senha, situacao, 200, null);
        }

        public OutputConsulta(int status, String mensagem) {
            this(null, null, null, null, null, status, mensagem);
        }
    }
}
