package EjemploUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ClienteUDP {	
	final static int PORT_SERVER = 5000;
	
	public static void main(String args[]) {
		byte [] buffer = new byte[1024];
		
		try {
		InetAddress address_Server = InetAddress.getLocalHost();
		//InetAddress address_Server = InetAddress.getByName("localhost");
		
		DatagramSocket ds = new DatagramSocket();
		
		String mensaje = "Hola soy el cliente";
		
		buffer = mensaje.getBytes();
		
		DatagramPacket peticion = new DatagramPacket(buffer, buffer.length,
				address_Server, PORT_SERVER);
		
		ds.send(peticion);
		
		buffer = new byte[1024];

		// creamos contentenedor para recibir el mensaje de respuesta
		DatagramPacket respuestaServidor = new DatagramPacket(buffer, buffer.length);
		ds.receive(respuestaServidor); 
		
		String respuestaSer = new String(respuestaServidor.getData());
		System.out.println(respuestaSer);
			
		}catch(SocketException e) {
			e.printStackTrace();
			
		}catch(IOException e1) {
			
		}
	}


}
