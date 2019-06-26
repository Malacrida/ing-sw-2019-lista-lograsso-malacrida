package it.polimi.isw2019.network.network_interface;

//import sun.plugin2.message.HeartbeatMessage;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface<T> extends Remote {

    //notify

    public void registerNewClient(T client, String nickname) throws IOException;

    public void write(Object object) throws IOException;

    //public void sendHeartBeat(HeartbeatMessage heartbeatMessage) throws RemoteException;

    public Boolean reconnectClient(T client, String nickname) throws RemoteException;

}
