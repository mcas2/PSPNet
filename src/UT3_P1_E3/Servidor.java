package UT3_P1_E3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    static final int PORT = 1024;

    public static void main(String[] args) {
        boolean asterisco = false;
        String cadena;
        DataInputStream in;
        DataOutputStream out;

        ServerSocket servidor = null;
        Socket sk = null;
        int suma = 0;

        try {
            System.out.println("Inizializado el servidor, esperando al cliente...");
            servidor = new ServerSocket(PORT);

            while (asterisco!=true){
                sk = servidor.accept();
                System.out.println("Comunicación establecida.");

                //Flujos de recepción y envío de mensajes
                in = new DataInputStream(sk.getInputStream());
                out = new DataOutputStream(sk.getOutputStream());

                while(asterisco!=true){
                    cadena = in.readUTF();
                    if (cadena.equals("*")) {
                        asterisco = true;
                    }
                    out.writeInt(cadena.length());
                }

                in.close();
                out.close();
                sk.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}