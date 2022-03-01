package ExamenMarioCastro.Ejercicio2_Hilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
/*
 * Mario Castro Vélez - Examen PSP
 * */
public class AtenderCliente extends Thread{
    static final String HOST = "127.0.0.1";
    static final int PORT = 5000;

    DataInputStream in;
    DataOutputStream out;
    Socket sk;


    public AtenderCliente(String n, Socket sk) throws IOException {
        setName(n);
        this.sk = sk;
        this.in = new DataInputStream(sk.getInputStream());
        this.out = new DataOutputStream(sk.getOutputStream());
    }

    @Override
    public void run() {
        Random r = new Random();
        String check = "";
        int numAdivinar = r.nextInt(1,11);
        int numIntentos = r.nextInt(1,6);

        try {
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
