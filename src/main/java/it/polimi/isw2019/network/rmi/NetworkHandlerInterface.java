package it.polimi.isw2019.network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NetworkHandlerInterface extends Remote {

    public void createActionMessage(String nickname) throws RemoteException;
}
