package UT3_Final;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    static final int PORT = 5000;

    public static void main(String[] args) throws IOException {
        DataInputStream in;
        DataOutputStream out;
        String nameThread;

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("El nuevo grupo ha sido creado.");

        while (true) {
            try {
                Socket sk = null;
                sk = serverSocket.accept();
                nameThread = in.readUTF();
                System.out.println(nameThread + " se ha unido al grupo.");


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
