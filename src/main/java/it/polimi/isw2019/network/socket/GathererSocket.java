package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.network.GathererInterface;
import it.polimi.isw2019.network.network_interface.ClientInterface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class GathererSocket implements Runnable, GathererInterface {

    private final int serverPort;

    private ServerSocket serverSocket;

    private static final Logger LOGGER = Logger.getLogger(GathererSocket.class.getName());

    public GathererSocket(int serverPort){
        this.serverPort = serverPort;
        try{
            this.serverSocket = new ServerSocket(serverPort);
        }catch (IOException e){
            LOGGER.warning(e.getMessage());
        }
    }

    public GathererSocket(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
        this.serverPort = serverSocket.getLocalPort();
    }

    public void run(){

        Socket newConnection = null;
        ClientInterface newClient;
        ObjectOutputStream output;

        try {
            newConnection = serverSocket.accept();

            /*Istanzio un nuovo Thread*/
            Thread thread = new Thread(this);
            thread.start();

            clientHandlerSocket(newConnection);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void clientHandlerSocket(Socket connection){
        ObjectOutputStream output;
        ObjectInputStream input;
        ClientInterface newClientInterface;
        String nickname;

        try{

            input = new ObjectInputStream(connection.getInputStream());
            output = new ObjectOutputStream(connection.getOutputStream());

            newClientInterface = new ClientSocket(output, input);

            output.writeObject("REGISTRAZIONE AVVENUTA CON SUCCESSO! HAI FATTO L'ACCESSO COME: " + output);


        } catch (IOException e) {
            e.getCause();
        }

    }

}
