package UT3_P2_E3;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    final static int PORT_SERVER = 5000;

    public static void main(String[] args) {
        String cadena = "";
        byte [] buffer;
        Scanner sc = new Scanner(System.in);

        try {
            InetAddress serverAddress  = InetAddress.getLocalHost();

            System.out.println("Introduce tantas cadenas como desees.");
            DatagramSocket ds = new DatagramSocket();


            while (!cadena.equalsIgnoreCase("*")){
                cadena = sc.nextLine();

                buffer = new byte[1024];
                buffer = cadena.getBytes();
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length, serverAddress, PORT_SERVER);
                ds.send(peticion);

                DatagramPacket respuestaServidor = new DatagramPacket(buffer, buffer.length);
                ds.receive(respuestaServidor);

                String respuesta = new String(respuestaServidor.getData()).trim();
                respuesta = Integer.toString(respuesta.length());
                if (!respuesta.equalsIgnoreCase("*")) {
                    System.out.println("Tiene "+respuesta+" caracteres.");
                }
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