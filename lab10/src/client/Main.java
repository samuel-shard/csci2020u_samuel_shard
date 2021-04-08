/**
 * Lab 10 Demo: client.Main
 *
 * @author Modified by: Samuel Shard, Base Code By: Michael Valdron
 * created at 2021/03/30
 */

// Sam Version
package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static javafx.application.Application.launch;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("clientInterface.fxml"));
        primaryStage.setTitle("Lab 10: Client");
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        try {
            MessageClient connection = MessageClient.connect(
                (args.length > 0) ? args[0] : "localhost",
                (args.length > 1) ? Integer.parseInt(args[1]) : 8001
            );

            if (connection != null) {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

                    String line = null;
                    while(connection.isAlive() && (line = in.readLine())!= null && !line.equals("\\q")) {
                        System.out.println(connection.sendMessage(line));
                    }
                    if(line != null && line.equals("\\q")){
                        //if \\q quit command was entered, need to send the \\q to the server
                        // to also cut the connection on the server's end
                        //if we quit due to a lost connection, don't need to do this
                        System.out.println(connection.sendMessage(line));
                    }
                    in.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
                if(connection.isAlive()) {
                    connection.close();
                }
            } else {
                System.err.println("No connection made.");
            }
        }  catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }
}

