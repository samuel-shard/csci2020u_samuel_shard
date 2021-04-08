package client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class ClientController {
    @FXML
    private Text actiontarget;

    @FXML
    private TextField message;

    @FXML
    private TextField username;

    @FXML
    private void onSendClick(ActionEvent actionEvent) {
        try {
            MessageClient mc = new MessageClient("localhost", 8001);
            System.out.println("Send Button Clicked");
            actiontarget.setText("Send button pressed");
            mc.sendMessage(username.getText() + ": " + message.getText());
            System.out.println("message: " + message.getText());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onExitClick(ActionEvent actionEvent) {
        Platform.exit();
    }


}
