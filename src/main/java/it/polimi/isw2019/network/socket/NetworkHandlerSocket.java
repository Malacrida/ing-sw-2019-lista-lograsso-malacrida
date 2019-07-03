package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.message.movemessage.EndRegistration;
import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.network.Lobby;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.utilities.Observer;

public class NetworkHandlerSocket extends Observable<MoveMessage> implements Observer<PlayerMove>, NetworkHandlerVisitorInterface  {

    ServerImplementationSocket serverImplementationSocket;
    Lobby lobby;

    public NetworkHandlerSocket(ServerImplementationSocket serverImplementationSocket) {
        this.serverImplementationSocket = serverImplementationSocket;
    }

    @Override
    public void update(PlayerMove message) {
        serverImplementationSocket.setPlayerMove(message);
    }

    public void receiveMoveMessage (MoveMessage moveMessage){
        notifyObservers(moveMessage);
    }


    @Override
    public void sendChooseMap(ChooseMapMove chooseMapMove) {
        update(chooseMapMove);
    }

    @Override
    public void sendActionChoose(ChooseActionMove chooseActionMove) {
        update(chooseActionMove);
    }

    @Override
    public void sendRun(RunMove runMove) {
        update(runMove);
    }

    @Override
    public void sendGrab(GrabMove grabMove) {
        update(grabMove);
    }

    @Override
    public void sendRegisterPlayer(FirstMessage firstMessage) {

        update(firstMessage);
    }

    @Override
    public void sendReload(ReloadMove reloadMove) {
        update(reloadMove);
    }

    @Override
    public void sendPowerUpChoice(PowerUpChoice powerUpChoice) {
        update(powerUpChoice);
    }

    @Override
    public void sendUsePowerUpCard(UsePowerUpCard usePowerUpCard) {
        update(usePowerUpCard);
    }

    @Override
    public void sendWeaponCardChoice(WeaponCardChoice weaponCardChoice) {
        update(weaponCardChoice);
    }

    @Override
    public void sendUseWeaponCard(UseWeaponCard useWeaponCard) {
        update(useWeaponCard);
    }

    @Override
    public void sendConnectionClient(ConnectionMove connectionMove) {
        update(connectionMove);
    }


}
