package ExamenMarioCastro.Ejercicio2_Hilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*
 * Mario Castro Vélez - Examen PSP
 * */
public class Servidor {
    static final int PORT = 5000;

    public static void main(String[] args) throws IOException {
        ArrayList<Thread> threadCollection = new ArrayList<>();
        String cadena;
        String nameThread;
        DataInputStream in;
        DataOutputStream out;
        int contador = 0;

        ServerSocket servidor = new ServerSocket(PORT);
        System.out.println("Servidor en pie. Esperando a los clientes.");

        while (true) {
            try {
                Socket sk = null;
                sk = servidor.accept();
                System.out.println("Cliente conectado." + sk);

                nameThread = "Hilo" + contador;
                in = new DataInputStream(sk.getInputStream());
                out = new DataOutputStream(sk.getOutputStream());

                ExamenMarioCastro.Ejercicio2_Hilos.AtenderCliente ac = new ExamenMarioCastro.Ejercicio2_Hilos.AtenderCliente(nameThread, sk);
                threadCollection.add(ac);
                contador++;

                ac.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

