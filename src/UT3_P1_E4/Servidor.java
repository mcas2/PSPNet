package UT3_P1_E4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    static final int PORT = 1024;

    public static void main(String[] args) throws IOException {
        boolean asterisco = false;
        String cadena;
        DataInputStream in;
        DataOutputStream out;

        ServerSocket servidor = new ServerSocket(PORT);
        Socket sk = null;
        System.out.println("Servidor en pie. Esperando al cliente.");

        while (true){
            try {
                sk = servidor.accept();
                System.out.println("Cliente conectado." + sk);

                in = new DataInputStream(sk.getInputStream());
                out = new DataOutputStream(sk.getOutputStream());

                AtenderCliente ac1 = new AtenderCliente(sk, in, out);
                AtenderCliente ac2 = new AtenderCliente(sk, in, out);
                AtenderCliente ac3 = new AtenderCliente(sk, in, out);

                ac1.start();
                ac2.start();
                ac3.start();

            } catch (IOException e) {
                sk.close();
                e.printStackTrace();
            }
        }


    }
}