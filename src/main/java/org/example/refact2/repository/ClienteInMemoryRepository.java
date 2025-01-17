package org.example.refact2.repository;

import org.example.refact2.model.Cliente;
import org.example.refact2.services.ClienteRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Adapter - Em memoria
public class ClienteInMemoryRepository implements ClienteRepository {

    private final List<Cliente> clientes;

    public ClienteInMemoryRepository() {
        this.clientes = new ArrayList<>();
    }

    public Cliente salvar(Cliente cliente) throws SQLException {
        cliente.setId(BigDecimal.valueOf(this.clientes.size() + 1));
        this.clientes.add(cliente);
        return cliente;
    }

    @Override
    public Optional<Cliente> consultarPorId(BigDecimal id) {
        return this.clientes.stream().filter(cliente -> cliente.getId().equals(id)).findFirst();
    }

}
