package ru.sharipov.danis.controller;

import ru.sharipov.danis.model.classes.User;
import ru.sharipov.danis.view.LoginWindow;

public class Controller {

    public Controller (){
        User user = new User();
        LoginWindow loginWindow = new LoginWindow(user);
        loginWindow.setVisible(true);
    }
}
