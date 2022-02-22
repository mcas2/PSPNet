package UT3_Repaso_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    static final String HOST = "127.0.0.1";
    static final int PORT = 1024;

    public static void main(String[] args) {
        String intento = "";
        String mensaje;
        Scanner sc = new Scanner(System.in);

        DataInputStream in;
        DataOutputStream out;
        System.out.println("Cliente activo.");

        Socket sk = null;

        try {
            sk = new Socket(HOST, PORT);
            in = new DataInputStream(sk.getInputStream());
            out = new DataOutputStream(sk.getOutputStream());
            System.out.println("Cliente conectado");

            //Recibimos longitud - R
            String inicio = in.readUTF();
            System.out.println(inicio);
            String [] array = inicio.split(":");
            int longitud = Integer.parseInt(array[1]);

            System.out.println("Introduce una palabra de "+longitud+" letras.");
            intento = comprobarLongitud(sc.nextLine(), longitud);

            //Enviamos la palabra - E
            out.writeUTF(intento);

            //Recibimos si es un acierto - R
            mensaje = in.readUTF();
            System.out.println(mensaje);

            while (!mensaje.equalsIgnoreCase("acierto")) {
                //E
                out.writeUTF(comprobarLongitud(sc.nextLine(), longitud));
                //R
                mensaje = in.readUTF();
                System.out.println(mensaje);
            }

            System.out.println("¡Enhorabuena! Has acertado.");

            in.close();
            out.close();
            sk.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String comprobarLongitud(String cadena, int longitud){
        Scanner sc = new Scanner(System.in);
        if (cadena.length()!=longitud) {
            do {
                System.out.println("¡Cuidado! La palabra tiene " + longitud + " letras. Introdúcela otra vez.");
                cadena = sc.nextLine();
            } while (cadena.length() != longitud);
        }
        return cadena;
    }
}
