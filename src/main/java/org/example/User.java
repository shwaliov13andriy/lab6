package org.example;

import java.util.Objects;

public class User implements Identifiable<String> {
    private final String login;
    private final String fullName;
    private final String registrationDate;

    public User(String login, String fullName, String registrationDate) {
        this.login = login;
        this.fullName = fullName;
        this.registrationDate = registrationDate;
    }

    @Override
    public String getKey() {
        return login;
    }

    public String getLogin() {
        return login;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return login + " | " + fullName + " (зареєстрований: " + registrationDate + ")";
    }
}