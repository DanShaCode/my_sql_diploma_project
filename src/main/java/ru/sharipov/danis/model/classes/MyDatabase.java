package ru.sharipov.danis.model.classes;

import ru.sharipov.danis.model.interfaces.DatabaseSQLAuthorization;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class MyDatabase implements DatabaseSQLAuthorization {
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    @Override
    public void connection(Properties properties) throws SQLException {
        String dbPath = properties.getProperty("db.path");
        String dbUsername = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");
        this.connection = DriverManager.getConnection(dbPath, dbUsername, dbPassword);
    }

    @Override
    public boolean userAuthorization(String login, String password, Properties properties) {
        boolean result = false;
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/application.properties");
            properties.load(fileInputStream);
            connection(properties);

            String query = "SELECT * FROM users WHERE username = ? AND password = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            result = resultSet.next();

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
