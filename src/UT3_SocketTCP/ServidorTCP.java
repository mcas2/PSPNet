package UT3_SocketTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class ServidorTCP {
    static final int PORT = 1024;

    public static void main(String[] args) {
        //Flujo de mensajes
        DataInputStream in;
        DataOutputStream out;

        ServerSocket servidor = null;
        Socket sc = null;

        try {
            System.out.println("Inizializado el servidor, esperando al cliente...");
            servidor = new ServerSocket(PORT);

            while (true){
                sc = servidor.accept();
                System.out.println("Comunicación establecida.");

                //Flujos de recepción y envío de mensajes
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                //Lectura del servidor
                String mensaje = in.readUTF(); //Se queda esperando hasta recibir un mensaje del cliente
                System.out.println("Mensaje: " + mensaje);

                //Enviamos respuesta al mensaje
                out.writeUTF("Respuesta programada del servidor.");

                in.close();
                out.close();
                sc.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
