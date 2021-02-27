package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataSource {
    public static ObservableList<StudentRecord> getAllMarks(){
        ObservableList<StudentRecord> marks = FXCollections.observableArrayList();

        marks.add(new StudentRecord("10055555555", "John Doe", 77.8f));
        marks.add(new StudentRecord("2580525802", "Bob Doe", 87.8f));
        marks.add(new StudentRecord("2905820009", "Joe Doe", 88.4f));
        marks.add(new StudentRecord("89503485340", "Albert Doe", 97.8f));

        return marks;
    }
}
