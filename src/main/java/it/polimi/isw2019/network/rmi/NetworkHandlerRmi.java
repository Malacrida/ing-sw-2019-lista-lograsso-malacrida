package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.message.playermove.*;

import it.polimi.isw2019.network.network_interface.ClientInterface;
import it.polimi.isw2019.network.network_interface.ServerInterface;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.utilities.Observer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class NetworkHandlerRmi extends Observable<MoveMessage> implements Observer<PlayerMove>, ClientInterface, Remote, NetworkHandlerVisitorInterface {

//forse va qui il riferimento al registro


    ServerInterface server;
    ClientInterface remoteClient;
    Scanner input = new Scanner(System.in);
    String nickname;



    public NetworkHandlerRmi (String nickname){
        try {
            server = (ServerInterface) Naming.lookup("rmi://localhost:8080/ServerRmi");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        this.nickname= nickname;
    }

    public void setRemoteClient(ClientInterface remoteClient) {
        this.remoteClient = remoteClient;
    }

    @Override
    public void logInFail() throws RemoteException {
        System.out.println("Nickname already present");
        System.out.println("Insert new nickname");
        nickname = input.nextLine();
        try {
            server.registerNewClient(remoteClient, nickname);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendConnectionClient(ConnectionMove connectionMove) {
        try {
            server.receiveConnectionMove(connectionMove.getPlayer(), connectionMove.getConnection());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startViewClient() throws RemoteException {
        StartMessage startMessage = new StartMessage(nickname);
        startMessage.setNotifyAll(true);
        notifyObservers(startMessage);
    }

    @Override
    public void logInCorrect() throws RemoteException {
        System.out.println("registrazione corretta");
    }

    @Override
    public void update(PlayerMove message) {
        System.out.println("vedo la playermove");
        message.accept(this);
    }


    @Override
    public void sendChooseMap(ChooseMapMove chooseMapMove) {
        try {
            server.receiveChooseMap(chooseMapMove.getPlayer(),chooseMapMove.getIndex(),chooseMapMove.getIndexColor(),chooseMapMove.getMod(), chooseMapMove.getTerminator());
        } catch (RemoteException e) {


        }
    }

    @Override
    public void sendActionChoose(ChooseActionMove chooseActionMove) {

        try {
            server.receiveChooseActionMove(chooseActionMove.getPlayer(), chooseActionMove.getNumAction());

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendRun(RunMove runMove) {
        try {
            server.receiveRun(runMove.getPlayer(), runMove.getMovement());

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendGrab(GrabMove grabMove) {
        try {
            server.receiveGrab(grabMove.getPlayer());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendRegisterPlayer(FirstMessage firstMessage) {
        try {
            System.out.println("invio la registrazione");
            server.receiveRegisterPlayer (firstMessage.getPlayer(), firstMessage.getActionHero());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendReload(ReloadMove reloadMove) {
        try {
            server.receiveReload(reloadMove.getPlayer());

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendPowerUpChoice(PowerUpChoice powerUpChoice) {
        try {
            server.receivePowerUpChoice(powerUpChoice.getPlayer(), powerUpChoice.getIdPowerUpDischarge());

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendUsePowerUpCard(UsePowerUpCard usePowerUpCard) {
        try {
            server.receiveUsePowerUpCard(usePowerUpCard.getPlayer());

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendWeaponCardChoice(WeaponCardChoice weaponCardChoice) {
     /*   try {
            server.receiveWeaponCardChoice(weaponCardChoice.getPlayer(),weaponCardChoice.getIndexWeaponCard(),weaponCardChoice.getPayment() weaponCardChoice.getPowerUpCards());

        } catch (RemoteException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void sendUseWeaponCard(UseWeaponCard useWeaponCard) {
        try {
            server.receiveUseWeaponCard(useWeaponCard.getPlayer(),useWeaponCard.getWeaponCard());

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void createActionMessage(String nickname) {
        System.out.println("ricevo move message Action");
        ActionMessage actionMessage= new ActionMessage(nickname);
        notifyObservers(actionMessage);
    }

    @Override
    public void createSetupView(String idMoveMessage, ArrayList<String> colorAvailable) throws RemoteException {

    }

    @Override
    public void createRun(String nicknamePlayer, String error, int numMovement) {
        RunMessage runMessage = new RunMessage(nicknamePlayer, error, numMovement);
        notifyObservers(runMessage);
    }

    @Override
    public void createGrab(String nicknamePlayer) {
        GrabMessage grabMessage= new GrabMessage(nicknamePlayer);
        notifyObservers(grabMessage);
    }

    @Override
    public void createReload(String nicknamePlayer) {
        ReloadMessage reloadMessage= new ReloadMessage(nicknamePlayer);
        notifyObservers(reloadMessage);
    }

    @Override
    public void createUpdateView(String nicknamePlayer,String gameBoardDescription, int[][] playersDescription, int[][] featuresOfPlayersAvailable,String[][] weaponCardDescription, String[][] powerUpCardDescription,boolean notifyAll) {
        UpdateMessage updateMessage = new UpdateMessage(nicknamePlayer,gameBoardDescription,playersDescription,featuresOfPlayersAvailable,weaponCardDescription,powerUpCardDescription,notifyAll);
        notifyObservers(updateMessage);
    }

    @Override
    public void createOkRegistration(String nicknamePlayer, String actionHero, ArrayList<String> colors) throws RemoteException {

    }

    @Override
    public void createWaitForStart(String nicknamePlayer) {
        System.out.println("ricevo una endRegistratio");
        EndRegistration endRegistration = new EndRegistration(nicknamePlayer);
        notifyObservers(endRegistration);

    }

    @Override
    public void createUseWeaponCard(String nicknamePlayer) {

    }

    @Override
    public void createPowerUpChoice(String nicknamePlayer, String[] descriptionPowerUp, int[] idPowerUp ) throws RemoteException {
        System.out.println("ricevo la Move message choice PU");
     //   ChoicePowerUpCard choicePowerUpCard = new ChoicePowerUpCard(nicknamePlayer, descriptionPowerUp, idPowerUp);
       // notifyObservers(choicePowerUpCard);
    }


    @Override
    public void createUsePowerUpCard(String nicknamePlayer) {

    }

    @Override
    public void createFirstPlayerChooseMap(String nicknamePlayer, int idPlayer, String[] possibleMaps, ArrayList<String> colorAvailable) throws RemoteException {
        FirstMessageFirstPlayer firstMessageFirstPlayer= new FirstMessageFirstPlayer(nicknamePlayer,idPlayer,possibleMaps,colorAvailable);
        System.out.println("ricevo la scelta della mappa per: "+ nicknamePlayer);
        notifyObservers(firstMessageFirstPlayer);
    }


    @Override
    public void createFailRegistration(String nicknamePlayer) {
        FailRegistration failRegistration = new FailRegistration(nicknamePlayer);
        notifyObservers(failRegistration);
    }



    @Override
    public void setNickname(String nickname) {

    }

    @Override
    public String getNickname() {
        return null;
    }

    @Override
    public Boolean isYourTurn() throws RemoteException {
        return null;
    }

    @Override
    public void selectModeGameAndMap() throws RemoteException {

    }

    @Override
    public void startRound() throws RemoteException {

    }




}
