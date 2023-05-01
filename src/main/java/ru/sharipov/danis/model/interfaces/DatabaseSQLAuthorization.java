package ru.sharipov.danis.model.interfaces;

import java.sql.SQLException;
import java.util.Properties;

public interface DatabaseSQLAuthorization {

    boolean userAuthorization(String login, String password, Properties properties);
    void connection (Properties properties) throws SQLException;

}
