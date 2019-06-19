package it.polimi.isw2019.network.network_interface;

import sun.plugin2.message.HeartbeatMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface<T> extends Remote {

    //notify

    public Boolean registerNewClient(T client, String nickname) throws RemoteException;

    public void sendHeartBeat(HeartbeatMessage heartbeatMessage) throws RemoteException;

    public Boolean reconnectClient(T client, String nickname) throws RemoteException;

}
