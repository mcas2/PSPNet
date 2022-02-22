package EjemploTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {

	 static final int PUERTO = 5000; 
	
	 public static void main(String[] args) {
		
		 //Flujo de mensajes
		DataInputStream in;
		DataOutputStream out;
		 
		ServerSocket servidor = null;
		Socket sc = null;
		
		try {
			
			System.out.println("Inicializado el servidor, esperando al cliente");
			servidor = new ServerSocket(PUERTO);
		
		
			while(true) {
				sc = servidor.accept();
				System.out.println("Comunicaci�n establecida");
				
				//Env�o y recepci�n de mensajes --> Creamos los flujos
				in = new DataInputStream(sc.getInputStream());
				out = new DataOutputStream(sc.getOutputStream());
				
				
				//Servidor escucha --> Lectura
				String mensaje = in.readUTF(); // Se queda esperando hasta recibir un mensaje del cliente
				System.out.println("Mensaje recibido de cliente: " +mensaje);
				
				//Enviamos respuesta al mensaje
				out.writeUTF("�Hola desde el servidor!");
				
				in.close();
				out.close();
				sc.close();
				
			}
		
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
