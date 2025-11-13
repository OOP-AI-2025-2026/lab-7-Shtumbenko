package ua.opnu;

import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Student {
    private String name;
    private String group;
    private int[] marks;

    public Student(String name, String group, int[] marks) {
        this.name = name;
        this.group = group;
        this.marks = marks;
    }

    public String getName() { return name; }
    public String getGroup() { return group; }
    public int[] getMarks() { return marks; }

    public boolean hasFailures() {
        for (int mark : marks) {
            if (mark < 60) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Студент: " + name + " (Група: " + group + "), Оцінки: " + Arrays.toString(marks);
    }
}

class StudentForLab6 {
    private String name;
    private String lastName;
    private double avgMark;

    public StudentForLab6(String name, String lastName, double avgMark) {
        this.name = name;
        this.lastName = lastName;
        this.avgMark = avgMark;
    }
    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public double getAvgMark() { return avgMark; }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", lastName='" + lastName + '\'' + ", avgMark=" + avgMark + '}';
    }
}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("----------  Завдання 1 ----------");
        task1();

        System.out.println("\n----------  Завдання 2 ----------");
        task2();

        System.out.println("\n----------  Завдання 3 ----------");
        task3();

        System.out.println("\n----------  Завдання 4 ----------");
        task4();

        System.out.println("\n----------  Завдання 5 ----------");
        task5();

        System.out.println("\n----------  Завдання 6 ----------");
        task6();

        System.out.println("\n----------  Завдання 7 ----------");
        task7();
    }

    public static void task1() {
        Predicate<Integer> isPrime = n -> {
            if (n <= 1) return false;
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        };

        int num1 = 7;
        int num2 = 10;
        System.out.printf("Чи %d є простим? %b\n", num1, isPrime.test(num1));
        System.out.printf("Чи %d є простим? %b\n", num2, isPrime.test(num2));
    }

    public static List<Student> filterStudents(Student[] students, Predicate<Student> predicate) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (predicate.test(student)) {
                result.add(student);
            }
        }
        return result;
    }

    public static void task2() {
        Student[] students = {
                new Student("Іван", "IT-101", new int[]{75, 88, 60}),
                new Student("Марія", "IT-102", new int[]{90, 95, 100}),
                new Student("Петро", "IT-101", new int[]{55, 65, 70}),
                new Student("Ольга", "IT-102", new int[]{40, 80, 90})
        };

        Predicate<Student> hasFailuresPredicate = student -> student.hasFailures();

        System.out.println("Студенти з заборгованостями:");
        List<Student> failedStudents = filterStudents(students, hasFailuresPredicate);
        failedStudents.forEach(System.out::println);
    }

    public static <T> List<T> filterByTwoPredicates(T[] array, Predicate<T> p1, Predicate<T> p2) {
        List<T> result = new ArrayList<>();
        for (T item : array) {
            if (p1.test(item) && p2.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public static void task3() {
        Student[] students = {
                new Student("Іван", "IT-101", new int[]{75, 88, 60}),
                new Student("Марія", "IT-102", new int[]{90, 95, 100}),
                new Student("Петро", "IT-101", new int[]{55, 65, 70}),
                new Student("Ольга", "IT-102", new int[]{40, 80, 90})
        };

        Predicate<Student> fromGroup101 = s -> s.getGroup().equals("IT-101");

        Predicate<Student> hasFailures = s -> s.hasFailures();

        System.out.println("Студенти з групи IT-101, ЯКІ мають заборгованості:");
        List<Student> result = filterByTwoPredicates(students, fromGroup101, hasFailures);
        result.forEach(System.out::println);
    }

    public static <T> void forEach(T[] array, Consumer<T> consumer) {
        for (T item : array) {
            consumer.accept(item);
        }
    }

    public static void task4() {
        StudentForLab6 student1 = new StudentForLab6("Борис", "Іванов", 75);
        StudentForLab6 student2 = new StudentForLab6("Петро", "Петренко", 32);
        StudentForLab6 student3 = new StudentForLab6("Сергій", "Сергієнко", 61);

        StudentForLab6[] studentsForLab6 = {student1, student2, student3};

        Consumer<StudentForLab6> printNameAndLastName = s -> {
            System.out.println(s.getLastName().toUpperCase() + " " + s.getName().toUpperCase());
        };

        forEach(studentsForLab6, printNameAndLastName);
    }

    public static <T> void applyIf(T[] array, Predicate<T> predicate, Consumer<T> consumer) {
        for (T item : array) {
            if (predicate.test(item)) {
                consumer.accept(item);
            }
        }
    }

    public static void task5() {
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Predicate<Integer> isEven = n -> n % 2 == 0;

        Consumer<Integer> printMultiplied = n -> {
            System.out.println(n + " * 10 = " + (n * 10));
        };

        System.out.println("Застосувати дію (x*10) до парних чисел:");
        applyIf(numbers, isEven, printMultiplied);
    }

    public static Integer[] applyFunction(Integer[] array, Function<Integer, Integer> func) {
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = func.apply(array[i]);
        }
        return result;
    }

    public static void task6() {
        Function<Integer, Integer> powerOfTwo = n -> (int) Math.pow(2, n);

        Integer[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println("Початковий масив: " + Arrays.toString(numbers));
        Integer[] powers = applyFunction(numbers, powerOfTwo);
        System.out.println("Результат (2^n):  " + Arrays.toString(powers));
    }

    public static String[] stringify(Integer[] array, Function<Integer, String> func) {
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = func.apply(array[i]);
        }
        return result;
    }

    public static void task7() {
        Function<Integer, String> numberToString = n -> {
            switch (n) {
                case 0: return "нуль";
                case 1: return "один";
                case 2: return "два";
                case 3: return "три";
                case 4: return "чотири";
                case 5: return "п'ять";
                case 6: return "шість";
                case 7: return "сім";
                case 8: return "вісім";
                case 9: return "дев'ять";
                default: return "невідоме число";
            }
        };

        Integer[] numbers = {5, 0, 3, 1, 1, 9, 2, 8, 7, 6};

        System.out.println("Початковий масив: " + Arrays.toString(numbers));
        String[] stringResults = stringify(numbers, numberToString);
        System.out.println("Результат (рядки): " + Arrays.toString(stringResults));
    }
}
