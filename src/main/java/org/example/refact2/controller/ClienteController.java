package org.example.refact2.controller;

import org.example.refact2.services.ClienteService;

import java.math.BigDecimal;

//Endpoints - API
public class ClienteController {

    public final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

//    @PostMapping()
    public ClienteResponse salvar(ClienteRequest clienteRequest) {
        try {
            BigDecimal clienteId = this.clienteService.salvar(clienteRequest);
            return new ClienteResponse(clienteId);
        } catch (Exception e) {
            return new ClienteResponse(400, e.getMessage());
        }
    }

//    public OutputConsulta consultarPorId(BigDecimal id) {
//        ClienteDAO.Output outputConsulta = this.clienteDAO.consultarPorId(id);
//        return new OutputConsulta(outputConsulta.id(), outputConsulta.nome(), outputConsulta.email(), outputConsulta.senha(), outputConsulta.situacao());
//    }




//
//    public record OutputConsulta(BigDecimal id, String nome, String email, String senha, String situacao, int status, String mensagem) {
//        public OutputConsulta(BigDecimal id, String nome, String email, String senha, String situacao) {
//            this(id, nome, email, senha, situacao, 200, null);
//        }
//
//        public OutputConsulta(int status, String mensagem) {
//            this(null, null, null, null, null, status, mensagem);
//        }
//    }
}
