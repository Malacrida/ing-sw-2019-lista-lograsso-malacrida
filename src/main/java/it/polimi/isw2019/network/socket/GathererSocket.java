package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.message.playermove.FirstMessage;
import it.polimi.isw2019.network.GathererInterface;
import it.polimi.isw2019.network.Lobby;
import it.polimi.isw2019.network.TypeConnection;
import it.polimi.isw2019.network.network_interface.ClientInterface;
import it.polimi.isw2019.network.rmi.VirtualViewRmi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

public class GathererSocket implements Runnable, GathererInterface {

    private final int serverPort;
    private Lobby lobby;
    private ServerSocket serverSocket;
    private FirstMessage firstMessage;

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

            LOGGER.info("Istanzio un nuovo thread");
            /*Istanzio un nuovo Thread*/
            Thread thread = new Thread(this);
            thread.start();

            LOGGER.info("Provo a inviare la player move");
            clientHandlerSocket(newConnection);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void clientHandlerSocket(Socket connection) throws IOException {
        ObjectOutputStream output;
        ObjectInputStream input;
        ClientInterface newClientInterface = new ClientSocket(connection);


        try{

            output = ((ClientSocket) newClientInterface).getObjectOutputStream();
            input = ((ClientSocket) newClientInterface).getObjectInputStream();
            //salva il messaggio che è arrivato dal client(nickname)
            String messageInput = (String) input.readObject();

            //aggiunge i client alla lobby
            lobby.addConnectedClient(messageInput, newClientInterface, TypeConnection.SOCKET);
            /*
            for (int i = 0; i < lobby.getClientConnected().size(); i++){
                System.out.println(lobby.getClientConnected());
            }*/

            //messaggio di output per la registrazione avvenuta
            output.writeObject( "REGISTRAZIONE AVVENUTA DA: " + messageInput);
            output.flush();
            output.reset();

            //setto il nickname nella client interface
            newClientInterface.setNickname(messageInput);
            //faccio partire il clientSocket per l'invio e la ricezione di MoveMessage e PlayerMoe
            ((ClientSocket) newClientInterface).start();

        } catch (IOException | ClassNotFoundException e) {
            e.getCause();
        }

    }

    @Override
    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    @Override
    public void setVirtualViewRmis(ArrayList<VirtualViewRmi> virtualViewRmis) {

    }
}
