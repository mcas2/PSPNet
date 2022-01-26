package UT3_SocketTCP;

import javax.imageio.IIOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteTCP {
    static final String HOST = "127.0.0.1";
    static final int PORT = 1024;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataInputStream in;
        DataOutputStream out;
        System.out.println("...");
        String mensajePersonalizado = scanner.nextLine();

        Socket sc = null;

            try {
                sc = new Socket(HOST, PORT);
                System.out.println("Cliente conectado");

                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                out.writeUTF(mensajePersonalizado);
                String mensaje = in.readUTF();
                System.out.println("Mensaje recibido del servidor: "+mensaje);

                in.close();
                out.close();
                sc.close();

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

