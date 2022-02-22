package EjemploTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteTCP {

	static final String HOST = "192.168.20.119";  // DOnde se encuentra el servidor
	static final int PORT = 5000;  // EL puerto donde se ha conectado el servidor
	
	public static void main(String[] args) {
		DataInputStream in;
		DataOutputStream out;

		Socket sc = null;
		
		try {
			sc = new Socket(HOST, PORT);
			System.out.println("Cliente conectado");  // El servidor ha realizado el accept
			
			//Env�o y recepci�n de mensajes --> Creamos flujos
			
			in = new DataInputStream(sc.getInputStream());
			out = new DataOutputStream(sc.getOutputStream());
			
			//Mandamos mensaje al servidor
			out.writeUTF("�Hola mundo de parte de Elisa!");
			
			//Leemos respuesta del servidor
			String mensaje = in.readUTF();
			System.out.println("Mensaje recibido del servidor: "+mensaje);
			
			in.close();
			out.close();
			sc.close();
			
				
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
