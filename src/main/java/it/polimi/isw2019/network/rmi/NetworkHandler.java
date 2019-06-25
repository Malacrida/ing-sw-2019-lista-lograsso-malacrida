package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.model.GameBoardInterface;
import it.polimi.isw2019.model.PlayerInterface;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.network.ClientRmi;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.utilities.Observer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class NetworkHandler extends Observable<MoveMessage> implements Observer<PlayerMove>, NetworkHandlerInterface, Remote, NetworkHandlerVisitorInterface {

//forse va qui il riferimento al registro


    static ServerInterfaceRMI server;
    static int id;
    static ClientRmi client;
    static String nameNetworkHandler;
    static String insert;

    public NetworkHandler (String nickname){
        try {
            server = (ServerInterfaceRMI) Naming.lookup("rmi://localhost:1234/ServerRmi");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        nameNetworkHandler= nickname;
    }


    @Override
    public void update(PlayerMove message) {
        System.out.println("notifica dalla VP");
        message.accept(this);
    }


    @Override
    public void sendChooseMap(ChooseMapMove chooseMapMove) {

    }

    @Override
    public void sendActionChoose(ChooseActionMove chooseActionMove) {
        System.out.println("invio la playermove");
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
            server.receivePowerUpChoice(powerUpChoice.getPlayer(), powerUpChoice.getIdPowerUp());

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendUsePowerUpCard(UsePowerUpCard usePowerUpCard) {
        try {
            server.receiveUsePowerUpCard(usePowerUpCard.getPlayer(), usePowerUpCard.getPowerUpCardInterface());

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendWeaponCardChoice(WeaponCardChoice weaponCardChoice) {
        try {
            server.receiveWeaponCardChoice(weaponCardChoice.getPlayer(),weaponCardChoice.getIndexWeaponCard(),weaponCardChoice.getPayment(), weaponCardChoice.getPowerUpCards(), weaponCardChoice.isGrab());

        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
        System.out.println("ricevo move message");
        ActionMessage actionMessage= new ActionMessage(nickname);
        notifyObservers(actionMessage);
    }

    @Override
    public void createSetupView(String idMoveMessage, int idPlayer, ArrayList<String> colorAvailable) {

    }

    @Override
    public void createRun(String nicknamePlayer, String error, int numMovement) {

    }

    @Override
    public void createGrab(String nicknamePlayer) {

    }

    @Override
    public void createReload(String nicknamePlayer, ArrayList<WeaponCardInterface> weaponCardInterfaces) {

    }

    @Override
    public void createUpdateView(String nicknamePlayer, GameBoardInterface gameBoard, ArrayList<PlayerInterface> players) {

    }

    @Override
    public void createOkRegistration(String nicknamePlayer, String actionHero, ArrayList<String> colors) {

    }

    @Override
    public void createWaitForStart(String nicknamePlayer) {

    }

    @Override
    public void createWeaponCardChoice(ChoiceWeaponCard choiceWeaponCard) {

    }

    @Override
    public void createUseWeaponCard(String nicknamePlayer) {

    }

    @Override
    public void createPowerUpChoice(String nicknamePlayer) {

    }

    @Override
    public void createUsePowerUpCard(String nicknamePlayer) {

    }

    @Override
    public void createFirstPlayerChooseMap(String nicknamePlayer) {

    }

    @Override
    public void createFailRegistration(String nicknamePlayer) {

    }


}
