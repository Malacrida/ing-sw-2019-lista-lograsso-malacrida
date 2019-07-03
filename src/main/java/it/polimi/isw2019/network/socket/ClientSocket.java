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
    public void createReload(String nicknamePlayer) throws RemoteException {

    }

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
       /* try {
            virtualViewSocket = new VirtualViewSocket(nickname, this);
            System.out.println("HO SETTATO LA VIRTUAL VIEW COL NICK: " + nickname);
            MiniController miniController = new MiniController();
            virtualViewSocket.registerObserver(miniController);
            miniController.registerObserver(virtualViewSocket);
            while (null != (playerMove = (PlayerMove) input.readObject())) {
                System.out.println(playerMove);
                System.out.println("[---CLIENTSOCKET---] Prendo la playermove");
                Runnable task = () -> {
                    virtualViewSocket.receivePlayerMove(playerMove);
                };
                Thread thread = new Thread(task);
                thread.start();
                //virtualViewSocket.receivePlayerMove(playerMove);
                System.out.println("HO MANDATO LA PLAYERMOVE ALLA VIRTUALVIEW");
            }
        }catch (IOException e){

        } catch (ClassNotFoundException e) {
                /*Runnable task = () -> {
                    playerMove.accept(networkHandlerVisitorInterface);
                    //System.out.println("Prendo la playermove");


                };
                try {
                    output.writeObject(playerMove);
                     Thread thread = new Thread(task);
                    thread.start();

                      }
                } catch (IOException e) {
                }
            }
       } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
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
    public void createActionMessage(String nickname) throws RemoteException {

    }

    @Override
    public void createSetupView(String idMoveMessage, ArrayList<String> colorAvailable) throws RemoteException {

    }

    @Override
    public void createRun(String nicknamePlayer, String error, int numMovement) throws RemoteException {

    }

    @Override
    public void createGrab(String nicknamePlayer) throws RemoteException {

    }


    @Override
    public void createOkRegistration(String nicknamePlayer, String actionHero, ArrayList<String> colors) throws RemoteException {
        EndRegistration endRegistration = new EndRegistration(nicknamePlayer);
    }

    @Override
    public void createWaitForStart(String nicknamePlayer) throws RemoteException {

    }


    @Override
    public void createUseWeaponCard(String nicknamePlayer) throws RemoteException {

    }

    @Override
    public void createPowerUpChoice(String nicknamePlayer, String[] descriptionPowerUp, int[] idPowerUp) throws RemoteException {

    }


    @Override
    public void createUsePowerUpCard(String nicknamePlayer) throws RemoteException {

    }

    @Override
    public void createFirstPlayerChooseMap(String nicknamePlayer, int idPlayer, String[] possibleMaps, ArrayList<String> colorAvailable) throws RemoteException {

    }


    @Override
    public void createFailRegistration(String nicknamePlayer) throws RemoteException {

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
