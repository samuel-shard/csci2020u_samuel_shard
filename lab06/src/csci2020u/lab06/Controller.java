package csci2020u.lab06;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class Controller {


    // Part 1 Bar Chart
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
    private Canvas canvas;

    @FXML
    private void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawPieChart(gc);
        drawBarGraph(gc, 70, 550);
    }

    private void drawPieChart(GraphicsContext gc) {
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
            gc.fillArc(550.0, 100.0, 400.0, 400.0, startAngle, sweepAngle, ArcType.ROUND);
            startAngle += sweepAngle;
        }
    }

    private void drawBarGraph(GraphicsContext gc, int startX, int y) {
        double heightCom;
        double heightHou;
        int x = startX;
        int width = 20;

        for (int i = 0; i < avgCommercialPricesByYear.length; i++){
            // get height of current commercial graph
            heightCom = avgCommercialPricesByYear[i]/3200;
            heightHou = avgHousingPricesByYear[i]/3200;
            gc.setFill(Color.BLUE);
            gc.fillRect(x, y-heightCom, width, heightCom);
            gc.setFill(Color.RED);
            gc.fillRect(x-20, y-heightHou, width, heightHou);
            x += 50;
        }
    }
}
