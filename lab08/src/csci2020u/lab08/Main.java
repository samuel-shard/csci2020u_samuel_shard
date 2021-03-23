package csci2020u.lab08;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.scene.text.Font.font;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("studinfo.fxml"));
        primaryStage.setTitle("Lab 08 - Loading and Saving Files");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
