package ru.sharipov.danis.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;


public class TableSelectionWindow extends JFrame {

    public TableSelectionWindow(Connection connection) throws SQLException {
        setTitle("Выбор таблицы");
        setSize(500, 175);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titleLabel = new JLabel("Выберите таблицу:");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(titleLabel, BorderLayout.NORTH);

        ArrayList<String> tableNames = new ArrayList<>();
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                if (!tableName.equals("users")) {
                    tableNames.add(tableName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JList<String> tableList = new JList<>(tableNames.toArray(new String[0]));
        tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(tableList);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton selectButton = new JButton("Выбрать");
        selectButton.addActionListener(e -> {
            String selectedTable = tableList.getSelectedValue();
            if (selectedTable != null) {
                try {
                    String query = "SELECT * FROM " + selectedTable;
                    ResultSet resultSet = connection.createStatement().executeQuery(query);
                    JTable table = new JTable(buildTableModel(resultSet));
                    JOptionPane.showMessageDialog(TableSelectionWindow.this, new JScrollPane(table));
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        getContentPane().add(selectButton, BorderLayout.SOUTH);
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> row = new Vector<>(columnCount);
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                row.add(rs.getObject(columnIndex));
            }
            data.add(row);
        }


        return new DefaultTableModel(data, columnNames);
    }
}


