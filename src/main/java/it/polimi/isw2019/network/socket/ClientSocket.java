package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.message.movemessage.ChoiceWeaponCard;
import it.polimi.isw2019.model.GameBoardInterface;
import it.polimi.isw2019.model.PlayerInterface;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.network.network_interface.ClientInterface;

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
    private String nickname;
    private boolean isReady = false;
    private InetAddress ip;

    public ClientSocket(Socket clientSocket) throws IOException{
        this.clientSocket = clientSocket;
        this.input = new ObjectInputStream(clientSocket.getInputStream());
        this.output = new ObjectOutputStream(clientSocket.getOutputStream());
        this.ip = clientSocket.getInetAddress();
    }

    public ClientSocket (ObjectOutputStream output, ObjectInputStream input) throws IOException{ //aggiugere anche la lobby
        this.input = input;
        this.output = output;
    }

    public String convertIpToString(InetAddress ip){
        return ip.toString();
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
    public void createReload(String nicknamePlayer, ArrayList<WeaponCardInterface> weaponCardInterfaces) throws RemoteException {

    }

    @Override
    public void createUpdateView(String nicknamePlayer, GameBoardInterface gameBoard, ArrayList<PlayerInterface> players) throws RemoteException {

    }

    @Override
    public void createOkRegistration(String nicknamePlayer, String actionHero, ArrayList<String> colors) throws RemoteException {

    }

    @Override
    public void createWaitForStart(String nicknamePlayer) throws RemoteException {

    }

    @Override
    public void createWeaponCardChoice(ChoiceWeaponCard choiceWeaponCard) throws RemoteException {

    }

    @Override
    public void createUseWeaponCard(String nicknamePlayer) throws RemoteException {

    }

    @Override
    public void createPowerUpChoice(String nicknamePlayer) throws RemoteException {

    }

    @Override
    public void createUsePowerUpCard(String nicknamePlayer) throws RemoteException {

    }

    @Override
    public void createFirstPlayerChooseMap(String nicknamePlayer) throws RemoteException {

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
}
