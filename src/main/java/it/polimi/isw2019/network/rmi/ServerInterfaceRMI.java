package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.view.VisitorView;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterfaceRMI extends Remote {

    void addToTheServer(String name, NetworkHandlerInterface networkHandler) throws RemoteException;

    void removeToTheClient(String name) throws RemoteException;

    void receiveChooseActionMove(String player, int numAction) throws RemoteException;

    void receiveChooseMap(String player, int index, int color) throws RemoteException;

    void receiveRun(String player,int[][] movement) throws RemoteException;

    void receiveGrab(String player)throws RemoteException;

    void receiveRegisterPlayer( String player, String actionHero) throws RemoteException;

    void receiveReload(String player) throws RemoteException;

    void receivePowerUpChoice(String player, int idPowerUp) throws RemoteException;

    void receiveUsePowerUpCard(String player, InterfacePowerUpCard powerUpCardInterface) throws RemoteException;

    void receiveWeaponCardChoice(String player, int indexWeaponCard, String[] payment, ArrayList<InterfacePowerUpCard> powerUpCards, boolean grab) throws RemoteException;

    void receiveUseWeaponCard(String player, WeaponCardInterface weaponCard) throws RemoteException;

}
