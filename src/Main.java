import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.json.JSONObject;

public class Main {
    public static void main(String args[]) throws IOException {
        String serverIp = "127.0.0.1";
        int serverPort = 1234;

        Socket clientSocket = new Socket(serverIp, serverPort);

        while(true){
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String jsonString = inFromServer.readLine();

            JSONObject json = new JSONObject(jsonString);
            String birthdate = json.getString("birthdate");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(birthdate, formatter);

            User user = new User(json.getInt("serial"), json.getString("name"),
                    json.getString("occupation"), json.getInt("siblings"),
                    json.getDouble("height"), json.getBoolean("married"), date);

            System.out.println(user.toString());
            Scanner scanner = new Scanner(System.in);
            System.out.println("Entrez la date de naissance (yyyy-MM-dd) : ");

            String newbirthdate = scanner.nextLine();
            System.out.println(newbirthdate);
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate newdate = LocalDate.parse(newbirthdate, formatter2);
            System.out.println("newdate : " + newdate);
            User updatedUserJson = new User(json.getInt("serial"), json.getString("name"),
                    json.getString("occupation"), json.getInt("siblings"),
                    json.getDouble("height"), json.getBoolean("married"), newdate);

            System.out.println(updatedUserJson.toString());
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes(updatedUserJson.toString());
            clientSocket.close();

        }
    }
}
