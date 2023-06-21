package com.ankur.prog.productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DatabaseCreator {

    private final DataSource dataSource;

    @Autowired
    public DatabaseCreator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createDatabase(String databaseName) {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE " + databaseName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

