package org.example.refact2.services;

import org.example.refact2.controller.ClienteRequest;
import org.example.refact2.model.Cliente;

import java.math.BigDecimal;

public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public BigDecimal salvar(ClienteRequest input) throws Exception {
        if (!input.nome().isEmpty()) {
            if (!input.email().isEmpty()) {
                Cliente cliente = new Cliente(input.nome(), input.email(), input.senha(), input.situacao());
                cliente = this.clienteRepository.salvar(cliente);
                return cliente.getId();
            } else {
                throw new Exception("Email é obrigatório.");
            }
        } else {
            throw new Exception("Nome é obrigatório.");
        }
    }
}

//Usecase -> service
//Infra -> Repository
