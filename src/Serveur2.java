import java.net.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

public class Serveur2 {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(7777);
        boolean wait = true;
        int iter = 0;
        while (wait) {

            System.out.println("En attente du serveur ...");
            Socket socket = server.accept();

            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String message = in.readUTF();
            System.out.println(message);

            JSONObject json = new JSONObject(message);
            int serial = json.getInt("serial");
            String name = json.getString("name");
            String occupation = json.getString("occupation");
            int siblings = json.getInt("siblings");
            double height = json.getDouble("height");
            boolean married = json.getBoolean("married");

            String date = json.getString("birthdate");
            String[] elem = date.split("-");
            LocalDate birthdate = LocalDate.of( Integer.parseInt(elem[0]) , Integer.parseInt(elem[1]) , Integer.parseInt(elem[2]));

            int age = json.getInt("age");

            File file = new File("users.csv");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(iter + " = " + serial + "," + name + "," + occupation + "," + siblings + "," + height + "," + married + "," + birthdate + "," + age);
            bw.newLine();
            bw.close();

            iter += 1; //pour iterer dans le fichier .csv

            socket.close();
        }
        server.close();
    }
}
