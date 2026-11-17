package org.example;

import java.util.Objects;

public class Title implements Identifiable<String> {
    private final String id;
    private final String name;
    private final String type;
    private final int year;
    private final double rating;

    public Title(String id, String name, String type, int year, double rating) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.year = year;
        this.rating = rating;
    }

    @Override
    public String getKey() {
        return id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Title title = (Title) o;
        return Objects.equals(id, title.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + " | " + name + " (" + type + ", " + year + ", рейтинг: " + rating + ")";
    }
}