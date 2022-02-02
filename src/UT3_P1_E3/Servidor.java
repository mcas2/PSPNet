package UT3_P1_E3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    static final int PORT = 1024;

    public static void main(String[] args) {
        String cadena;
        DataInputStream in;
        DataOutputStream out;

        ServerSocket servidor = null;
        Socket sk = null;

        try {
            System.out.println("Inizializado el servidor, esperando al cliente...");
            servidor = new ServerSocket(PORT);

            while (true){
                sk = servidor.accept();
                System.out.println("Comunicación establecida.");

                in = new DataInputStream(sk.getInputStream());
                out = new DataOutputStream(sk.getOutputStream());

                cadena = in.readUTF();

                while(!cadena.equalsIgnoreCase("*")){
                    out.writeInt(cadena.length());
                    cadena = in.readUTF();
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