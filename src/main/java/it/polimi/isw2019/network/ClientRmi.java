package it.polimi.isw2019.network;

import it.polimi.isw2019.network.network_interface.ClientInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public class ClientRmi implements ClientInterface, Remote {

    @Override
    public Boolean isYourTurn() throws RemoteException {
        return null;
    }

    @Override
    public void selectModeGameAndMap() throws RemoteException {

    }

    @Override
    public void startRound() throws RemoteException {

    }

    @Override
    public void run() {

    }
}
