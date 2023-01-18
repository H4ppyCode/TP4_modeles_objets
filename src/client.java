import java.net.InetAddress;
import java.net.Socket;
import java.io.DataOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.json.JSONObject;

public class client {

    public static void main(String[] args) throws Exception {
        InetAddress address = InetAddress.getByName("127.0.0.1");;
        Socket socket = null;
        Scanner console = new Scanner(System.in);

        socket = new Socket(address, 5555);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());



        System.out.println("Please enter the user's serial number:");
        String input = console.nextLine();
        while (!input.matches("-?\\d+(\\.\\d+)?")) { // checks if the input is an integer
            System.out.println("Mauvais type de donnée saisie --> (int).");
            input = console.nextLine();

        }
        Integer serial = Integer.valueOf(input);



        System.out.println("Please enter the user's name:");
        String input2 = console.nextLine();
        while (!input2.matches("[a-zA-Z]+")) { // checks if the input is a string
            System.out.println("Invalid input, please enter a valid name.");
            input = console.nextLine();
        }
        String name = input;

        System.out.println("Please enter the user's occupation:");
        input = console.nextLine();
        while (!input.matches("[a-zA-Z]+")) { // checks if the input is a string
            System.out.println("Invalid input, please enter a valid occupation.");
            input = console.nextLine();
        }
        String occupation = input;

        System.out.println("Please enter the number of siblings the user has:");
        input = console.nextLine();
        while (!input.matches("-?\\d+(\\.\\d+)?")) { // checks if the input is an integer
            System.out.println("Invalid input, please enter a valid number of siblings.");
            input = console.nextLine();
        }
        int siblings = Integer.valueOf(input);

        System.out.println("Please enter the user's height:");
        input = console.nextLine();
        while (!input.matches("-?\\d+(\\.\\d+)?")) { // checks if the input is a double
            System.out.println("Invalid input, please enter a valid height.");
            input = console.nextLine();
        }
        double height = Double.valueOf(input);

        System.out.println("Is the user married? (true/false):");
        input = console.nextLine();
        while (!input.matches("true|false")) { // checks if the input is a boolean
            System.out.println("Invalid input, please enter true or false.");
            input = console.nextLine();
        }
        boolean married = Boolean.valueOf(input);

        System.out.println("What is the user's birthdate? (DD/MM/YYYY)");
        input = console.nextLine();
        while (!input.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)")) { // checks if the input is a date
            System.out.println("Invalid input, please enter a valid date.");
            input = console.nextLine();
        }
        String birthdate = input;

        LocalDate localDate = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        User user = new User(serial, name, occupation, siblings, height, married, localDate);

        System.out.println("Sending object to Server");
        dos.writeUTF((new JSONObject(user)).toString());

        socket.close();
        console.close();
    }
}
