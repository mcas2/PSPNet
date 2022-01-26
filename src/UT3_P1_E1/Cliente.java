package UT3_P1_E1;

import javax.imageio.IIOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.Scanner;

public class Cliente {
    static final String HOST = "127.0.0.1";
    static final int PORT = 1024;

    public static void main(String[] args) {
        DataInputStream in;
        DataOutputStream out;
        System.out.println("...");

        Socket sc = null;

        try {
            sc = new Socket(HOST, PORT);
            System.out.println("Cliente conectado");

            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            String mensaje = in.readUTF();
            String reenvio = mensaje.toLowerCase();
            System.out.println("Mensaje recibido del servidor: " + reenvio);


            out.writeUTF(reenvio);

            in.close();
            out.close();
            sc.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
