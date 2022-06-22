import java.io.*;
import java.net.*;

public class Server {

    public static void main(String args[]) throws IOException {

        Credentials credentials = new Credentials();

        ServerSocket serverSocket = new ServerSocket(credentials.PORT);

        System.out.println("Server is running");
        int clientId = 0;

        Database database = new Database();

        if(!database.establishDBConnection()){

            System.out.println("DB connection fail, stopping.");

        } else {

            System.out.println("Server is now connected to DB");

            while (true){

                Socket clientSocket = serverSocket.accept();
                clientId++;

                System.out.println("Client " + clientId + " connected with IP " +
                        clientSocket.getInetAddress().getHostAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, clientId, database);

                new Thread(clientHandler).start();
            }
        }
    }
}
