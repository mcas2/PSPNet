package ExamenMarioCastro.Ejercicio1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/*
* Mario Castro Vélez - Examen PSP
* */

public class Cliente {
    static final String HOST = "127.0.0.1";
    static final int PORT = 5001;

    public static void main(String args[]) {
        byte[] buffer = new byte[1024];
        Scanner sc = new Scanner(System.in);

        try {
            //Creamos el flujo de comunicación
            InetAddress address_Server = InetAddress.getLocalHost();
            DatagramSocket ds = new DatagramSocket();

            //Enviamos los 2 números para establecer la comunicación con el servidor - E
            System.out.println("Introduce 2 números separados por un espacio.");
            String initialMsg = sc.nextLine();
            buffer = initialMsg.getBytes();
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length, address_Server, PORT);
            ds.send(peticion);

            //Creamos contentenedor para recibir el primer mensaje del servidor - R
            buffer = new byte[1024];
            DatagramPacket respuestaServidor = new DatagramPacket(buffer, buffer.length);
            ds.receive(respuestaServidor);
            String respuestaSer = new String(respuestaServidor.getData()).trim();
            System.out.println(respuestaSer);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}


