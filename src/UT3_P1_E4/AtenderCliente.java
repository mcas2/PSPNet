package UT3_P1_E4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class AtenderCliente extends Thread {
    static final String HOST = "127.0.0.1";
    static final int PORT = 1024;
    private DataInputStream in;
    private DataOutputStream out;
    private String cadena;
    private Scanner sc = new Scanner(System.in);
    private boolean asterisco = false;

    public AtenderCliente (){
    }
    
    @Override
    public void run() {
        try {
            Socket sk = new Socket(HOST, PORT);
            System.out.println("Cliente conectado");
            System.out.println("Introduce tantas cadenas como desees. Se separarán con saltos de línea." +
                    " Introduce un asterisco para enviarlas todas.");

            in = new DataInputStream(sk.getInputStream());
            out = new DataOutputStream(sk.getOutputStream());

            while (!asterisco) {
                cadena = sc.nextLine();
                if (cadena.equals("*")) {
                    asterisco = true;
                }
                out.writeUTF(cadena);
                if (asterisco) {
                    in.readInt();
                    out.writeUTF("Finalizado");
                    System.out.println("Finalizado");
                } else {
                    System.out.println("Tiene " + in.readInt() + " caracteres.");
                }
            }

            in.close();
            out.close();
            sk.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
