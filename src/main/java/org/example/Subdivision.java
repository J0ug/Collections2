package org.example;

public class Subdivision {
    private final String id;
    private final String name;

    // Конструктор
    public Subdivision(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Геттеры
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Subdivision{id='%s', name='%s'}", id, name);
    }
}
