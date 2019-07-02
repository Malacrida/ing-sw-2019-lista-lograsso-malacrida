package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.message.playermove.FirstMessage;
import it.polimi.isw2019.network.GathererInterface;
import it.polimi.isw2019.network.Lobby;
import it.polimi.isw2019.network.network_interface.ClientInterface;
import it.polimi.isw2019.network.rmi.VirtualView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;
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

            System.out.println("Istanzio un nuovo thread");
            /*Istanzio un nuovo Thread*/
            Thread thread = new Thread(this);
            thread.start();

            System.out.println("Provo a inviare la player move");
            clientHandlerSocket(newConnection);

            /*ClientSocket cs = new ClientSocket(newConnection, output, input);
            cs.setLobby(lobby);
            cs.start();*/

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
            System.out.println(input);
            System.out.println(output);
            System.out.println("---IN ATTESA DI MESSAGGIO---");
            String messageInput = (String) input.readObject();

            System.out.println("---MESSAGGIO: "+ messageInput);

            newClientInterface.setNickname(messageInput);
            lobby.addClientConnected(messageInput, newClientInterface);

            for (int i = 0; i < lobby.getClientConnected().size(); i++){
                System.out.println(lobby.getClientConnected());
            }


            String outputString = "REGISTRAZIONE AVVENUTA DA: ";
            output.writeObject(outputString + messageInput);
            output.flush();
            output.reset();

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
    public void setVirtualViews(ArrayList<VirtualView> virtualViews) {

    }
}
