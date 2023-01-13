import java.net.*;
import java.io.*;
import org.json.JSONObject;

public class serveur {

    private static boolean DEBUG = true;

    private static void debug(String message) {
        if (DEBUG) {
            System.out.println("    >> " + message);
        }
    }

    public static void main(String[] args) throws Exception {
        var user = new User(1, "John Doe", "gardener", 2, 172.35, true);
        var userjo = new JSONObject(user);
        System.out.println(userjo);

        ServerSocket server = new ServerSocket(5555);
        boolean wait = true;
        while (wait) {
            System.out.println("Waiting for the client request");
            Socket socket = server.accept();

            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String message  = in.readUTF();
            System.out.println(message);


            
            socket.close();

        }
        
        server.close();
    }
}
