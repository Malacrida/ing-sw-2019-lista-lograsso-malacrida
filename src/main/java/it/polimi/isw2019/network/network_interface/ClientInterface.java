package it.polimi.isw2019.network.network_interface;

import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInterface extends Remote {

    /**
     * After registration on server start View in client
     * @throws RemoteException exception
     */
    public void startViewClient () throws RemoteException;


    /**
     * set nickname (socket)
     * @param nickname player's nickname
     * @throws RemoteException exception
     */
    public void setNickname(String nickname) throws RemoteException;

    /**
     * return player's nickname
     */
    public String getNickname() throws RemoteException;

    /**
     * Reconnection message
     * @throws RemoteException exception
     */
    public void reconnectionClient () throws RemoteException;

    /**
     * login correct message
     * @throws RemoteException exception
     */
    public void logInCorrect () throws RemoteException;

    /**
     * login fail message
     * @throws RemoteException exception
     */
    public void logInFail () throws RemoteException;

    /**
     * method to create actionMessage in the View
     * @param nicknamePlayer player's nickname
     * @param actionYouCanPerform action possible to do
     * @param actionPlayerCanPerform action possible to do
     * @param intIdAction id action
     * @throws RemoteException exceptiono
     */
    public void createActionMessage(String nicknamePlayer,String[] actionYouCanPerform,ArrayList<String> actionPlayerCanPerform,ArrayList<Integer> intIdAction)throws RemoteException;

    /**
     * method to create RunMessage in the View
     * @param nicknamePlayer player's nickname
     * @param error possible error
     * @param numMovement number of movement possible
     * @throws RemoteException exception
     */
    public void createRun(String nicknamePlayer, String error, int numMovement) throws RemoteException;

    /**
     * method to create GrabMove in the View
     * @param nicknamePlayer player's nickname
     * @param error possible error
     * @param weaponCardAvailable weapon card available
     * @param featuresAvailable features available
     * @param grabWeapon possible grab of weapon
     * @param ammoTileDescription description ammo tile
     * @param powerUpDescription description power up
     * @throws RemoteException
     */
    public void createGrab(String nicknamePlayer, String error,String[] weaponCardAvailable,int[] featuresAvailable,boolean grabWeapon, String ammoTileDescription, String powerUpDescription) throws RemoteException;

    /**
     * method to create ReloadMove in the View
     * @param nicknamePlayer player's nickname
     * @param featuresAvailable features available
     * @param weaponYouCanReload weapon card can be reload
     * @param error possible error
     * @throws RemoteException exception
     */
    public void createReload(String nicknamePlayer, int[] featuresAvailable, int[] weaponYouCanReload, String error) throws RemoteException;

    /**
     * method to create UpdateMessage in the View
     * @param nicknamePlayer player's nickname
     * @param gameBoardDescription description of game board
     * @param playersDescription description of players
     * @param featuresOfPlayersAvailable features of players available
     * @param weaponCardDescription description of weapon card
     * @param powerUpCardDescription description of power up card
     * @param notifyAll it's true so all player can see update
     * @throws RemoteException exception
     */
    public void createUpdateView(String nicknamePlayer,String gameBoardDescription, int[][] playersDescription, int[][] featuresOfPlayersAvailable,String[][] weaponCardDescription, String[][] powerUpCardDescription,boolean notifyAll) throws RemoteException;

    /**
     * method to create EndRegistration in the View
     * @param nicknamePlayer player's nickname
     * @throws RemoteException exception
     */
    public void createWaitForStart(String nicknamePlayer) throws RemoteException;

    /**
     * method to create UseWeaponCardMessage in the View
     * @param nicknamePlayer player's nickname
     * @param weaponCard weapon card available
     * @param featuresForEffect features for effect
     * @param featuresAvailable features available
     * @param playersToAttack player to attac
     * @param error possible error
     * @throws RemoteException exception
     */
    public void createUseWeaponCardMessage(String nicknamePlayer,int[] weaponCard, int[][] featuresForEffect, int[] featuresAvailable, int[][] playersToAttack, String error)throws RemoteException;

    /**
     * method to create UsePowerUpCardMessage in the View
     * @param nicknamePlayer player's nickname
     * @param featuresAvailable features avilable
     * @param stateGame id power up
     * @param canBeUsed can be used
     * @param error possible error
     * @param cooPlayer coordinate player
     * @throws RemoteException exception
     */
    public void createUsePowerUpCard(String nicknamePlayer, int[] featuresAvailable, int stateGame, boolean[] canBeUsed, String error, int[][] cooPlayer) throws RemoteException;

    /**
     * method to create FirstMessageFirstPlayer in the View
     * @param nicknamePlayer player's nickname
     * @param idPlayer id player
     * @param possibleMaps map available
     * @param colorAvailable color available
     * @throws RemoteException exception
     */
    public void createFirstPlayerChooseMap(String nicknamePlayer, int idPlayer, String[] possibleMaps, ArrayList<String> colorAvailable) throws RemoteException;

    /**
     * method to create ChoiceCard in the View
     * @param nicknamePlayer player's nickname
     * @param descriptionPowerUp power up description
     * @param idPowerUp id power up
     * @param error possible error
     * @param discardOne card discard
     * @param isPowerUp if is power up
     * @throws RemoteException exception
     */
    public void createChoiceCard(String nicknamePlayer, String[] descriptionPowerUp, int[] idPowerUp, String error, boolean discardOne, boolean isPowerUp ) throws RemoteException ;

    /**
     * method to create TerminatorMessage in the View
     * @param nicknamePlayer player's nickname
     * @param runOrDamage possible run or shoot of the terminator
     * @param colorSpawn spawn point
     * @param movement num movement terminator can perform
     * @param numPeopleToKill num people the terminator can kill
     * @param cooPeople coordinate of people that the terminator can damage
     * @param error possible error
     * @throws RemoteException exception
     */
    public void createTerminatorMessage (String nicknamePlayer, boolean runOrDamage, ArrayList<String> colorSpawn, int movement, int numPeopleToKill, int[][] cooPeople,String error) throws RemoteException;

    /**
     * method to create EndGame in the View
     * @param nickname player's nickname
     * @param ranking players ranking
     * @param points players point
     * @param pointMax point of winner
     * @param winner winner
     * @param phrase phrase of winner
     * @param notifyAll  it's true so all player can see this
     * @throws RemoteException exception
     */
    public void createEndGame(String nickname, String[] ranking, int [] points, int pointMax, String winner, String phrase, boolean notifyAll) throws RemoteException;
}
