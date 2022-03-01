package ExamenMarioCastro.Ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
/*
 * Mario Castro Vélez - Examen PSP
 * */

public class Cliente {
    static final String HOST = "127.0.0.1";
    static final int PORT = 5002;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String intento;
        int cont = 1;

        DataInputStream in;
        DataOutputStream out;
        System.out.println("Cliente activo.");

        Socket sk = null;

        try {
            sk = new Socket(HOST, PORT);
            in = new DataInputStream(sk.getInputStream());
            out = new DataOutputStream(sk.getOutputStream());
            System.out.println("Cliente conectado");

            //Recibimos los intentos - R
            String inicio = in.readUTF();
            System.out.println(inicio);
            String [] array = inicio.split(":");
            int numIntentos = Integer.parseInt(array[1]);

            //Pedimos el número
            System.out.println("Introduce un número.");
            String mensaje = "";

            while (!mensaje.equalsIgnoreCase("Has acertado") && cont<=numIntentos) {
                //E
                intento = sc.nextLine();
                out.writeUTF(intento);

                //R
                mensaje = in.readUTF();
                System.out.println(mensaje);
                if (!mensaje.equalsIgnoreCase("Has acertado")){
                    System.out.println("Te quedan "+ (numIntentos-cont)+ " intentos.");
                    cont++;
                }
            }

            if (cont>numIntentos){
                out.writeUTF("gameover");
                System.out.println(in.readUTF());
            }

            in.close();
            out.close();
            sk.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
