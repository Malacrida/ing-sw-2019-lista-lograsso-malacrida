package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.message.movemessage.EndRegistration;
import it.polimi.isw2019.message.movemessage.MoveMessage;
import it.polimi.isw2019.message.movemessage.StartMessage;
import it.polimi.isw2019.message.playermove.FirstMessage;
import it.polimi.isw2019.message.playermove.PlayerMove;
import it.polimi.isw2019.network.Lobby;
import it.polimi.isw2019.network.Server;
import it.polimi.isw2019.network.network_interface.ClientInterface;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ClientSocket extends Thread implements ClientInterface {

    private Socket clientSocket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private PlayerMove playerMove;
    private MoveMessage moveMessage;
    private String nickname;
    private boolean isReady = false;
    private InetAddress ip;
    private Lobby lobby;
    private VirtualViewSocket virtualViewSocket;

    @Override
    public void createUpdateView(String nicknamePlayer, String gameBoardDescription, int[][] playersDescription, int[][] featuresOfPlayersAvailable, String[][] weaponCardDescription, String[][] powerUpCardDescription, boolean notifyAll) throws RemoteException {

    }

    public ClientSocket(Socket clientSocket) throws IOException{
        this.clientSocket = clientSocket;
        this.output = new ObjectOutputStream(this.clientSocket.getOutputStream());
        this.input = new ObjectInputStream(this.clientSocket.getInputStream());
        this.ip = clientSocket.getInetAddress();

    }

    public String convertIpToString(InetAddress ip){
        return ip.toString();
    }

    @Override
    public void run() {
        try {
            virtualViewSocket = new VirtualViewSocket(nickname, this);

            while (null != (playerMove = (PlayerMove) input.readObject())) {
                System.out.println(playerMove);
                System.out.println("[---CLIENTSOCKET---] Prendo la playermove");
                Runnable task = () -> {
                    virtualViewSocket.receivePlayerMove(playerMove);
                };
                Thread thread = new Thread(task);
                thread.start();
                System.out.println("HO MANDATO LA PLAYERMOVE ALLA VIRTUALVIEW");
            }
        }catch (IOException | ClassNotFoundException e){
            e.getCause();
        }
    }

    @Override
    public void startViewClient() throws RemoteException {
        StartMessage startMessage = new StartMessage(nickname);
        try {
            write(startMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String getNickname() {
        return this.nickname;
    }

    @Override
    public void reconnectionClient() throws RemoteException {

    }

    @Override
    public Boolean isYourTurn() throws RemoteException {
        return true;
    }

    @Override
    public void selectModeGameAndMap() throws RemoteException {

    }

    @Override
    public void startRound() throws RemoteException {

    }

    @Override
    public void logInCorrect() throws RemoteException {

    }

    @Override
    public void logInFail() throws RemoteException {

    }

    @Override
    public void createActionMessage(String nicknamePlayer, String[] actionYouCanPerform, ArrayList<String> actionPlayerCanPerform, ArrayList<Integer> intIdAction) throws RemoteException {

    }

    @Override
    public void createRun(String nicknamePlayer, String error, int numMovement) throws RemoteException {

    }

    @Override
    public void createGrab(String nicknamePlayer, String error, String[] weaponCardAvailable, int[] featuresAvailable, boolean grabWeapon, String ammoTileDescription, String powerUpDescription) throws RemoteException {

    }

    @Override
    public void createReload(String nicknamePlayer, int[] featuresAvailable, int[] weaponYouCanReload, String error) throws RemoteException {

    }

    @Override
    public void createWaitForStart(String nicknamePlayer) throws RemoteException {

    }

    @Override
    public void createUseWeaponCardMessage(String nicknamePlayer, int[] weaponCard, int[] featuresAvailable, int[][] playersToAttack, String error) throws RemoteException {

    }

    @Override
    public void createUsePowerUpCard(String nicknamePlayer, int[] featuresAvailable, int stateGame, boolean[] canBeUsed, String error, int[][] cooPlayer) throws RemoteException {

    }

    @Override
    public void createFirstPlayerChooseMap(String nicknamePlayer, int idPlayer, String[] possibleMaps, ArrayList<String> colorAvailable) throws RemoteException {

    }


    @Override
    public void createChoiceCard(String nicknamePlayer, String[] descriptionPowerUp, int[] idPowerUp, String error, boolean discardOne, boolean isPowerUp) throws RemoteException {

    }

    public ObjectOutputStream getObjectOutputStream(){
        return this.output;
    }

    public ObjectInputStream getObjectInputStream(){
        return this.input;
    }

    public void setMoveMessage(MoveMessage moveMessage){
        this.moveMessage = moveMessage;
        System.out.println("---CS--- QUESTA È LA MOVE MESSAGE CHE HO RICEVUTO: " + moveMessage);
        try {
            write(moveMessage);
            System.out.println("---CS--- HO INVIATO LA MOVE MESSAGE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write (Object object) throws IOException {
        this.output.writeObject(object);
        this.output.flush();
        this.output.reset();
    }
}
