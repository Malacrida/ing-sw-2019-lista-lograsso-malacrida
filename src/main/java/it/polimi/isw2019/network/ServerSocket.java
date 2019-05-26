package it.polimi.isw2019.network;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerSocket {

    private final int serverPort;
    private ServerSocket serverSocket;


    private ExecutorService executor = Executors.newFixedThreadPool(128); //Da capire

    /*private Map<String, ClientConnection> waitingConnection = new HashMap<>();

    private Map<ClientConnection, ClientConnection> playingConnection = new HashMap<>();*/

    /* Costruisce un Client gartherer su quella porta specifica*/

    public ServerSocket(int serverPort){
        this.serverPort = serverPort;
        this.serverSocket = new ServerSocket(serverPort);

    }

    /* Costruisce un client garthere usando quel ServerSocket specifico*/

    public ServerSocket(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
        this.serverPort = serverSocket.getLocalPort();
    }

    private int getLocalPort() {
        return serverPort;
    }


}
