package UT3_P1_E4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class AtenderCliente extends Thread {
    static final String HOST = "127.0.0.1";
    static final int PORT = 1024;

    DataInputStream in;
    DataOutputStream out;
    Socket sk;

    private String cadena;
    private boolean asterisco = false;

    public AtenderCliente(Socket sk, DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
        this.sk = sk;
    }

    @Override
    public void run() {
        try {
            while (!asterisco){
                System.out.println("Comunicación establecida.");

                in = new DataInputStream(sk.getInputStream());
                out = new DataOutputStream(sk.getOutputStream());

                while(!asterisco){
                    cadena = in.readUTF();
                    if (cadena.equals("*")) {
                        asterisco = true;
                    }
                    out.writeInt(cadena.length());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            in.close();
            out.close();
            sk.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
