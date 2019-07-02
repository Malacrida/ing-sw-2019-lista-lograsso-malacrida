package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.message.playermove.PlayerMove;
import it.polimi.isw2019.network.rmi.VirtualView;
import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.utilities.Observer;

import java.io.IOException;
import java.rmi.RemoteException;

public class VirtualViewSocket extends Observable<PlayerMove> implements Observer<MoveMessage>, VirtualViewVisitorInterface  {

    private String nickname;
    private ServerImplementationSocket serverImplementationSocket;
    private ClientSocket clientSocket;

    public VirtualViewSocket(String nickname, ClientSocket clientSocket){
        this.nickname = nickname;
        this.clientSocket = clientSocket;
    }

    public void receivePlayerMove (PlayerMove playerMove){
        System.out.println("evviva");
        System.out.println("player: " + playerMove.getPlayer());
        notifyObservers(playerMove);
    }

    public String getNickname() {
        return nickname;
    }

    public void startView() throws RemoteException {
        clientSocket.startViewClient();
    }

    @Override
    public void update(MoveMessage message) {
        System.out.println("---VIRTUALVIEW--- HO FATTO L'UPDATE DI MOVEMESSAGE: " + message);
        clientSocket.setMoveMessage(message);
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
