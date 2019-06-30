package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.message.movemessage.EndRegistration;
import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.network.Lobby;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;
import it.polimi.isw2019.network.rmi.VirtualView;
import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.utilities.Observer;

public class NetworkHandlerSocket extends Observable<MoveMessage> implements Observer<PlayerMove>, NetworkHandlerVisitorInterface {

    ClientSocket clientSocket;
    Lobby lobby;

    public NetworkHandlerSocket(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void setLobby(Lobby lobby){
        this.lobby = lobby;
    }

    @Override
    public void sendChooseMap(ChooseMapMove chooseMapMove) {

    }

    @Override
    public void sendActionChoose(ChooseActionMove chooseActionMove) {

    }

    @Override
    public void sendRun(RunMove runMove) {

    }

    @Override
    public void sendGrab(GrabMove grabMove) {

    }

    @Override
    public void sendRegisterPlayer(FirstMessage firstMessage) {
        System.out.println("HO FATTO FINTA DI REGISTRARE IL PLAYER NELLA LOBBY");
        EndRegistration endRegistration = new EndRegistration(firstMessage.getPlayer());
        endRegistration.setNotifyAll(true);
        notifyObservers(endRegistration);
    }

    @Override
    public void sendReload(ReloadMove reloadMove) {

    }

    @Override
    public void sendPowerUpChoice(PowerUpChoice powerUpChoice) {

    }

    @Override
    public void sendUsePowerUpCard(UsePowerUpCard usePowerUpCard) {

    }

    @Override
    public void sendWeaponCardChoice(WeaponCardChoice weaponCardChoice) {

    }

    @Override
    public void sendUseWeaponCard(UseWeaponCard useWeaponCard) {

    }

    @Override
    public void update(PlayerMove message) {
        System.out.println("IL SOCKET VEDE LA PLAYERMOVE, CAZZO!");
        message.accept(this);
    }
}
