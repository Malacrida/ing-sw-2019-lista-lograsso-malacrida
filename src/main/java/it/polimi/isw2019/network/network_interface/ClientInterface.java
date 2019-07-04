package it.polimi.isw2019.network.network_interface;

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

    public void reconnectionClient () throws RemoteException;

    public Boolean isYourTurn() throws RemoteException;

    //per fargli fare la scelta della modalità di gioco e mappa
    public void selectModeGameAndMap () throws RemoteException;

    //per attivare il giocatore nel suo turno
    public void startRound () throws RemoteException;

    public void logInCorrect () throws RemoteException;

    public void logInFail () throws RemoteException;

    public void createActionMessage(String nicknamePlayer,String[] actionYouCanPerform,ArrayList<String> actionPlayerCanPerform,ArrayList<Integer> intIdAction)throws RemoteException;

    public void createRun(String nicknamePlayer, String error, int numMovement) throws RemoteException;

    public void createGrab(String nicknamePlayer, String error,String[] weaponCardAvailable,int[] featuresAvailable,boolean grabWeapon, String ammoTileDescription, String powerUpDescription) throws RemoteException;

    public void createReload(String nicknamePlayer, int[] featuresAvailable, int[] weaponYouCanReload, String error) throws RemoteException;

    public void createUpdateView(String nicknamePlayer,String gameBoardDescription, int[][] playersDescription, int[][] featuresOfPlayersAvailable,String[][] weaponCardDescription, String[][] powerUpCardDescription,boolean notifyAll) throws RemoteException;

    public void createWaitForStart(String nicknamePlayer) throws RemoteException;

    public void createUseWeaponCardMessage(String nicknamePlayer,int[] weaponCard, int[] featuresAvailable, int[][] playersToAttack, String error)throws RemoteException;

    public void createUsePowerUpCard(String nicknamePlayer, int[] featuresAvailable,boolean[] stateCard, int stateGame, boolean attacked, int[] effectCard, String error) throws RemoteException;

    public void createFirstPlayerChooseMap(String nicknamePlayer, int idPlayer, String[] possibleMaps, ArrayList<String> colorAvailable) throws RemoteException;

    public void createChoiceCard(String nicknamePlayer, String[] descriptionPowerUp, int[] idPowerUp, String error, boolean discardOne, boolean isPowerUp ) throws RemoteException ;


}
