package UT3_P2_E3;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    final static int PORT_SERVER = 5000;

    public static void main(String[] args) {
        byte [] buffer;
        Scanner sc = new Scanner(System.in);

        try {
            //HAY QUE METER UN WHILE PARA ENVIAR LOS MENSAJES Y UN SCANNER PARA ESCRIBIRLOS

            InetAddress serverAddress  = InetAddress.getLocalHost();

            System.out.println("Introduce una cadena.");
            String cadena = sc.nextLine();

            while (!cadena.equalsIgnoreCase("*")){
                DatagramSocket ds = new DatagramSocket();
                buffer = cadena.getBytes();
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length, serverAddress, PORT_SERVER);
                ds.send(peticion);

                DatagramPacket respuestaServidor = new DatagramPacket(buffer, buffer.length);
                ds.receive(respuestaServidor);
                String respuesta = new String(respuestaServidor.getData());
                System.out.println(respuesta);

                System.out.println("Introduce una cadena.");
                cadena = sc.nextLine();
               //CAMBAIR ESTO buffer = new byte[1024];
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}