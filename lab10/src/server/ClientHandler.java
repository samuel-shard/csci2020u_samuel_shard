/**
 * Lab 10 Demo: server.ClientHandler
 *
 * @author Modified by: Samuel Shard, Base Code By: Michael Valdron
 * created at 2021/03/30
 */
package server;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

//A2 IMPORTS
//Imports for JavaFX user interface
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

//Imports for file reading and writing
import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
//END OF A2 Imports

public class ClientHandler implements Runnable{
    protected final Socket socket;
    protected PrintWriter out     = null;
    protected BufferedReader in   = null;
    public ArrayList<String> messages = null;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Hello, Client Handler Running");
            var out = new PrintWriter(socket.getOutputStream(), true);
            var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line;
            while (!socket.isClosed() && (line = in.readLine()) != null) {
                System.out.println("Please enter a command: ");
                if (line.equals("\\q")) {
                    out.println("Bye.");
                    break;
                }
                //String previousText = board.getText();
                //Attempted to change the board TextArea from server but could not access it.
                System.out.println(line);
                out.println(line);
            }
            System.out.println("None entered.  Program quit");
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void stop() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}