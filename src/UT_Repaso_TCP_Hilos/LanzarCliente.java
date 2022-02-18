package UT_Repaso_TCP_Hilos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class LanzarCliente {

    public static void main(String[] args) throws InterruptedException {
        HiloCliente c1 = new HiloCliente();
        HiloCliente c2 = new HiloCliente();
        c1.start();
        c1.join();
        c2.start();
    }
}
