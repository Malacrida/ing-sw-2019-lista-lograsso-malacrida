package it.polimi.isw2019.network.network_interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInterface extends Remote {
    //update model

    //info card

    //altre info

    public Boolean isYourTurn() throws RemoteException;

    //per fargli fare la scelta della modalità di gioco e mappa
    void selectModeGameAndMap () throws RemoteException;

    //per attivare il giocatore nel suo turno
    void startRound () throws RemoteException;

}
