/**
 * Lab 10 Demo: server.Main
 *
 * @author Modified by: Samuel Shard, Base Code By: Michael Valdron
 * created at 2021/03/30
 */
package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("serverInterface.fxml"));
        primaryStage.setTitle("Lab 10: Server");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            var serverThread = new Thread(() -> {
                System.out.println("Server Connected and Waiting for Clients");
                MessageServer.start((args.length > 1) ? Integer.parseInt(args[0]) : 8001);
            });

            var UIThread = new Thread(() -> {
                launch(args);
            });

            UIThread.start();
            serverThread.start();
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }
}