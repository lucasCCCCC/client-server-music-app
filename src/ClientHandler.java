import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {

    Socket socket;
    int clientId;
    Database db;

    public ClientHandler (Socket socket, int clientId, Database db) {

        this.socket = socket;
        this.clientId = clientId;
        this.db = db;
    }

    public void run() {

        System.out.println("ClientHandler started...");

        try{

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            PrintWriter outToClient = new PrintWriter(this.socket.getOutputStream(), true);
            String clientMessage;

            while (! (clientMessage = inFromClient.readLine()).equals("stop") ){
                outToClient.println(db.getArtistInfo(clientMessage));
                System.out.println("Client has sent the following info: " + clientMessage);
            }

            System.out.println("tesrggrtgt" + db.getArtistInfo(clientMessage));

            System.out.println("Client " + this.clientId + " has disconnected");
            outToClient.println("Connection closed, Goodbye!");

            inFromClient.close();
            outToClient.close();
            this.socket.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
