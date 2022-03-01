package ExamenMarioCastro.Ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
/*
 * Mario Castro Vélez - Examen PSP
 * */

public class Servidor {
    static final int PORT = 5002;

    public static void main(String[] args) {
        Random r = new Random();
        String check = "";
        int numAdivinar = r.nextInt(1,11);
        int numIntentos = r.nextInt(1,6);

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

                //Enviamos el número de intentos - E
                out.writeUTF("Adivina un número del 1 al 10. Intentos restantes :" + numIntentos);

                check = "";
                String intento = "";

                while (!check.equalsIgnoreCase("Has acertado")){
                    //Recibimos intento - R
                    intento = in.readUTF();

                    if (intento.equalsIgnoreCase("gameover")){
                        out.writeUTF("Te quedaste sin intentos");
                        //Este check en realidad no implica que se haya acertado, es sólamente la condición de salida del bucle.
                        check = "Has acertado";
                    } else {
                        //Enviamos mensaje de error - E
                        check = comprobarNumero(Integer.parseInt(intento), numAdivinar);
                        out.writeUTF(check);
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

    public static String comprobarNumero (int num, int numAdivinar){
        if (num>numAdivinar){
            return "Más bajo";
        } else if (num<numAdivinar){
            return "Más alto";
        } else {
            return "Has acertado";
        }
    }
}

