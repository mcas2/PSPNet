package UT3_SocketTCP;

import javax.imageio.IIOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class ClienteTCP {
    static final String HOST = "127.0.0.1";
    static final int PORT = 2222;

    public static void main(String[] args) {
        DataInputStream in;
        DataOutputStream out;

        Socket sc = null;

        try {
            sc = new Socket(HOST, PORT);
            System.out.println("Cliente conectado");

            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
