package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.message.playermove.PlayerMove;
import it.polimi.isw2019.network.network_interface.ClientInterface;

import java.rmi.RemoteException;

public interface ServerInterfaceRMI {

    void addToTheServer(String name, ClientInterface clientInterface) throws RemoteException;

    void removeToTheClient(String name) throws RemoteException;

    //Per connettere alla partita
    void addGame ();

    //per ricevere la mossa fatta dal player forse è meglio farne più di uno
    //Forse come parametro in ingresso mettere una plyer move in modo da semplificarmi la vita
    void receiveMove(PlayerMove playerMove);

    void receiveChooseActionMove(String nickname, String idPlayer, int numAction);

}
