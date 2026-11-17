package org.example;

import java.util.Comparator;

public class UserByDateComparator implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        return u1.getRegistrationDate().compareTo(u2.getRegistrationDate());
    }
}