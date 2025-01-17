package org.example;

import org.example.refact2.configs.Database;
import org.example.refact2.configs.PostgresDatabaseConnection;
import org.example.refact2.controller.ClienteController;
import org.example.refact2.repository.ClienteDatabaseRepository;
import org.example.refact2.services.ClienteRepository;
import org.example.refact2.services.ClienteService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello, World!");
        Database database = new PostgresDatabaseConnection();
        ClienteRepository clienteRepository = new ClienteDatabaseRepository(database);
        ClienteService clienteService = new ClienteService(clienteRepository);
        new ClienteController(clienteService);
    }
}