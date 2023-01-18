import java.net.*;
import java.io.*;
import org.json.JSONObject;
import java.time.LocalDate;

public class serveur {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(5555);
        boolean wait = true;
        while (wait) {
            System.out.println("En attente du client ...");
            Socket socket = server.accept();

            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String message  = in.readUTF();
            System.out.println("Message reçu du client : " + message);

            JSONObject jsonUser = new JSONObject(message);
            int serial = jsonUser.getInt("serial");
            String name = jsonUser.getString("name");
            String occupation = jsonUser.getString("occupation");
            int siblings = jsonUser.getInt("siblings");
            double height = jsonUser.getDouble("height");
            boolean married = jsonUser.getBoolean("married");

            String date = jsonUser.getString("birthdate");
            String[] elem = date.split("-");
            LocalDate birthdate = LocalDate.of( Integer.parseInt(elem[0]) , Integer.parseInt(elem[1]) , Integer.parseInt(elem[2]) );


            User user = new User(serial, name, occupation, siblings, height, married, birthdate);
            System.out.println("Calcul de l'âge : "+ user.getAge() + " ans");
            System.out.println("Création du user : " + user);

            JSONObject jsonObject = new JSONObject(user);

            // Envoi du JSONObject au serveur 2
            Socket socket2 = new Socket("127.0.0.1", 7777);
            DataOutputStream dos = new DataOutputStream(socket2.getOutputStream());
            dos.writeUTF(jsonObject.toString());
            dos.flush();


            socket.close();
            socket2.close();
        }
        server.close();
    }
}
