package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.message.playermove.PlayerMove;
import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.utilities.Observer;

public class VirtualViewSocket  extends Observable<PlayerMove> implements Observer<MoveMessage>, VirtualViewVisitorInterface {

    private String nickname;
    private ServerImplementationSocket serverImplementationSocket;

    public VirtualViewSocket (ServerImplementationSocket serverImplementationSocket){
        this.serverImplementationSocket = serverImplementationSocket;
    }

    @Override
    public void sendActionView(ActionMessage actionMessage) {

    }

    @Override
    public void sendRun(RunMessage runMessage) {

    }

    @Override
    public void sendGrab(GrabMessage grabMessage) {

    }

    @Override
    public void sendReload(ReloadMessage reloadMessage) {

    }

    @Override
    public void sendUpdateView(UpdateMessage updateMessage) {

    }

    @Override
    public void sendWaitForStart(EndRegistration endRegistration) {
        System.out.println("In attesa di iniziare il gioco");
    }

    @Override
    public void sendUseWeaponCard(UseWeaponCardMessage useWeaponCardMessage) {

    }

    @Override
    public void sendPowerUpChoice(ChoiceCard choiceCard) {

    }

    @Override
    public void sendUsePowerUpCard(UsePowerUpCardMessage usePowerUpCardMessage) {

    }

    @Override
    public void sendFirstPlayerChooseMap(FirstMessageFirstPlayer firstMessageFirstPlayer) {

    }

    @Override
    public void sendFailRegistration(FailRegistration failRegistration) {

    }

    @Override
    public void update(MoveMessage message) {
        System.out.println("IL SOCKET VEDE LA PLAYERMOVE, CAZZO!");
        message.accept(this);
    }
}
