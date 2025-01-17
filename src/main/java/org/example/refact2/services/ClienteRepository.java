package org.example.refact2.services;

import org.example.refact2.model.Cliente;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

//Ports
public interface ClienteRepository {
    Cliente salvar(Cliente cliente) throws SQLException;
    Optional<Cliente> consultarPorId(BigDecimal id);
}
