package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static java.awt.Font.*;
import static javafx.scene.text.Font.font;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        //Create gridpane layout
        GridPane myGrid = new GridPane();
        myGrid.setAlignment(Pos.CENTER);
        myGrid.setHgap(10);
        myGrid.setVgap(10);


        myGrid.setPadding(new Insets(25, 25, 25, 25));
        Scene scene = new Scene(root, 300, 275);

        //        Create login UI controls
//        --- Title Welcome
        Text sceneTitle = new Text("Welcome");
        sceneTitle.setFont(font("Tahoma", FontWeight.NORMAL, 20));
// -- labels
        Label lbUserName = new Label("Username:");
        Label lbPassword = new Label("Password:");
        //Sammy added
        Label lbFullName = new Label("Full Name:");
        Label lbEmail = new Label("E-Mail:");
        Label lbPhoneNum = new Label("Phone #:");
        Label lbDateOfBirth = new Label("Date of Birth:");
// -- inputs
        TextField txUserName = new TextField();
        PasswordField psPassword = new PasswordField();
        //Sammy added
        TextField txFullName = new TextField();
        TextField txEmail = new TextField();
        TextField txPhoneNum = new TextField();
        DatePicker txDateOfBirth = new DatePicker();
//        -- Button
        Button btn = new Button("Register");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
//        -- link
        final Text actionTarget = new Text();



//        Add the components onto the myGrid pane
        myGrid.add(sceneTitle, 0,0,2,1);
        myGrid.add(lbUserName, 0,1);
        myGrid.add(txUserName, 1,1);
        myGrid.add(lbPassword, 0,2);
        myGrid.add(psPassword, 1,2);


        //Sammy added Full Name to Date of Birth Fields
        myGrid.add(lbFullName, 0,3);
        myGrid.add(txFullName, 1,3);
        myGrid.add(lbEmail, 0,4);
        myGrid.add(txEmail, 1,4);
        myGrid.add(lbPhoneNum, 0,5);
        myGrid.add(txPhoneNum, 1,5);
        myGrid.add(lbDateOfBirth, 0,6);
        myGrid.add(txDateOfBirth, 1,6);


        //Original Register Button
        myGrid.add(hbBtn, 0, 7);
        myGrid.add(actionTarget, 0, 9);
        //action target is "sign in button pressed"

//        Setting the btn event
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                actionTarget.setFill(Color.FIREBRICK);
                actionTarget.setText("Register button pressed");
                // We are asked to print 4 fields.
                // It was assumed we were not meant to print username and password
                //System.out.println(txUserName.getText());
                //System.out.println(psPassword.getText());
                System.out.println(txFullName.getText());
                System.out.println(txEmail.getText());
                System.out.println(txPhoneNum.getText());
                System.out.println(txDateOfBirth.getValue());

            }
        });


//        Creating myScene with custom layout
        Scene myScene = new Scene(myGrid, 600,400);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
