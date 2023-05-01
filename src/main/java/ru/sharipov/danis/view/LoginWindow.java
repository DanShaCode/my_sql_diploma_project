package ru.sharipov.danis.view;

import ru.sharipov.danis.model.classes.MyDatabase;
import ru.sharipov.danis.model.classes.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class LoginWindow extends JFrame {
    private final JTextField loginField;
    private final JPasswordField passwordField;

    public LoginWindow(User user) {
        setTitle("Authorization");
        setSize(500, 175);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel loginLabel = new JLabel("Login:");
        JLabel passwordLabel = new JLabel("Password:");
        loginField = new JTextField();
        loginField.setPreferredSize(new Dimension(200, 25));
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 25));

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.add(loginLabel);
        panel.add(loginField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton loginButton = new JButton("Enter");
        loginButton.setPreferredSize(new Dimension(220, 30));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkLogin(loginField.getText(), new String(passwordField.getPassword()))) {
                    dispose();
                    user.setAuthorized(true);
                    user.setUserName(loginField.getText());
                    new DatabaseMainWindow(user).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Incorrect login or password");
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(loginButton);
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private boolean checkLogin(String login, String password) {
        MyDatabase myDatabase = new MyDatabase();
        boolean authorized = myDatabase.userAuthorization(login, password, new Properties());
        return authorized;
    }
}

