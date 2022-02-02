package UT3_P1_E4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

public class Servidor {
    static final int PORT = 1024;

    public static void main(String[] args) throws IOException {
        ArrayList<Thread> threadCollection = new ArrayList<>();
        String cadena;
        String nameThread;
        DataInputStream in;
        DataOutputStream out;
        int contador = 0;

        ServerSocket servidor = new ServerSocket(PORT);
        System.out.println("Servidor en pie. Esperando al cliente.");

        while (true) {
            try {
                Socket sk = null;
                sk = servidor.accept();
                System.out.println("Cliente conectado." + sk);

                nameThread = "Hilo" + contador;
                in = new DataInputStream(sk.getInputStream());
                out = new DataOutputStream(sk.getOutputStream());

                AtenderCliente ac = new AtenderCliente(nameThread, sk);
                threadCollection.add(ac);
                contador++;

                ac.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}