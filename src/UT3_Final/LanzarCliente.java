package UT3_Final;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class LanzarCliente extends Thread{

    private String nombre;
    private Socket socket;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();
        DataInputStream in;
        DataOutputStream out;
        String mensaje;
        ArrayList<LanzarCliente> clientes;

        try{
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            this.nombre = in.readUTF();
            mensaje = this.nombre + " entró en el chat";
            clientes = Servidor.clientList;
            for (LanzarCliente l: clientes) {
                out = new DataOutputStream(l.getSocket().getOutputStream());
                out.writeUTF(mensaje);
            }

            while (!mensaje.equalsIgnoreCase("exitserver")){
                mensaje = in.readUTF();

                if (mensaje.equalsIgnoreCase("exitserver")){
                    mensaje = this.nombre + " : " + mensaje;
                    clientes = Servidor.clientList;
                    for (LanzarCliente l: clientes) {
                        out = new DataOutputStream(l.getSocket().getOutputStream());
                        out.writeUTF(mensaje);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
