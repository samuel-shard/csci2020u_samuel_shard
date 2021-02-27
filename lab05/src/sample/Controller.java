package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class Controller {
    @FXML
    private TableView tabView;

    @FXML
    private  void initialize() {
        tabView.setItems(DataSource.getAllMarks());
        tabView.getItems();
    }
}
