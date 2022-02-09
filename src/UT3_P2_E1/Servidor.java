package UT3_P2_E1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Servidor {
    final static int PORT = 5000;

    public static void main(String[] args) {
        byte [] buffer = new byte[1024];

        try {
            System.out.println("Inicializando el servidor UDP");
            DatagramSocket socketUDP = new DatagramSocket(PORT);
            String mensaje;

            while (true){
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);
                System.out.println("Recibimos la petición del cliente");
                mensaje = new String(peticion.getData());
                System.out.println("El mensaje es: " + mensaje);

                int puertoCliente = peticion.getPort();
                InetAddress address = peticion.getAddress();

                String msgRespuesta = mensaje;
                buffer = msgRespuesta.getBytes();

                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, address, puertoCliente);
                System.out.println("Reenviamos el mensaje al cliente.");
                socketUDP.send(respuesta);
                System.out.println("Finalizada la comunicación UDP");
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
