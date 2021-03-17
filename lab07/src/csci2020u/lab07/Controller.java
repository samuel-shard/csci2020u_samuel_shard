package csci2020u.lab07;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

    private static ArrayList<Integer> stormTypeCountList = new  ArrayList<Integer>();

    private static ArrayList<String> stormTypeList = new  ArrayList<String>();


    // Part 2 Pie Chart
    private static final Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawPieChart(gc);

    }

    private void drawPieChart(GraphicsContext gc) {

        // MUST PUT stormTypeList AND stormTypeCountList CREATION IN drawPieChart FUNCTION!!!
        // OR ELSE FUNCTION DOES NOT WORK!!! (NO ERROR, IT JUST DOES NOTHING.)
        Map<String, Integer> stormTypeCol = readCSV("weatherwarnings-2015.csv", 5);
        System.out.println(stormTypeCol);

        for(Map.Entry<String,Integer> entry : stormTypeCol.entrySet()){
            stormTypeList.add(entry.getKey());
            stormTypeCountList.add(entry.getValue());
        }
        System.out.println("stormTypeList: " + stormTypeList);
        System.out.println("stormTypeCountList: " + stormTypeCountList);
        System.out.println("stormTypeCountList.size(): " + stormTypeCountList.size());

        int totalStorms = 0;
        System.out.println("totalstorms: " + totalStorms);

        for (int i = 0; i < stormTypeCountList.size(); i++){
            totalStorms += stormTypeCountList.get(i);
        }

        System.out.println("stormTypeCountList.size(): " + stormTypeCountList.size());
        System.out.println("totalstorms: " + totalStorms);

        double startAngle = 0.0;
        int legendIncrement = 0;
        for (int i = 0; i < stormTypeCountList.size(); i++){
            // get percentage of purchases by each age group
            double slicePercentage = (double) stormTypeCountList.get(i) / totalStorms;
            double sweepAngle = slicePercentage * 360.0;
            System.out.println("sweepAngle: " + sweepAngle);

            gc.setFill(pieColours[i]);
            // make width and height the same value or else you get an oval
            gc.fillArc(600.0, 150.0, 300.0, 300.0, startAngle, sweepAngle, ArcType.ROUND);
            gc.fillRect(50.0, 150.0+legendIncrement, 100.0, 40.0);

            gc.setFill(Color.BLACK);
            gc.strokeArc(600.0, 150.0, 300.0, 300.0, startAngle, sweepAngle, ArcType.ROUND);
            startAngle += sweepAngle;
            gc.strokeRect(50.0, 150.0+legendIncrement, 100.0, 40.0);
            gc.strokeText(stormTypeList.get(i), 160, 180 +legendIncrement);
            legendIncrement += 50;
        }
    }

    //Lab 07 Code for parsing through file and returning frequency map
    private Map<String, Integer> readCSV(String filename, int columnIndex) {
        File currentWorkingDirectory = new File(".");
        File inFile = new File(currentWorkingDirectory, filename);
        Map<String, Integer> result = new HashMap<>();
        List<String> fineAmounts = new ArrayList<>();
        String label;

        try {
            if(inFile.exists()) {
                BufferedReader rdr = new BufferedReader(new FileReader(inFile));
                //Dont need to deal with label for lab 7 csv file
                String line = rdr.readLine();
                String[] cells = line.split(",");
                label = cells[columnIndex];
                //ignore these 3 lines for lab 7

                while ((line = rdr.readLine()) != null) {
                    cells = line.split(",");
                    fineAmounts.add(cells[columnIndex]);

                    if (result.containsKey(cells[columnIndex])){
                        result.put(cells[columnIndex], result.get(cells[columnIndex]) + 1);

                    }else{
                        result.put(cells[columnIndex], 1);
                    }
                }
            }
            else{
                System.err.printf("File '%s' was not found.\n", inFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
