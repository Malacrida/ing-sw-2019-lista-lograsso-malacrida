package it.polimi.isw2019.network;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocket {

    private final int serverPort;
    private ServerSocket serverSocket;

    private ExecutorService executor = Executors.newFixedThreadPool(128); //Da capire

    public ServerSocket(int serverPort){
        this.serverPort = serverPort;
        this.serverSocket = new ServerSocket(serverPort);

    }

}
