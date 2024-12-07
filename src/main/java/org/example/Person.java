package org.example;

public class Person {
    private final int id;
    private final String name;
    private final String gender;
    private final Subdivision subdivision;
    private final double salary;
    private final String birthDate;

    // Конструктор
    public Person(int id, String name, String gender, Subdivision subdivision, double salary, String birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.subdivision = subdivision;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    // Геттеры
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Subdivision getSubdivision() {
        return subdivision;
    }

    public double getSalary() {
        return salary;
    }

    public String getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return String.format("Person{id=%d, name='%s', gender='%s', subdivision=%s, salary=%.2f, birthDate='%s'}",
                id, name, gender, subdivision, salary, birthDate);
    }
}
