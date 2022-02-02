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

    private boolean asterisco = false;

    public AtenderCliente(String n, Socket sk) throws IOException {
        setName(n);
        this.sk = sk;
        this.in = new DataInputStream(sk.getInputStream());
        this.out = new DataOutputStream(sk.getOutputStream());
    }

    @Override
    public void run() {
        String cadena;
        try {
            while (!asterisco){
                cadena = in.readUTF();
                    if (cadena.equals("*")) {
                        asterisco = true;
                    }
                    out.writeInt(cadena.length());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
