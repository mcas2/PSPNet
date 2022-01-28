package UT3_P1_E4;

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
        boolean asterisco = false;
        String cadena;

        try {
            Socket sk = new Socket(HOST, PORT);
            DataInputStream in = new DataInputStream(sk.getInputStream());
            DataOutputStream out = new DataOutputStream(sk.getOutputStream());

            while (!asterisco) {
                System.out.println("Introduce las cadenas.");
                cadena = sc.nextLine();
                if (cadena.equals("*")) {
                    asterisco = true;
                }
                out.writeUTF(cadena);
                if (asterisco) {
                    in.readInt();
                    System.out.println("Finalizado");
                } else {
                    System.out.println("Tiene " + in.readInt() + " caracteres.");
                }
            }

            in.close();
            out.close();
            sk.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
