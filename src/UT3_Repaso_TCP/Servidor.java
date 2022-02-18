package UT3_Repaso_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    static final int PORT = 1024;

    public static void main(String[] args) {
        String palabra = "hola";
        String checkOut = "false";

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

                for (int i = 0; i < palabra.length() && !checkOut.equalsIgnoreCase("true"); i++) {
                    System.out.println("se mete "+i);
                    out.writeUTF("Error. La posición " + i + " es una " + palabra.charAt(i));

                    if (intento.equalsIgnoreCase(palabra)){
                        checkOut = "true";
                        out.writeUTF(checkOut);
                    } else{
                        checkOut = "false";
                        out.writeUTF(checkOut);
                        intento = in.readUTF();
                    }
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
