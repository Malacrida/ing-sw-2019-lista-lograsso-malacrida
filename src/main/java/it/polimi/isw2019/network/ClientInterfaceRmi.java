package it.polimi.isw2019.network;

import java.rmi.RemoteException;

public interface ClientInterfaceRmi {


    //per fargli fare la scelta della modalità di gioco e mappa
    void selectModeGameAndMap () throws RemoteException;

    //per attivare il giocatore nel suo turno
    void startRound () throws RemoteException;


}
