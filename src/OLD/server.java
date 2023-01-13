package OLD;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    private static void debug(String message) {
        boolean DEBUG = true;
        if (DEBUG) {
            System.out.println("    >> " + message);
        }
    }

    public static void main(String[] args) throws Exception {
        var user = new User(1, "John Doe", "gardener", 2, 172.35, true, null);
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

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            debug("Sending back to client [" + userjo + "]");
            dos.writeUTF(userjo.toString());

            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            message  = in.readUTF();
            System.out.println(message);

            socket.close();

        }

        server.close();
    }
}