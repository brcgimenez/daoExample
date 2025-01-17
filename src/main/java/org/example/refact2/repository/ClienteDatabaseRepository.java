package org.example.refact2.repository;

import org.example.refact2.configs.Database;
import org.example.refact2.model.Cliente;
import org.example.refact2.services.ClienteRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

//Adapter - Postgres
public class ClienteDatabaseRepository implements ClienteRepository {

    private final Database databaseConnection;

    public ClienteDatabaseRepository(Database databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public Cliente salvar(Cliente cliente) throws SQLException {
        Connection con = this.databaseConnection.getConnection();
        PreparedStatement statement = con.prepareStatement("select max(id) from cliente");
        ResultSet resultSet = statement.executeQuery();
        int id = 0;
        if (resultSet.next()) {
            id = resultSet.getInt(1);
        }
        statement.close();

        id ++;

        String insertQuery = "insert into cliente (id, nome, email, senha, ie_situacao) values (?,?,?,?,?)";
        statement = con.prepareStatement(insertQuery);
        statement.setBigDecimal(1, BigDecimal.valueOf(id));
        statement.setString(2, cliente.getNome());
        statement.setString(3, cliente.getEmail());
        statement.setString(4, cliente.getSenha());
        statement.setString(5, cliente.getSituacao());

        statement.execute();
        statement.close();

        cliente.setId(BigDecimal.valueOf(id));
        return cliente;
    }

    @Override
    public Optional<Cliente> consultarPorId(BigDecimal id) {
        return Optional.empty();
    }

}
