package UT3_P1_E2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    static final int PORT = 1024;

    public static void main(String[] args) {
        DataInputStream in;
        DataOutputStream out;

        ServerSocket servidor = null;
        Socket sk = null;
        int suma = 0;

        try {
            System.out.println("Inizializado el servidor, esperando al cliente...");
            servidor = new ServerSocket(PORT);

            while (true){
                sk = servidor.accept();
                System.out.println("Comunicación establecida.");

                //Flujos de recepción y envío de mensajes
                in = new DataInputStream(sk.getInputStream());
                out = new DataOutputStream(sk.getOutputStream());

                //Lectura del servidor
                int numero = in.readInt(); // lee el número

                for (int i = 0; i < numero; i++) {
                    String palabra = in.readUTF();
                    for (int j = 0; j < palabra.length(); j++) {
                        int letra = (int) palabra.charAt(j);
                        suma = letra + suma;
                    }
                    out.writeUTF(suma+"");
                    suma = 0;
                }

                out.writeUTF("Servidor ha terminado.");

                in.close();
                out.close();
                sk.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}