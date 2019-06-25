package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.network.GathererInterface;
import it.polimi.isw2019.network.Lobby;
import it.polimi.isw2019.network.network_interface.ClientInterface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.logging.Logger;

public class GathererSocket implements Runnable, GathererInterface {

    private final int serverPort;
    private Lobby lobby;
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
            output = new ObjectOutputStream(newConnection.getOutputStream());
            LOGGER.info("output: " + output);

            /*Istanzio un nuovo Thread*/
            Thread thread = new Thread(this);
            thread.start();

            clientHandlerSocket(newConnection);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void clientHandlerSocket(Socket connection) throws IOException {
        ObjectOutputStream output;
        ObjectInputStream input;
        ClientInterface newClientInterface = new ClientSocket(connection);
        String nickname;

        try{
            LOGGER.info("ClientHandler");
            output = ((ClientSocket) newClientInterface).getObjectOutputStream();
            input = ((ClientSocket) newClientInterface).getObjectInputStream();
            LOGGER.info("Sto attendendo un messaggio \n");
            String messageInput = (String) input.readObject();
            LOGGER.info("Mi è arrivato il messaggio: " + messageInput);
            newClientInterface.setNickname(messageInput);
            lobby.addClient(newClientInterface);

            for (int i = 0; i < lobby.getConnectedClients().size(); i++){
                System.out.println(lobby.getConnectedClients());
            }


            String outputString = "REGISTRAZIONE AVVENUTA";
            output.writeObject(outputString);
            output.flush();
            output.reset();


        } catch (IOException | ClassNotFoundException e) {
            e.getCause();
        }

    }

    @Override
    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }
}
