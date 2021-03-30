package main;

//For Plotting in JavaFX
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

//For reading and writing from the Yahoo Finance server
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;

public class Controller {
    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ArrayList<Float> stockCloseGoogle = downloadStockPrices("GOOG");
        ArrayList<Float> stockCloseApple = downloadStockPrices("AAPL");
        System.out.println("stockCloseGoogle:" + stockCloseGoogle);
        System.out.println("stockCloseApple:" + stockCloseApple);

        drawLinePlot(gc,stockCloseGoogle, stockCloseApple);
    }

    public ArrayList<Float> downloadStockPrices(String company){
        long start = LocalDate.of(2010, 1, 1)
                .atStartOfDay(ZoneId.systemDefault())
                .toEpochSecond();

        long end = LocalDate.of(2015, 12, 31)
                .atStartOfDay(ZoneId.systemDefault())
                .toEpochSecond();

        final String ticker = company;

        //System.out.printf("Start Timestamp: %d\n End Timestamp: %d\n", start, end);
        //System.out.println("https://query1.finance.yahoo.com/v7/finance/download/"+ticker+"?period1="+start+"&period2="+end+"1262322000&period2=1451538000&interval=1mo&events=history&\n" +
        //        "includeAdjustedClose=true");

        // create stockCloseList to contain closing prices for the selected stock in the URL
        ArrayList<Float> stockCloseList = new ArrayList<>();

        try {
            URL url = new URL("https://query1.finance.yahoo.com/v7/finance/download/" + ticker + "?period1=" + start + "&period2=" + end + "&interval=1mo&events=history&includeAdjustedClose=true");
            URLConnection conn = url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(false);
            BufferedReader rdr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            //connection is an input stream, use "InputStreamReader(conn.getInputStream()" instead of FileReader(file)
            StringBuilder lines = new StringBuilder();

            String line;
            while (null != (line = rdr.readLine())) {
                lines.append(line);
                lines.append("\n");
            }

            rdr.close();
            String stringLines = lines.toString();

            System.out.println(stringLines);

            String[] rows = stringLines.split("\n");

            for(int i = 1; i < rows.length; i++){
                //System.out.println("before split and i = " + i);
                String[] cells = rows[i].split(",");
                //System.out.println("after split, before add and i = " + i);
                stockCloseList.add(Float.parseFloat(cells[4]));
                //System.out.println("after add and i = " + i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stockCloseList;
    }


    public void drawLinePlot(GraphicsContext gc, ArrayList<Float> stock1, ArrayList<Float> stock2){
        //to end at last point, need to do last point minus 1 in the for loop
        System.out.println("stock1.size(): " + stock1.size());
        gc.strokeLine(50, 50, 50, 550);
        gc.strokeLine(50, 550, 950, 550);
        //gc.strokeLine(50, 50, 950, 50);
        float xMultiple = (float) (canvas.getWidth()-100)/stock1.size();

        double yMultiple1 = (canvas.getHeight()-100)/Collections.max(stock1);
        double yMultiple2 = (canvas.getHeight()-100)/Collections.max(stock2);
        float yMultiple;

        if( yMultiple1 > yMultiple2){
            yMultiple = (float) yMultiple2;
        }else{
            yMultiple = (float) yMultiple1;
        }
        plotLine(gc, stock1, stock2, xMultiple, yMultiple);

    }

    public void plotLine(GraphicsContext gc, ArrayList<Float> stock1, ArrayList<Float> stock2, float xMultiple, float yMultiple){
        for (int i = 0; i < stock1.size() - 1; i++){
            gc.setStroke(Color.RED);
            gc.strokeLine(i*xMultiple+50, 550-(stock1.get(i)*yMultiple), (i+1)*xMultiple+50, 550-(stock1.get(i+1)*yMultiple));
            gc.setStroke(Color.BLUE);
            gc.strokeLine(i*xMultiple+50, 550-(stock2.get(i)*yMultiple), (i+1)*xMultiple+50, 550-(stock2.get(i+1)*yMultiple));
        }

    }


}
