package csci2020u.lab05;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class StudInfoController {
    @FXML
    private TableView<StudentRecord> tabView;

    @FXML
    private void initialize() {
        //stud.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        tabView.setItems(DataSource.getAllMarks());
        //tabView.getItems();
    }
}
