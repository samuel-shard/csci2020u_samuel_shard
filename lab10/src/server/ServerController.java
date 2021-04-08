package server;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ServerController {
    @FXML
    private Text actiontarget;

    @FXML
    private void onExitClick(ActionEvent actionEvent) {
        System.out.println("Exit Button Clicked");
        //actiontarget.setText("Exit button pressed");
        Platform.exit();
    }


}