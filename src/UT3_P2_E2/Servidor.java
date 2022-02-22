package UT3_P2_E2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.time.LocalDateTime;

public class Servidor {
    final static int PORT = 5000;

    public static void main(String[] args) {
        byte [] buffer = new byte[1024];
        LocalDateTime now = LocalDateTime.now();

        try {
            System.out.println("Inicializando el servidor UDP");
            DatagramSocket socketUDP = new DatagramSocket(PORT);
            String mensaje;

            while (true){
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);

                mensaje = new String(peticion.getData());
                System.out.println("Recibiendo la petición del cliente..."+mensaje);

                int puertoCliente = peticion.getPort();
                InetAddress address = peticion.getAddress();

                String msgRespuesta = now.toString();
                buffer = msgRespuesta.getBytes();

                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, address, puertoCliente);
                socketUDP.send(respuesta);
                System.out.println("Enviada la respuesta");
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
