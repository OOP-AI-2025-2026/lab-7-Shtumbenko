package main.java.ua.opnu.list;

public class Student {
    private String name;
    private String lastName;
    private double avgMark;

    public Student(String name, String lastName, double avgMark) {
        this.name = name;
        this.lastName = lastName;
        this.avgMark = avgMark;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public double getAvgMark() {
        return avgMark;
    }

    @Override
    public String toString() {
        return String.format("%s %s (Оцінка: %.1f)", name, lastName, avgMark);
    }
}
