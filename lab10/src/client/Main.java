package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try {
            MessageClient connection = new MessageClient("localhost", 8001);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            String line = null;
            while(connection.isAlive() && (line = in.readLine())!= null && !line.equals("\\q")) {
                System.out.println(connection.sendMessage(line));
            }
            if(line != null && line.equals("\\q")){
                //if \\q quit command was entered, need to send the \\q to the server
                // to also cut the connection on the server's end
                //if we quit due to a lost connection, don't need to do this
                in.close();
                if(connection.isAlive()) {
                    connection.close();
                }
            }
        }catch (Exception e){
            //can use a generic exception and it will handle both errors
            System.err.println(e.getMessage());
        }
    }
}
