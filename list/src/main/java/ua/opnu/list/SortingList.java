package main.java.ua.opnu.list;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Comparator;

public class SortingList extends Application {

    private ObservableList<Student> students;

    private boolean orderName = true;
    private boolean orderLastName = true;
    private boolean orderMark = true;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Список студентів");
        students = populateList();

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(5));
        vbox.setAlignment(Pos.CENTER);

        final ListView<Student> listView = new ListView<>(students);
        listView.setPrefSize(400, 240);

        final HBox hbox = setButtons();

        vbox.getChildren().addAll(listView, hbox);
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private ObservableList<Student> populateList() {
        Student student1 = new Student("Борис", "Іванов", 75);
        Student student2 = new Student("Петро", "Петренко", 32);
        Student student3 = new Student("Сергій", "Сергієнко", 61);
        Student student4 = new Student("Григорій", "Сковорода", 88);
        return FXCollections.observableArrayList(
                student1, student2, student3, student4
        );
    }

    private HBox setButtons() {
        final Button sortByNameButton = new Button("Сортувати за ім'ям");
        final Button sortByLastNameButton = new Button("Сортувати за прізвищем");
        final Button sortByMarkButton = new Button("Сортувати за оцінкою");

        HBox.setHgrow(sortByNameButton, Priority.ALWAYS);
        HBox.setHgrow(sortByLastNameButton, Priority.ALWAYS);
        HBox.setHgrow(sortByMarkButton, Priority.ALWAYS);
        sortByNameButton.setMaxWidth(Double.MAX_VALUE);
        sortByLastNameButton.setMaxWidth(Double.MAX_VALUE);
        sortByMarkButton.setMaxWidth(Double.MAX_VALUE);

        sortByNameButton.setOnAction(event -> {
            Comparator<Student> nameComparator = Comparator.comparing(Student::getName);
            students.sort(orderName ? nameComparator : nameComparator.reversed());
            orderName = !orderName; // [cite: 89]
        });

        sortByLastNameButton.setOnAction(event -> {
            Comparator<Student> lastNameComparator = Comparator.comparing(Student::getLastName);
            students.sort(orderLastName ? lastNameComparator : lastNameComparator.reversed());
            orderLastName = !orderLastName;
        });

        sortByMarkButton.setOnAction(event -> {
            Comparator<Student> markComparator = Comparator.comparingDouble(Student::getAvgMark);
            students.sort(orderMark ? markComparator : markComparator.reversed());
            orderMark = !orderMark;
        });

        HBox hb = new HBox();
        hb.setSpacing(5);
        hb.getChildren().addAll(sortByNameButton, sortByLastNameButton, sortByMarkButton);
        hb.setAlignment(Pos.CENTER);
        return hb;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
