package it.polimi.isw2019.network;

import it.polimi.isw2019.message.playermove.PlayerMove;

import java.rmi.RemoteException;

public interface ServerInterfaceRMI {

    void addToTheServer(String name, ClientInterfaceRmi clientInterfaceRmi) throws RemoteException;

    void removeToTheClient(String name) throws RemoteException;

    //Per connettere alla partita
    void addGame ();

    //per ricevere la mossa fatta dal player forse è meglio farne più di uno
    //Forse come parametro in ingresso mettere una plyer move in modo da semplificarmi la vita
    void reciveMove (PlayerMove playerMove);

    //Possiamo mettere un metodo che manda messa

}
