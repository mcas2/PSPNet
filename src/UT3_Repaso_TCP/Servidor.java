package UT3_Repaso_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    static final int PORT = 1024;

    public static void main(String[] args) {
        String adivinanza = "hola";
        String error = "";
        int cont = 0;

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

                //Enviamos la longitud - E
                out.writeUTF("El numero de caracteres de la palabra es:" + adivinanza.length());

                //Recibimos el primer intento - R
                String intento = in.readUTF();

                while (!intento.equalsIgnoreCase(adivinanza)){
                    //Enviamos mensaje de error - E
                    error = "Error. Pista: en la posición " + (cont+1) + " va la letra " + adivinanza.charAt(cont);
                    out.writeUTF(error);

                    //Recibimos intento - R
                    intento = in.readUTF();

                    cont++;
                    if (cont==adivinanza.length()){
                        cont = 0;
                    }
                }

                //Enviamos mensaje de acuerto - E
                String mensajeAcierto = "acierto";
                out.writeUTF(mensajeAcierto);

                in.close();
                out.close();
                sk.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
