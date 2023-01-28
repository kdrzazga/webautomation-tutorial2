package org.kd.anotherspringtutorial.webapp.config.data;

public enum Roles {
    ADMIN;

    public String getLogin() {
        return "admin";
    }

    public String getPassword() {
        return "admin";
    }
}