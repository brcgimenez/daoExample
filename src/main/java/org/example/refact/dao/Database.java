package org.example.refact.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Database {

    private final DatabaseConnection connection;

    public Database(DatabaseConnection connection) {
        this.connection = connection;
    }

    public BigDecimal salvar(String tableName, String[] columns, List<Map<String, Object>> values) {
        try (Connection conexao = this.connection.getConnection()) {
            PreparedStatement statement = conexao.prepareStatement(String.format("select max(id) from %s", tableName));
            ResultSet resultSet = statement.executeQuery();
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            statement.close();

            id ++;
            String insertQuery = String.format("insert into %s (%s) values (?,%s)",
                    tableName,
                    String.join(", ", columns),
                    values.stream().map(v -> "?").collect(Collectors.joining(",")));

            statement = conexao.prepareStatement(insertQuery);

            statement.setBigDecimal(1, BigDecimal.valueOf(id));

            for( int i=0; i < values.size(); i++) {
                for (Map.Entry<String, Object> entry : values.get(i).entrySet()) {
                    if (entry.getKey().equals("String")) {
                        statement.setString(i + 2, entry.getValue().toString());
                    }
                }
            }

            statement.execute();
            statement.close();
            return BigDecimal.valueOf(id);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar", e);
        }
    }

    public Map<String, Object> consultarPorId(String tableName, BigDecimal id) {
        try (Connection conexao = this.connection.getConnection()) {
            PreparedStatement statement = conexao.prepareStatement(String.format("select * from %s where id = ?", tableName));
            statement.setBigDecimal(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            Map<String, Object> output = new HashMap<>();

            for (int i = 1; i <= columnCount; i++) {
                output.put(metaData.getColumnName(i), resultSet.getObject(i));
            }

            statement.close();
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao consultar cliente", e);
        }
    }
}
