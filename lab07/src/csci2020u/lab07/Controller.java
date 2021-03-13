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

    @FXML
    private Label maxFine;
    @FXML
    private Label minFine;

    /*// Part 1 Bar Chart
    private static final double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static final double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    // Part 2 Pie Chart
    private static final String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static final int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static final Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    @FXML
    private Canvas canvas; */

    @FXML
    private void initialize() {
        //GraphicsContext gc = canvas.getGraphicsContext2D();

        //drawPieChart(gc);

        Map<String, List<Integer>> fineAmountsCol = readCSV("Parking_Tags_Data_2014_4.csv", 4);
        if (fineAmountsCol.containsKey("set_fine_amount")) {
            List<Integer> fineAmounts = fineAmountsCol.get("set_fine_amount");
            int maxFine = Integer.MIN_VALUE;
            int minFine = Integer.MAX_VALUE; //make sure minFine is initialized to a big value so it is overwritten

            for (int fine : fineAmounts) {
                if (fine > maxFine) {
                    maxFine = fine;
                }
                if (fine < minFine) {
                    minFine = fine;
                }
            }

            System.out.printf("Max Fine: %d\n", maxFine);
            System.out.printf("Min Fine: %d\n", minFine);

            this.maxFine.setText(Integer.toString(maxFine));
            this.minFine.setText(Integer.toString(minFine));


        }
    }

    /*private void drawPieChart(GraphicsContext gc) {
        int totalPurchasesByAgeGroup = 0;
        for (int purchaseByAge: purchasesByAgeGroup){
            totalPurchasesByAgeGroup += purchaseByAge;
        }

        double startAngle = 0.0;
        for (int i = 0; i < purchasesByAgeGroup.length; i++){
            // get percentage of purchases by each age group
            double slicePercentage = (double)purchasesByAgeGroup[i] / totalPurchasesByAgeGroup;
            double sweepAngle = slicePercentage * 360.0;

            gc.setFill(pieColours[i]);
            // make width and height the same value or else you get an oval
            gc.fillArc(600.0, 150.0, 300.0, 300.0, startAngle, sweepAngle, ArcType.ROUND);
            startAngle += sweepAngle;
        }
    }*/

    //Lab 07 Code for parsing through file and returning frequency map
    private Map<String, List<Integer>> readCSV(String filename, int columnIndex) {
        File currentWorkingDirectory = new File(".");
        File inFile = new File(currentWorkingDirectory, filename);
        Map<String, List<Integer>> result = new HashMap<>();
        List<Integer> fineAmounts = new ArrayList<>();
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
                    fineAmounts.add(Integer.parseInt(cells[columnIndex]));
                }

                result.put(label, fineAmounts);
            }
            else{
                System.err.printf("File '%s' was not found.\n", inFile.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException nfe){
            System.err.printf("Invalid number in data: '%s'.\n", nfe.getMessage());
        }

        return result;
    }
}