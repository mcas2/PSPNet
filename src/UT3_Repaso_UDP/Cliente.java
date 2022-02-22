package UT3_Repaso_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

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

            //Enviamos el primer mensaje para establecer la comunicación con el servidor - E
            String initialMsg = "¡Cliente 01!";
            buffer = initialMsg.getBytes(); //El buffer es lo que se envía
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length, address_Server, PORT);
            ds.send(peticion);

            //Creamos contentenedor para recibir el primer mensaje del servidor - R
            buffer = new byte[1024];
            DatagramPacket respuestaServidor = new DatagramPacket(buffer, buffer.length);
            ds.receive(respuestaServidor);
            String respuestaSer = new String(respuestaServidor.getData()).trim();
            System.out.println(respuestaSer);


            String [] array = respuestaSer.split("-");
            int longitud = Integer.parseInt(array[1]);

            //Comprobamos la longitud e introducimos la palabra
            System.out.println("Introduce una palabra de " + longitud + " letras.");
            String intento;
            intento = sc.nextLine();
            intento = comprobarLongitud(intento, longitud);

            //Enviamos el primer mensaje - E
            buffer = new byte[1024];
            buffer = intento.getBytes();
            peticion = new DatagramPacket(buffer, buffer.length, address_Server, PORT);
            ds.send(peticion);

            //Recibimos si es cierto - R
            buffer = new byte[1024];
            respuestaServidor = new DatagramPacket(buffer, buffer.length);
            ds.receive(respuestaServidor);
            String resultado = new String(respuestaServidor.getData()).trim();

           while (!resultado.equalsIgnoreCase("acierto")){ //Si no lo es
                //Probamos de nuevo - E
                System.out.println(resultado);
                intento = sc.nextLine();
                intento =
                        comprobarLongitud(intento, longitud);
                buffer = intento.getBytes();
                peticion = new DatagramPacket(buffer, buffer.length, address_Server, PORT);
                ds.send(peticion);

                //Recibimos la respuesta - R
               buffer = new byte[1024];
               respuestaServidor = new DatagramPacket(buffer, buffer.length);
                ds.receive(respuestaServidor);
                resultado = new String(respuestaServidor.getData()).trim();
            }

            if (resultado.equalsIgnoreCase("acierto")){
                System.out.println("¡Enhorabuena, has acertado!");
            }

        } catch (SocketException e) {
            e.printStackTrace();

        } catch (IOException e1) {

        }
    }

    public static String comprobarLongitud(String cadena, int longitud){
        Scanner sc = new Scanner(System.in);
        if (cadena.length()!=longitud) {
            do {
                System.out.println("¡Cuidado! La palabra tiene " + longitud + " letras. Introdúcela otra vez.");
                cadena = sc.nextLine();
            } while (cadena.length() != longitud);
        }
        return cadena;
    }
}


