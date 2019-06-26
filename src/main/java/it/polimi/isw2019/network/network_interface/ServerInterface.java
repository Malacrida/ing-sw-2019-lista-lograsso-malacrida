package it.polimi.isw2019.network.network_interface;

//import sun.plugin2.message.HeartbeatMessage;

import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface<T> extends Remote {

    //notify

    public void registerNewClient(T client, String nickname) throws IOException,RemoteException;

    public void write(Object object) throws IOException,RemoteException;

    //public void sendHeartBeat(HeartbeatMessage heartbeatMessage) throws RemoteException;

    public void reconnectClient(T client, String nickname) throws RemoteException;

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