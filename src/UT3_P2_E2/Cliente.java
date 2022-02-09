package UT3_P2_E2;

import java.io.IOException;
import java.net.*;
import java.sql.Time;

public class Cliente {
    final static int PORT_SERVER = 5000;

    public static void main(String[] args) {
        byte [] buffer;

        try {
            InetAddress serverAddress  = InetAddress.getLocalHost();

            DatagramSocket ds = new DatagramSocket();
            ds.setSoTimeout(5000);

            String mensaje = "Petición";

            buffer = mensaje.getBytes();
            buffer = new byte[1024];

            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length, serverAddress, PORT_SERVER);

            ds.send(peticion);


            long segundosInicio = System.currentTimeMillis();
            long segundosActuales = 0;



            DatagramPacket respuestaServidor = new DatagramPacket(buffer, buffer.length);

            ds.receive(respuestaServidor);

            String respuesta = new String(respuestaServidor.getData());
            System.out.println(respuesta);

        } catch (SocketException e) {
            e.printStackTrace();
        }
        catch (SocketTimeoutException e){
            System.out.println("El cliente no ha recibido respuesta del servidor.");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}