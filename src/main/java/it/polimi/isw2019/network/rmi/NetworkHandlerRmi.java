package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.message.playermove.*;

import it.polimi.isw2019.network.ConfigLoader;
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

    ConfigLoader cl = new ConfigLoader();

    ServerInterface server;
    ClientInterface remoteClient;
    Scanner input = new Scanner(System.in);
    String nickname;



    public NetworkHandlerRmi (String nickname){
        try {
            server = (ServerInterface) Naming.lookup("rmi://"+ cl.getHostIp()+":" + cl.getRmiPort()+ "/ServerRmi");
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
    public void reconnectionClient() throws RemoteException {
        System.out.println("reconnection to the server");
    }

    @Override
    public void sendConnectionClient(ConnectionMove connectionMove) {
        try {
            System.out.println("invio exit");
            server.receiveConnectionMove(connectionMove.getPlayer(), connectionMove.getConnection());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendTerminatorMove(TerminatorMove terminatorMove) {
        try {
            server.receiveTerminatorMove(terminatorMove.getPlayer(),terminatorMove.getCoordinates(),terminatorMove.isShootPeople(),terminatorMove.getColorSpawn(),terminatorMove.getIdPlayerToShoot());
        } catch (RemoteException e) {
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
            e.printStackTrace();
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
            server.receiveGrab(grabMove.getPlayer(), grabMove.getPositionWeaponCard(), grabMove.getPayment());
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
            server.receiveReload(reloadMove.getPlayer(), reloadMove.getWeaponCard(), reloadMove.getPayment());

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
            System.out.println("invio power up");
            server.receiveUsePowerUpCard(usePowerUpCard.getPlayer(),usePowerUpCard.getCoordinates(), usePowerUpCard.getIdPlayer(), usePowerUpCard.isDefend(), usePowerUpCard.getIdPlayer());

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendWeaponCardChoice(WeaponCardChoice weaponCardChoice) {
        try {
            server.receiveWeaponCardChoice(weaponCardChoice.getPlayer(),weaponCardChoice.getIndexWeaponCard(),weaponCardChoice.getPayment());

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendUseWeaponCard(UseWeaponCard useWeaponCard) {
        try {
            server.receiveUseWeaponCard(useWeaponCard.getPlayer(),useWeaponCard.getWeaponCard(), useWeaponCard.getEffectUsed(), useWeaponCard.getHandleEffectCoordinates(),useWeaponCard.getPeopleToBeShoot());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void createActionMessage(String nicknamePlayer, String[] actionYouCanPerform, ArrayList<String> actionPlayerCanPerform, ArrayList<Integer> intIdAction) {
        ActionMessage actionMessage = new ActionMessage(nickname,actionYouCanPerform, actionPlayerCanPerform, intIdAction);
        notifyObservers(actionMessage);
    }

    @Override
    public void createRun(String nicknamePlayer, String error, int numMovement) {
        RunMessage runMessage = new RunMessage(nicknamePlayer, error, numMovement);
        notifyObservers(runMessage);
    }

    @Override
    public void createGrab(String nicknamePlayer, String error,String[] weaponCardAvailable,int[] featuresAvailable,boolean grabWeapon, String ammoTileDescription, String powerUpDescription){
        GrabMessage grabMessage= new GrabMessage(nicknamePlayer, error,weaponCardAvailable,featuresAvailable,grabWeapon,ammoTileDescription,powerUpDescription);
        notifyObservers(grabMessage);
    }

    @Override
    public void createReload(String nicknamePlayer, int[] featuresAvailable, int[] weaponYouCanReload, String error) {
        ReloadMessage reloadMessage= new ReloadMessage(nicknamePlayer, featuresAvailable, weaponYouCanReload, error);
        notifyObservers(reloadMessage);
    }

    @Override
    public void createUpdateView(String nicknamePlayer,String gameBoardDescription, int[][] playersDescription, int[][] featuresOfPlayersAvailable,String[][] weaponCardDescription, String[][] powerUpCardDescription,boolean notifyAll) {
        UpdateMessage updateMessage = new UpdateMessage(nicknamePlayer,gameBoardDescription,playersDescription,featuresOfPlayersAvailable,weaponCardDescription,powerUpCardDescription,notifyAll);
        notifyObservers(updateMessage);
    }


    @Override
    public void createWaitForStart(String nicknamePlayer) {
        System.out.println("ricevo una endRegistratio");
        EndRegistration endRegistration = new EndRegistration(nicknamePlayer);
        notifyObservers(endRegistration);

    }

    @Override
    public void createUseWeaponCardMessage(String nicknamePlayer,int[] weaponCard, int[][] featuresForEffect, int[] featuresAvailable, int[][] playersToAttack, String error){
        UseWeaponCardMessage useWeaponCardMessage = new UseWeaponCardMessage(nicknamePlayer, weaponCard, featuresForEffect, featuresAvailable, playersToAttack, error);
        notifyObservers(useWeaponCardMessage);

    }

    @Override
    public void createChoiceCard(String nicknamePlayer, String[] descriptionPowerUp, int[] idPowerUp, String error, boolean discardOne, boolean isPowerUp ) throws RemoteException {
        System.out.println("ricevo la Move message choice PU");
        ChoiceCard choiceCard = new ChoiceCard(nicknamePlayer,descriptionPowerUp,idPowerUp,error,discardOne, isPowerUp);
        notifyObservers(choiceCard);
    }

    @Override
    public void createTerminatorMessage(String nicknamePlayer, boolean runOrDamage, ArrayList<String> colorSpawn, int movement, int numPeopleToKill, int[][] cooPeople, String error) throws RemoteException {
        TerminatorMessage terminatorMessage= new TerminatorMessage(nicknamePlayer,runOrDamage,colorSpawn,movement, numPeopleToKill,cooPeople,error);
        notifyObservers(terminatorMessage);
    }

    @Override
    public void createEndGame(String nickname, String[] ranking, int[] points, int pointMax, String winner, String phrase, boolean notifyAll) throws RemoteException {
        System.out.println("ricevo una end game");
        EndGame endGame = new EndGame(nickname,ranking, points,pointMax,winner,phrase, notifyAll);
        notifyObservers(endGame);
    }


    @Override
    public void createUsePowerUpCard(String nicknamePlayer, int[] featuresAvailable, int stateGame, boolean[] canBeUsed, String error, int[][] cooPlayer) {
        UsePowerUpCardMessage usePowerUpCardMessage = new UsePowerUpCardMessage(nicknamePlayer,featuresAvailable,stateGame,canBeUsed,error, cooPlayer);
        System.out.println("notifico");
        notifyObservers(usePowerUpCardMessage);
    }

    @Override
    public void createFirstPlayerChooseMap(String nicknamePlayer, int idPlayer, String[] possibleMaps, ArrayList<String> colorAvailable) throws RemoteException {
        FirstMessageFirstPlayer firstMessageFirstPlayer= new FirstMessageFirstPlayer(nicknamePlayer,idPlayer,possibleMaps,colorAvailable);
        System.out.println("ricevo la scelta della mappa per: "+ nicknamePlayer);
        notifyObservers(firstMessageFirstPlayer);
    }


    @Override
    public void setNickname(String nickname) {

    }

    @Override
    public String getNickname() {
        return null;
    }





}
