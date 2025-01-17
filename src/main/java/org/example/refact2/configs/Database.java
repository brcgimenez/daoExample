package org.example.refact2.configs;

import java.sql.Connection;

public interface Database {
    Connection getConnection();
}
