/**
 * Lab 10 Demo: server.Main
 *
 * @author Michael Valdron
 * created at 2021/03/30
 */
package server;

import static javafx.application.Application.launch;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Server Connected and Waiting for Clients");
            MessageServer.start((args.length > 1) ? Integer.parseInt(args[0]) : 8001);
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        launch(args);
    }
}

/*Sam Version
package server;

public class Main {
    public static void main(String[] args) {
        MessageServer.start(8001);
    }
}
*/