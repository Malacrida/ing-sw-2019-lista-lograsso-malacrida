package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.message.movemessage.MoveMessage;
import it.polimi.isw2019.message.playermove.ChooseActionMove;
import it.polimi.isw2019.message.playermove.PlayerMove;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.utilities.Observer;

public class VirtualView extends Observable<PlayerMove> implements Observer<MoveMessage> {

    private String nickname;
    private NetworkHandlerInterface networkHandler;

    VirtualView (String nickname, NetworkHandlerInterface networkHandler){
        this.nickname=nickname;
        this.networkHandler= networkHandler;
    }

    public String getNickname() {
        return nickname;
    }

    public void createChooseActionMove (String idPlayer, int numAction){
        ChooseActionMove chooseActionMove= new ChooseActionMove(idPlayer,numAction);
        notifyObservers(chooseActionMove);
    }

    @Override
    public void update(MoveMessage message) {

    }

    public void sendActionMessage (){
        networkHandler.createActionMessage(nickname);
    }
}
