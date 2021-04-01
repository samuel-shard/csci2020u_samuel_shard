package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageClient {
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;


    public MessageClient(String host, int port) throws IOException {
        //the socket needs a sting for the host IP address name and the port it's listening to
        this.socket = new Socket(host, port);
        this.out = new PrintWriter(this.socket.getOutputStream(), true); //true means flush output
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    public boolean isAlive() {return socket.isConnected();}

    //returns a string so the server cna send the same message back to the client
    public String sendMessage(String msg){
        String res = null;
        out.println(msg);

        try {
            res = in.readLine();
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
        return res;
    }

    //close input stream
    public void close(){
        try {
            in.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        out.close();

        if(!socket.isClosed()){
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
