package UT3_Repaso_UDP;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Servidor {
    static final int PORT = 5001;
    private static String adivinanza = "hola";

    /*
    * NOTAS IMPORTANTE PARA UDP:
    *  Reiniciar el buffer antes de RECIBIR, no de enviar
    *  Comentar R y E para respuestas y envíos
    *  El método .trim() se ha de usar en la misma línea en la que se crea el nuevo String
    *  Ejemplo: String saludoCliente = new String(peticion.getData()).trim();
     * */

    public static void main(String[] args) {
        byte [] buffer = new byte[1024];
        String resultado="";
        int cont = 0;

        try {
            System.out.println("Inicializando el servidor UDP");
            DatagramSocket socketUDP = new DatagramSocket(PORT);

            while(true) {

                //Llega la primera petición del cliente, creamos un paquete para que nos llegue el mensaje - R
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);
                String saludoCliente = new String(peticion.getData()).trim();
                System.out.println("Cliente conectado:" + saludoCliente);

                //Establecemos el canal de comunicación con la información del cliente
                int puertoCliente = peticion.getPort();
                InetAddress address = peticion.getAddress();

                //Lanzamos la primera respuesta del servidor - E
                String mensaje = "Hola cliente, la longitud de la palabra es -" + adivinanza.length();
                buffer = mensaje.getBytes();
                DatagramPacket initialMsg = new DatagramPacket(buffer, buffer.length, address, puertoCliente);
                socketUDP.send(initialMsg);

                //Recibimos el primer intento - R
                buffer = new byte[1024];
                peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);
                String intento = new String(peticion.getData()).trim();

                while (!intento.equalsIgnoreCase(adivinanza)){ //Mientras no se acierte
                    //Enviar mensaje de error - E
                    resultado = "Error. Pista: la letra " + (cont+1) + " es " + adivinanza.charAt(cont);
                    buffer = resultado.getBytes();
                    DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, address, puertoCliente);
                    socketUDP.send(respuesta);

                    //Recibir intento para comprobar - R
                    buffer = new byte[1024];
                    peticion = new DatagramPacket(buffer, buffer.length);
                    socketUDP.receive(peticion);
                    intento = new String(peticion.getData()).trim();

                    cont++;
                    if (cont==adivinanza.length()){
                        cont = 0;
                    }
                }

                //Enviamos el mensaje de éxito - E
                resultado = "acierto";
                buffer = resultado.getBytes();
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, address, puertoCliente);
                socketUDP.send(respuesta);

            }
        }catch(SocketException e) {
            e.printStackTrace();
        }catch(IOException e1) {
            e1.printStackTrace();
        }
    }
}

