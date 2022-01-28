package UT3_P1_E4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    static final int PORT = 1024;

    public static void main(String[] args) {
        boolean asterisco = false;
        boolean hilosVivos = true;
        String hiloStatus;
        String cadena;
        DataInputStream in;
        DataOutputStream out;

        ServerSocket servidor = null;
        Socket sk = null;
        int suma = 0;

        try {
            System.out.println("Inizializado el servidor, esperando al cliente...");
            servidor = new ServerSocket(PORT);

            while (hilosVivos){
                sk = servidor.accept();
                System.out.println("Comunicación establecida.");

                //Flujos de recepción y envío de mensajes
                in = new DataInputStream(sk.getInputStream());
                out = new DataOutputStream(sk.getOutputStream());

                while(!asterisco){
                    cadena = in.readUTF();
                    if (cadena.equals("*")) {
                        asterisco = true;
                    }
                    out.writeInt(cadena.length());
                    hiloStatus = in.readUTF();

                    //Contar los hilos conectados y despues cerrar el servidor una vez se apaguen todos
                    if (hiloStatus.equalsIgnoreCase("Finalizado")){
                        hilosVivos
                    }
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