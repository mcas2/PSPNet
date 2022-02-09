package UT3_P2_E3;

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
            Integer cuenta;

            while (true){
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);
                mensaje = new String(peticion.getData());

                int puertoCliente = peticion.getPort();
                InetAddress address = peticion.getAddress();


                while (!mensaje.equalsIgnoreCase("*")){
                    socketUDP.receive(peticion);
                    mensaje = new String(peticion.getData());


                    cuenta = mensaje.length();
                    String msgRespuesta = cuenta.toString();
                    buffer = msgRespuesta.getBytes();
                    DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, address, puertoCliente);
                    socketUDP.send(respuesta);

                }



                System.out.println("Finalizada la comunicación UDP");
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
