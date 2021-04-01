package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class MessageServer {
    public static void start(int port){
        try {
            //var creates a variable and auto-picks the type
            //only works when you do the new right on creation
            ServerSocket serverSocket = new ServerSocket();
            LinkedList<Thread> threads = new LinkedList<Thread>();
            //Thread function runs separately from the main thread
            Thread socketThread = new Thread(() -> {
                try {
                    while (true) {
                        //Will wait for incoming connection, then create the socket connection
                        Socket newSocket = serverSocket.accept();
                        ClientHandler handler = new ClientHandler(newSocket);
                        Thread t = new Thread(handler);
                        t.start();
                        threads.add(t);
                    }
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            });

            //CREATING A SEPARATE THREAD TO LISTEN FOR CONSOLE INPUT
            //BufferedReader to read input from the server console to know when to quit
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            String line;
            socketThread.start();
            //while loops with ; is treated like a wait statement
            while((line = in.readLine()) == null || !line.equals("\\q"));
            in.close();
            //Interrupts the thread
            socketThread.interrupt();

            while(!threads.isEmpty()){
                threads.remove().interrupt();
            }
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
