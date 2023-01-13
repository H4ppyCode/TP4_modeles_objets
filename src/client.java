import java.net.InetAddress;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;

import org.json.JSONObject;

import java.util.Scanner;
import java.time.LocalDate;

public class client {

    public static void main(String[] args) throws Exception {
        InetAddress address = InetAddress.getByName("127.0.0.1");;
        Socket socket = null;
        Scanner console = new Scanner(System.in);

        socket = new Socket(address, 5555);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        System.out.println("Please enter the user's serial number:");
        int serial = console.nextInt();
        console.nextLine(); // consume the newline character after the integer input

        System.out.println("Please enter the user's name:");
        String name = console.nextLine();

        System.out.println("Please enter the user's occupation:");
        String occupation = console.nextLine();

        System.out.println("Please enter the number of siblings the user has:");
        int siblings = console.nextInt();
        console.nextLine();

        System.out.println("Please enter the user's height:");
        double height = console.nextDouble();
        console.nextLine();

        System.out.println("Is the user married? (true/false):");
        boolean married = console.nextBoolean();
        console.nextLine();

        System.out.println("Quelle est la date de naissance de cette personne ? (JJ/MM/AAAA)");
        String naissance = console.nextLine();
        String[] elem = naissance.split("/");
        LocalDate localDate = LocalDate.of( Integer.parseInt(elem[2]) , Integer.parseInt(elem[1]) , Integer.parseInt(elem[0]) );
        User user = new User(serial, name, occupation, siblings, height, married);

        System.out.println("Sending object to Server");
        dos.writeUTF((new JSONObject(user)).toString());


        socket.close();
        console.close();
    }
}
