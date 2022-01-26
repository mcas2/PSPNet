package UT3_P1_E2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    static final String HOST = "127.0.0.1";
    static final int PORT = 1024;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String suma, palabra;

        DataInputStream in;
        DataOutputStream out;
        System.out.println("...");

        Socket sk = null;
        System.out.println("Introduce un número: ");
        String num = sc.next();

        try {
            sk = new Socket(HOST, PORT);
            System.out.println("Cliente conectado");

            in = new DataInputStream(sk.getInputStream());
            out = new DataOutputStream(sk.getOutputStream());

            int numero = Integer.parseInt(num);
            out.writeInt(numero);

            for (int i = 0; i < numero; i++) {
                System.out.println("Escribe una palabra: ");
                palabra = sc.next();
                out.writeUTF(palabra);
                suma = in.readUTF();
                System.out.println(suma);
            }

            System.out.println(in.readUTF());

            in.close();
            out.close();
            sk.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
