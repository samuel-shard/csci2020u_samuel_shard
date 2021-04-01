package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            // PrintWriter for output stream
            // NEED "true" to indicate that output is flushed every time
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line;
            while((line= in.readLine()) != null){
                //You will need to change this to a JavaFX quit button, so you don't need this
                if(line.equals("\\q")){
                    out.println("Exiting Server");
                    break;
                }
                //REPLACE WITH JavaFX UNEDITABLE text area
                System.out.println(line);
                //Print line on the client side
                out.print(line);
            }
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
