package it.polimi.isw2019.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImplementationServerSocket {

    private static int serverPort;
    private static ServerSocket server;
    private static boolean isRunning = true;

    private ExecutorService executor = Executors.newFixedThreadPool(128); //Da capire

    public ImplementationServerSocket(int serverPort) throws IOException {
        this.serverPort = serverPort;
        try {
            this.server = new ServerSocket(serverPort);
        } catch (IOException e) {
            e.getCause();
        }
    }

    public ImplementationServerSocket(ServerSocket server) {
        this.server = server;
        this.serverPort = server.getLocalPort();
    }

    public static void main(String args[]) throws IOException {
        serverPort = 12345;

        System.out.println("Creating server socket on port " + serverPort);
        new ImplementationServerSocket(serverPort);
        new ImplementationServerSocket(server);


        while (isRunning) {

            Socket socket = server.accept();
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println("What's you name?");

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String str = br.readLine();

            pw.println("Hello, " + str);


            System.out.println("Just said hello to:" + str);
            pw.close();
            socket.close();
        }

    }
}
