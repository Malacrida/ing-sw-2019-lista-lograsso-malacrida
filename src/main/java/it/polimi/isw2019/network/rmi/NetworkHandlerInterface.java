package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.model.GameBoardInterface;
import it.polimi.isw2019.model.PlayerInterface;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface NetworkHandlerInterface extends Remote {

    public void createActionMessage(String nickname) throws RemoteException;

    public void createSetupView(String idMoveMessage, ArrayList<String> colorAvailable) throws RemoteException;

    public void createRun(String nicknamePlayer, String error, int numMovement) throws RemoteException;

    public void createGrab(String nicknamePlayer) throws RemoteException;

    public void createReload(String nicknamePlayer, ArrayList<WeaponCardInterface> weaponCardInterfaces) throws RemoteException;

    public void createUpdateView(String nicknamePlayer, GameBoardInterface gameBoard, ArrayList<PlayerInterface> players) throws RemoteException;

    public void createOkRegistration(String nicknamePlayer, String actionHero, ArrayList<String> colors) throws RemoteException;

    public void createWaitForStart(String nicknamePlayer) throws RemoteException;

    public void createUseWeaponCard(String nicknamePlayer) throws RemoteException;

    public void createPowerUpChoice(String nicknamePlayer) throws RemoteException;

    public void createUsePowerUpCard(String nicknamePlayer) throws RemoteException;

    public void createFirstPlayerChooseMap(String nicknamePlayer) throws RemoteException;

    public void createFailRegistration(String nicknamePlayer) throws RemoteException;
}
