package UT3_Repaso_1;

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
        String ayuda = "";
        boolean acierto = false;
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

            String inicio = in.readUTF();
            System.out.println(inicio);

            int palabra = Integer.parseInt(inicio.substring(42));
            System.out.println(palabra);
            System.out.println("Introduce una palabra de la misma longitud");
            intento = sc.nextLine();

            while (intento.length()!=palabra) {
                System.out.println("Error. Tu palabra no tiene la misma longitud.");
                intento = sc.nextLine();
            }


            while(!acierto){
                out.writeUTF(intento);
                String check = in.readUTF();
                if (check.equalsIgnoreCase("true")){
                    acierto = true;
                } else {
                    acierto = false;
                }
                ayuda = in.readUTF();
                System.out.println(ayuda);
                System.out.println("Prueba otra vez.");
                intento = sc.nextLine();
            }

            String mensajeAcierto = in.readUTF();
            System.out.println(mensajeAcierto);

            in.close();
            out.close();
            sk.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
