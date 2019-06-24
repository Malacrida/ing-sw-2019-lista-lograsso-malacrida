package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.message.movemessage.ActionMessage;
import it.polimi.isw2019.message.movemessage.MoveMessage;
import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.network.ClientRmi;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.utilities.Observer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

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
    public void createActionMessage(String nickname) {
        System.out.println("ricevo move message");
        ActionMessage actionMessage= new ActionMessage(nickname);
        notifyObservers(actionMessage);
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
}
