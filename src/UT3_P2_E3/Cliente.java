package UT3_P2_E3;
import java.io.IOException;
import java.net.*;

public class Cliente {
    final static int PORT_SERVER = 5000;

    public static void main(String[] args) {
        byte [] buffer;

        try {
            //HAY QUE METER UN WHILE PARA ENVIAR LOS MENSAJES Y UN SCANNER PARA ESCRIBIRLOS

            InetAddress serverAddress  = InetAddress.getLocalHost();

            DatagramSocket ds = new DatagramSocket();

            String mensaje = "Soy el cliente que vuelva esto";

            buffer = mensaje.getBytes();

            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length, serverAddress, PORT_SERVER);

            ds.send(peticion);

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