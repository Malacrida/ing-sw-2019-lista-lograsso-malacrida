package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.network.network_interface.ServerInterface;
import sun.plugin2.message.HeartbeatMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

public class ServerImplementationSocket extends Thread implements ServerInterface<Socket> {

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private String message;


    @Override
    public Boolean registerNewClient(Socket client, String nickname) throws RemoteException {
        try {
            this.output = new ObjectOutputStream(client.getOutputStream());
            System.out.println("Si è registrato " + nickname);
        }catch (IOException e){
            e.getCause();
        }
        return true;
    }

    @Override
    public void sendHeartBeat(HeartbeatMessage heartbeatMessage) throws RemoteException {
        //send(heartbeatMessage);
    }

    @Override
    public Boolean reconnectClient(Socket client, String nickname) throws RemoteException {
        return true;
    }
}