package UT3_Final;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
    static final int PORT = 5000;
    static ArrayList<LanzarCliente> clientList;

    public static void main(String[] args) throws IOException {
        DataInputStream in;
        DataOutputStream out;
        clientList = new ArrayList<LanzarCliente>();

        try {
            Socket sk = null;
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("El nuevo grupo ha sido creado.");

            while (true){
                sk = serverSocket.accept();
                System.out.println("Nuevo cliente");
                LanzarCliente lc = new LanzarCliente();
                clientList.add(lc);
                lc.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
