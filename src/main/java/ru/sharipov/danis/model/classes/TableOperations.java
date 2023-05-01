package ru.sharipov.danis.model.classes;

import ru.sharipov.danis.model.interfaces.DatabaseTableOperations;

public class TableOperations implements DatabaseTableOperations {

    @Override
    public void createTable() {
        String createTableQuery = "CREATE TABLE name (id_);";
    }
}
