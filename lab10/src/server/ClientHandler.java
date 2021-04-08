/**
 * Lab 10 Demo: server.ClientHandler
 *
 * @author Michael Valdron
 * created at 2021/03/30
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ClientHandler implements Runnable {
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


/* Sam Version
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
            while(!socket.isClosed() && (line= in.readLine()) != null){
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
*/