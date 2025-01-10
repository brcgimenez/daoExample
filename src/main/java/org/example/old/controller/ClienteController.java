package org.example.old.controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

//End-points
//@RestController
//@RestMapping("/clientes")
public class ClienteController {

//    @PostMapping()
    public Output salvar(Input input) {
        String url = "jdbc:postgresql://localhost:5432/mydb";
        String user = "myuser";
        String password = "mypassword";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = connection.prepareStatement("select max(id) from cliente");
            ResultSet resultSet = statement.executeQuery();
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            statement.close();

            id ++;

            String insertQuery = "insert into cliente (id, nome, email, senha, ie_situacao) values (?,?,?,?,?)";
            statement = connection.prepareStatement(insertQuery);
            statement.setBigDecimal(1, BigDecimal.valueOf(id));
            statement.setString(2, input.nome());
            statement.setString(3, input.email());
            statement.setString(4, input.senha());
            statement.setString(5, input.situacao());

            statement.execute();
            statement.close();
            return new Output(BigDecimal.valueOf(id), 201, null);

        } catch (SQLException e) {
            e.printStackTrace();
            return new Output(null, 500, e.getMessage());
        }
    }

    public OutputConsulta consultarPorId(BigDecimal id) {
        String url = "jdbc:postgresql://localhost:5432/mydb";
        String user = "myuser";
        String password = "mypassword";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = connection.prepareStatement("select * from cliente where id = ?");
            statement.setBigDecimal(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            var output = new OutputConsulta(resultSet.getBigDecimal("id"), resultSet.getString("nome"), resultSet.getString("email"), resultSet.getString("senha"), resultSet.getString("ie_situacao"));
            statement.close();
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
            return new OutputConsulta(500, e.getMessage());
        }
    }

    public record Input(String nome, String email, String senha, String situacao) {
    }

    public record Output(BigDecimal id, int status, String mensagem) {
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
