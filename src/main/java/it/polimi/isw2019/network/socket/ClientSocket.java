package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.network.network_interface.ClientInterface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.RemoteException;

public class ClientSocket extends Thread implements ClientInterface {

    private Socket clientSocket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private boolean isReady = false;
    private InetAddress ip = clientSocket.getInetAddress();

    public ClientSocket(Socket clientSocket) throws IOException{
        this.clientSocket = clientSocket;
        this.input = new ObjectInputStream(clientSocket.getInputStream());
        this.output = new ObjectOutputStream(clientSocket.getOutputStream());
    }

    public ClientSocket (ObjectOutputStream output, ObjectInputStream input) throws IOException{ //aggiugere anche la lobby
        this.input = input;
        this.output = output;
    }

    public String convertIpToString(InetAddress ip){
        return ip.toString();
    }


    @Override
    public Boolean isYourTurn() throws RemoteException {
        return true;
    }
}
