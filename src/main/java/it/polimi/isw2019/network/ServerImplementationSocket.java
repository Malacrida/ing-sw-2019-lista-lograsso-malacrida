package it.polimi.isw2019.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import static java.lang.System.out;

public class ServerImplementationSocket implements Runnable {

    private static int port;
    private static ServerSocket serverSocket;

    private ExecutorService executor = Executors.newFixedThreadPool(128);
    private Map<String, ClientConnection> waitingConnection = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(ServerImplementationSocket.class.getName());

    public ServerImplementationSocket(int serverPort) throws IOException {
        this.port = serverPort;
        try {
            this.serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            e.getCause();
        }
    }

    public ServerImplementationSocket(ServerSocket server) {
        this.serverSocket = server;
        this.port = server.getLocalPort();
    }

    @Override
    public void run() {
        Socket newConnection = null;
        ClientInterfaceRmi newClient;
        ObjectOutputStream output;
        LOGGER.info("Creazione di un nuovo thread con id: " + Thread.currentThread().getId() + "\nIn ascolto sulla porta: " + port);
        try {

            newConnection = serverSocket.accept();
            startNewClientSocket();
            registerClient(newConnection);

        } catch (IOException e) {
            throw new RuntimeException("Impossibile connettersi alla porta" + port, e);
        }
        new ServerImplementationSocket(serverSocket);
    }

    private void startNewClientSocket(){
        Thread newThread = new Thread(this);
        newThread.start();
    }

    private void registerClient (Socket clientConnection) throws IOException {

        ObjectInputStream input;
        ObjectOutputStream output;
        ClientInterfaceRmi newClient;
        String nickname;

        Boolean reconnected;
        final String clientInput;

        try{
            input = new ObjectInputStream(clientConnection.getInputStream());
            output = new ObjectOutputStream(clientConnection.getOutputStream());
            sendMessage("Benvenuto! Inserisci il tuo nick: ");

            //clientInput = (String) ObjectInputStream.readObject();

        } catch (IOException | RuntimeException e){
            e.getCause();
        }


    }
    private void sendMessage(String message) {
        out.println(message);
        out.flush();
    }

}
