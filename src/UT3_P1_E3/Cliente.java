package UT3_P1_E3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
    static final String HOST = "127.0.0.1";
    static final int PORT = 1024;

    public static void main(String[] args) {
        String cadena;
        Scanner sc = new Scanner(System.in);
        boolean asterisco = false;
        int index = 0;

        DataInputStream in;
        DataOutputStream out;
        System.out.println("...");

        Socket sk = null;

        try {
            sk = new Socket(HOST, PORT);
            System.out.println("Cliente conectado");
            System.out.println("Introduce tantas cadenas como desees. Se separarán con saltos de línea." +
                    " Introduce un asterisco para enviarlas todas.");

            in = new DataInputStream(sk.getInputStream());
            out = new DataOutputStream(sk.getOutputStream());

            while(asterisco!=true){
                cadena = sc.nextLine();
                if (cadena.equals("*")){
                    asterisco = true;
                }
                out.writeUTF(cadena);
                if (asterisco == true){
                    in.readInt();
                    System.out.println("Finalizado");
                } else {
                System.out.println("Tiene "+in.readInt()+" caracteres.");
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
