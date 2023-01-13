import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;

import org.json.JSONObject;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server started, listening for incoming connections on port 1234...");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected: " + clientSocket.getInetAddress());
        String jsonString;
        while (true) {

            LocalDate birthdate = LocalDate.parse("2002-04-24");
            User user = new User(1, "John Doe", "gardener", 2, 172.35, true, birthdate);
            JSONObject userJson = user.toJson();
            jsonString = userJson.toString();

            DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
            outToClient.writeBytes(jsonString);


            clientSocket.close();

        }

    }
}
