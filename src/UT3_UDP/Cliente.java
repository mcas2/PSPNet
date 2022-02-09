package UT3_UDP;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class Cliente {
    final static int PORT_SERVER = 5000;

    public static void main(String[] args) {
        byte [] buffer = new byte[1024];

        try {
            InetAddress serverAddress  = InetAddress.getLocalHost();

            DatagramSocket ds = new DatagramSocket();

            String mensaje = "Soy el cliente";

            buffer = mensaje.getBytes();

            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length, serverAddress, PORT_SERVER);

            ds.send(peticion);
            buffer = new byte[1024];

            DatagramPacket respuestaServidor = new DatagramPacket(buffer, buffer.length);
            ds.receive(respuestaServidor);

            String respuesta = new String(respuestaServidor.getData());
            System.out.println(respuesta);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
