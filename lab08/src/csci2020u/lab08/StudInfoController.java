package csci2020u.lab08;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class StudInfoController {
    //@FXML
    //private TableView<StudentRecord> tabView;

    //@FXML
    //private void initialize() {
        //stud.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        //tabView.setItems(DataSource.getAllMarks());
        //tabView.getItems();

    //}
    //@FXML
    //private TextArea txtContent;
    @FXML
    private MenuItem mnuOpen;
    @FXML
    private MenuItem mnuSave;
    @FXML
    private MenuItem mnuExit;
    //tabView for displaying student info csv file when opening files
    @FXML
    private TableView<StudentRecord> tabView;

    private File currentFilename;
    //private File currentSaveLocation;

    @FXML
    private void initialize() {
        currentFilename = null;
    }

    @FXML
    private void onExitClick(javafx.event.ActionEvent e) {
        Platform.exit();
    }

    @FXML
    private void onNewClick(javafx.event.ActionEvent e) {
        //clear code from: https://www.reddit.com/r/javahelp/comments/3eomc4/clearing_a_tableview_in_javafx/
        tabView.getItems().clear();
    }

    @FXML
    private void onOpenClick(javafx.event.ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                //new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                //new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        // To access the main stage without having created a stage variable,
        // We can get it from one of our menu options or from the txtContent
        currentFilename = fileChooser.showOpenDialog(tabView.getScene().getWindow());

        load();
    }

    private void load() {
        //first check the user entered some valid filename
        if (currentFilename != null) {
            try{
                //then check the filename matches a real file (this lets us separate our error messages)
                if(currentFilename.exists()) {
                    //string builder allows you to build a string line by line
                    //StringBuilder is mutable (changeable) but String is not
                    StringBuilder lines = new StringBuilder();
                    BufferedReader rdr = new BufferedReader(new FileReader(currentFilename));

                    String line;
                    while((line = rdr.readLine()) != null){
                        lines.append(line);
                        lines.append("\n");
                    }
                    rdr.close(); //close reader to avoid wasted memory
                    String csvData = lines.toString();
                    String[] rows = csvData.split("\n");

                    ObservableList<StudentRecord> marks = FXCollections.observableArrayList();

                    for(int i = 0; i < rows.length; i++){
                        String[] cells = rows[i].split(",");
                        marks.add(new StudentRecord(cells[0], Float.valueOf(cells[1]), Float.valueOf(cells[2]), Float.valueOf(cells[3])));
                    }
                    tabView.setItems(marks);
                }
                else{
                    System.err.printf("File '%s' does not exist \n", currentFilename.getAbsolutePath());
                }
            }catch(IOException e) {
                System.err.printf("File Error: %s\n", e.getMessage());
            }
        }
    }

    @FXML
    private void onSaveAsClick(javafx.event.ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File As");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                //new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                //new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        // To access the main stage without having created a stage variable,
        // We can get it from one of our menu options or from the txtContent
        currentFilename = fileChooser.showSaveDialog(tabView.getScene().getWindow());
        save();
    }
    private void save() {
        //first check the user entered some valid filename
        if (currentFilename != null) {
            try{
                //then check the filename matches a real file (this lets us separate our error messages)
                //if(currentSaveLocation.exists()) {

                //write the "line" to the output file
                PrintWriter wtr = new PrintWriter(new FileWriter(currentFilename));

                //fetch tabview data: https://stackoverflow.com/questions/29090583/javafx-tableview-how-to-get-cells-data
                //also inspired by: https://www.youtube.com/watch?v=J2AobQq1WiE
                ObservableList<StudentRecord> tabItems = tabView.getItems();
                StringBuilder lines = new StringBuilder();
                for(int i = 0; i < tabItems.size(); i++){
                    lines.append(tabItems.get(i).getStudentId() + ",");
                    lines.append(tabItems.get(i).getAssignmentGrade() + ",");
                    lines.append(tabItems.get(i).getMidtermGrade() + ",");
                    lines.append(tabItems.get(i).getExamGrade() + "\n");
                }
                //System.out.println("Lines: \n" + lines);
                wtr.println(lines);
                wtr.close();

            }catch(IOException e) {
                System.err.printf("Save Error: %s\n", e.getMessage());
            }
        }
        else{
            System.out.println("No save location selected.  Please press \"Save As\" first.");
        }
    }


    @FXML
    private void onSaveClick(javafx.event.ActionEvent e) {
        //save the file to the current "currentSaveLocation"
        save();
    }


}
