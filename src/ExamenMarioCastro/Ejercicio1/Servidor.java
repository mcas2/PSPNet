package ExamenMarioCastro.Ejercicio1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/*
 * Mario Castro Vélez - Examen PSP
 * */

public class Servidor {
    static final int PORT = 5001;
    private static String adivinanza = "hola";

    public static void main(String[] args) {
        byte [] buffer = new byte[1024];
        int num1, num2, numFinal;

        try {
            System.out.println("Inicializando el servidor UDP");
            DatagramSocket socketUDP = new DatagramSocket(PORT);

            while(true) {
                buffer = new byte[1024];

                //Llegan los números del cliente, creamos un paquete para que nos llegue el mensaje - R
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);
                String mensajeRecibido = new String(peticion.getData()).trim();

                //Separamos los números y comprobamos
                String [] numeros = null;
                numeros = mensajeRecibido.split(" ");
                num1 = Integer.parseInt(numeros[0]);
                num2 = Integer.parseInt(numeros[1]);
                numFinal = comprobarValores(num1,num2);

                //Asignamos la respuesta a un string
                String respuestaServidor = String.valueOf(numFinal);
                if (numFinal==-1) respuestaServidor = "Son iguales";

                //Establecemos el canal de comunicación con la información del cliente
                int puertoCliente = peticion.getPort();
                InetAddress address = peticion.getAddress();

                //Lanzamos la respuesta del servidor - E
                buffer = respuestaServidor.getBytes();
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, address, puertoCliente);
                socketUDP.send(respuesta);

            }
        }catch(SocketException e) {
            e.printStackTrace();
        }catch(IOException e1) {
            e1.printStackTrace();
        }
    }

    public static int comprobarValores(int num1, int num2){
        if (num1>num2){
            return num1;
        } else if (num2>num1){
            return num2;
        } else return -1;
    }
}
