package EjemploUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorUDP {
	final static int PORT = 5000;
	
	public static void main(String args[]) {
		byte [] buffer = new byte[1024];
		
		try {
			System.out.println("Inicializando el servidor UDP");
			DatagramSocket socketUDP = new DatagramSocket(PORT);
			String mensaje;
			while(true) {
				//COntenedor para almacenar el mensaje que nos llegue del cliente
				DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
				socketUDP.receive(peticion); // NOS LLEVA EL MENSAJE DEL CLIENTE
				System.out.println("Recibimos la petici�n de cliente");
				mensaje = new String(peticion.getData());
				System.out.println("imprimimos el mensaje que ha llegado de"
						+ " cliente a servidor: "+mensaje);
				
				//INFO DE CLIENTE PARA PODER RESPONDER
				int puertoCliente = peticion.getPort();  
				InetAddress address = peticion.getAddress();
				
				String mensajeRespuesta = "Hola soy el servidor y te saludo cliente";
				buffer = mensajeRespuesta.getBytes();
				DatagramPacket respuesta = new DatagramPacket(buffer, 
						buffer.length, address, puertoCliente);
				System.out.println("Enviamos mensaje del servidor al cliente");
				socketUDP.send(respuesta);
				
				System.out.println("Finalizado la comunicación UDP");
				
			}
			
		}catch(SocketException e) {
			e.printStackTrace();
		}catch(IOException e1) {
			e1.printStackTrace();
		}
	}

}
