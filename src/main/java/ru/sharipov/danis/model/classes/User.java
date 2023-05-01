package ru.sharipov.danis.model.classes;

import ru.sharipov.danis.model.interfaces.DatabaseUser;

public class User implements DatabaseUser {

    private String userName;

    private boolean isAuthorized;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    @Override
    public boolean isAuthorized() {
        return isAuthorized;
    }
}
