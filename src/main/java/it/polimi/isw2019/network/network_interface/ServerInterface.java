package it.polimi.isw2019.network.network_interface;

//import sun.plugin2.message.HeartbeatMessage;

import it.polimi.isw2019.view.CLIView;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerInterface<T> extends Remote {


    /**
     * register new client to the server
     * @param client type of client's interface that use client
     * @param nickname nickname of client
     * @throws IOException exception
     * @throws RemoteException exception
     */
    public void registerNewClient(T client, String nickname) throws IOException,RemoteException;

    /**
     * send message (socket)
     * @param object message send
     * @throws IOException exception
     * @throws RemoteException exception
     */
    public void write(Object object) throws IOException,RemoteException;

    /**
     * method to create ChooseActionMove in the virtualView
     * @param player player's nickname
     * @param numAction type of action
     * @throws RemoteException exception
     */
    public void receiveChooseActionMove(String player, int numAction) throws RemoteException;

    /**
     * method to create ChooseMap in the virtualView
     * @param player player's nickname
     * @param index map
     * @param color color player
     * @param mod game mod
     * @param terminator active terminator
     * @throws RemoteException exception
     */
    public void receiveChooseMap(String player, int index, int color,int mod, int terminator) throws RemoteException;

    /**
     * method to create RunMove in the virtualView
     * @param player player's nickname
     * @param movement player's movement
     * @throws RemoteException exception
     */
    public void receiveRun(String player,int[][] movement) throws RemoteException;

    /**
     * method to create GrabMove in the virtualView
     * @param player player's nickname
     * @param positionWeaponCard position of Card
     * @param paymen payment to take the card
     * @throws RemoteException exception
     */
    public void receiveGrab(String player, int positionWeaponCard, int[] paymen)throws RemoteException;

    /**
     * method to RegisterPlayer in the virtualView
     * @param player player's nickname
     * @param actionHero player's phrase
     * @throws RemoteException exception
     */
    public void receiveRegisterPlayer(String player, String actionHero) throws RemoteException;

    /**
     * method to create ReloadMove in the virtualView
     * @param player player's nickname
     * @param weaponCard Card to reload
     * @param payment payment to reload
     * @throws RemoteException exception
     */
    public void receiveReload(String player, int[] weaponCard, int[][] payment) throws RemoteException;

    /**
     * method to create PowerUpChoice in the virtualView
     * @param player player's nickname
     * @param idPowerUp power up selected
     * @throws RemoteException exception
     */
    public void receivePowerUpChoice(String player, int idPowerUp) throws RemoteException;

    /**
     * method to create UsePowerUpCardMove in the virtualView
     * @param player player's nickname
     * @param coordinates coordinates of player inside the arena and the index of each player
     * @param idPlayer id of the player
     * @param defend if the power up can be used only to attack or defend
     * @param positionPowerUp position of powerup inside the deck of the player
     * @throws RemoteException exception
     */
    public void receiveUsePowerUpCard(String player, int[][] coordinates, int idPlayer, boolean defend, int positionPowerUp) throws RemoteException;

    /**
     * method to create WeaponCardChoice in the virtualView
     * @param player player's nickname
     * @param indexWeaponCard index of weapon card choice
     * @param payment payment to take this card
     * @throws RemoteException exception
     */
    public void receiveWeaponCardChoice(String player, int indexWeaponCard, int[] payment) throws RemoteException;

    /**
     * method to create UseWeaponCardMove in the virtualView
     * @param player player's nickname
     * @param weaponCard weapon card selected
     * @param effectUsed type of effect
     * @param handleEffectCoordinates handel effect coordinates
     * @param peopleToBeShoot people to shoot
     * @throws RemoteException exception
     */
    public void receiveUseWeaponCard (String player, int weaponCard, int[] effectUsed, int[][] handleEffectCoordinates, int[][] peopleToBeShoot) throws RemoteException;

    /**
     * method to create ConnectionMove in the virtualView
     * @param player player's nickname
     * @param connection type connection
     * @throws RemoteException exception
     */
    public void receiveConnectionMove(String player, int connection) throws RemoteException;

    /**
     * method to create TerminatorMove in the virtualView
     * @param player player's nickname
     * @param coordinates coordinates to move terminator
     * @param shootPeople people shoot by terminator
     * @param colorSpawn where terminator spawn
     * @param idPlayerToShoot player to shoot
     * @throws RemoteException exception
     */
    public void receiveTerminatorMove(String player, int[] coordinates, boolean shootPeople, int colorSpawn, int[] idPlayerToShoot) throws RemoteException;

}
