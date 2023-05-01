package ru.sharipov.danis.view;

import ru.sharipov.danis.model.classes.MyDatabase;
import ru.sharipov.danis.model.classes.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class DatabaseMainWindow extends JFrame {
    public DatabaseMainWindow(User user) {
        setTitle("MyDiplomaSQL");
        setSize(500, 175);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel userLabel = new JLabel("Current User: " + user.getUserName());
        userLabel.setHorizontalAlignment(SwingConstants.LEFT);

        getContentPane().add(userLabel, BorderLayout.NORTH);

        JButton viewTablesButton = new JButton("Просмотр таблиц");
        viewTablesButton.addActionListener(e -> {
            TableSelectionWindow tableSelectionWindow;
            try {
                tableSelectionWindow = new TableSelectionWindow(MyDatabase.getConnection());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            tableSelectionWindow.setVisible(true);
        });

        // Добавляем компонент JButton в центр окна
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        centerPanel.add(viewTablesButton, gbc);
        getContentPane().add(centerPanel, BorderLayout.CENTER);
    }
}
