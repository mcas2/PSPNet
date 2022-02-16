package UT3_Repaso_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    static final int PORT = 1024;

    public static void main(String[] args) {
        String palabra = "hada";
        DataInputStream in;
        DataOutputStream out;

        ServerSocket servidor = null;
        Socket sk = null;

        try {
            System.out.println("Inizializado el servidor, esperando al cliente...");
            servidor = new ServerSocket(PORT);


            while (true){
                sk = servidor.accept();
                in = new DataInputStream(sk.getInputStream());
                out = new DataOutputStream(sk.getOutputStream());
                System.out.println("Comunicación establecida");
                out.writeUTF("El numero de caracteres de la palabra es: " + palabra.length());

                String intento = in.readUTF();

                for (int i = 0; i < palabra.length()&&!intento.equalsIgnoreCase(palabra); i++) {
                    intento = in.readUTF();
                    if (intento == palabra){
                        out.writeUTF("true");
                    } else{
                        out.writeUTF("false");
                    }
                    out.writeUTF("Error. La posición " + i + " es una " + palabra.charAt(i+1));
                }

                String mensajeAcierto = "¡El cliente ha acertado!";
                out.writeUTF(mensajeAcierto);
                System.out.println(mensajeAcierto);

                in.close();
                out.close();
                sk.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
