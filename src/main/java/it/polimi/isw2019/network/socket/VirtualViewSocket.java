package it.polimi.isw2019.network.socket;

import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.message.playermove.FirstMessage;
import it.polimi.isw2019.message.playermove.PlayerMove;
import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.utilities.Observer;

import java.io.IOException;
import java.rmi.RemoteException;

public class VirtualViewSocket extends Observable<PlayerMove> implements Observer<MoveMessage>, VirtualViewVisitorInterface {

    private FirstMessage firstMessage = null;
    private FirstMessage newFirstMessage = null;
    private String nickname;
    private ServerImplementationSocket serverImplementationSocket;
    private ClientSocket clientSocket;

    public VirtualViewSocket(String nickname, ClientSocket clientSocket) {
        this.nickname = nickname;
        this.clientSocket = clientSocket;
    }

    public void receivePlayerMove(PlayerMove playerMove) {
        System.out.println("evviva");
        System.out.println("player: " + playerMove.getPlayer());
        System.out.println(playerMove);
        if (playerMove instanceof FirstMessage) {
            firstMessage = (FirstMessage) playerMove;
            newFirstMessage = new FirstMessage(this, firstMessage.getPlayer(), firstMessage.getActionHero());
            notifyObservers(newFirstMessage);
        }
        else notifyObservers(playerMove);
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
}
