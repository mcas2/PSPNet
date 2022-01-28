package UT3_P1_E4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    static final String HOST = "127.0.0.1";
    static final int PORT = 1024;

    public static void main(String[] args) {
        AtenderCliente c1 = new AtenderCliente();
        AtenderCliente c2 = new AtenderCliente();
        AtenderCliente c3 = new AtenderCliente();



        System.out.println("...");

        Socket sk = null;

        try {
            c1.start();
            c2.start();
            c3.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
