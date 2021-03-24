package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.ZoneId;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        long start = LocalDate.of(2020, 1, 1)
                .atStartOfDay(ZoneId.systemDefault())
                .toEpochSecond();

        long end = LocalDate.of(2021, 3, 24)
                .atStartOfDay(ZoneId.systemDefault())
                .toEpochSecond();

        final String ticker = "GOOG";

        //System.out.printf("Start Timestamp: %d\n End Timestamp: %d\n", start, end);
        //System.out.println("https://query1.finance.yahoo.com/v7/finance/download/"+ticker+"?period1="+start+"&period2="+end+"1262322000&period2=1451538000&interval=1mo&events=history&\n" +
        //        "includeAdjustedClose=true");

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
            System.out.println(lines.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}