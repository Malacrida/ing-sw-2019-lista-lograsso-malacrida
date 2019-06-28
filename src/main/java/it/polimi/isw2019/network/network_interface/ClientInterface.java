package it.polimi.isw2019.network.network_interface;


import it.polimi.isw2019.model.GameBoardInterface;
import it.polimi.isw2019.model.PlayerInterface;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInterface extends Remote {
    //update model

    //info card

    //altre info

    public void startViewClient () throws RemoteException;

    public void setNickname(String nickname) throws RemoteException;

    public String getNickname() throws RemoteException;

    public Boolean isYourTurn() throws RemoteException;

    //per fargli fare la scelta della modalità di gioco e mappa
    public void selectModeGameAndMap () throws RemoteException;

    //per attivare il giocatore nel suo turno
    public void startRound () throws RemoteException;

    public void logInCorrect () throws RemoteException;

    public void logInFail () throws RemoteException;

    public void createActionMessage(String nickname) throws RemoteException;

    public void createSetupView(String idMoveMessage, ArrayList<String> colorAvailable) throws RemoteException;

    public void createRun(String nicknamePlayer, String error, int numMovement) throws RemoteException;

    public void createGrab(String nicknamePlayer) throws RemoteException;

    public void createReload(String nicknamePlayer, ArrayList<WeaponCardInterface> weaponCardInterfaces) throws RemoteException;

    public void createUpdateView(String nicknamePlayer, GameBoardInterface gameBoard, ArrayList<PlayerInterface> players,boolean notifyAll) throws RemoteException;

    public void createOkRegistration(String nicknamePlayer, String actionHero, ArrayList<String> colors) throws RemoteException;

    public void createWaitForStart(String nicknamePlayer) throws RemoteException;

    public void createUseWeaponCard(String nicknamePlayer) throws RemoteException;

    public void createPowerUpChoice(String nicknamePlayer) throws RemoteException;

    public void createUsePowerUpCard(String nicknamePlayer) throws RemoteException;

    public void createFirstPlayerChooseMap(String nicknamePlayer, String[] possibleMaps, ArrayList<String> colorAvailable) throws RemoteException;

    public void createFailRegistration(String nicknamePlayer) throws RemoteException;

}
