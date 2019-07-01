package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.message.playermove.PlayerMove;
import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.utilities.Observer;

import java.io.IOException;

public class VirtualViewSocket extends Observable<PlayerMove> implements Observer<MoveMessage>, VirtualViewVisitorInterface  {

    private String nickname;
    private ServerImplementationSocket serverImplementationSocket;

    public VirtualViewSocket (ServerImplementationSocket serverImplementationSocket){
        this.serverImplementationSocket = serverImplementationSocket;
    }

    public void receivePlayerMove (PlayerMove playerMove){
        System.out.println("evviva");
        System.out.println("player: " + playerMove.getPlayer());
        notifyObservers(playerMove);
    }

    @Override
    public void update(MoveMessage message) {
        //Richiami il metodo per fare l'oput stream sul oggetto
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
    public void sendPowerUpChoice(ChoicePowerUpCard choicePowerUpCard) {

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

}
