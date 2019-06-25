package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

public class RunMessage extends MoveMessage {

    private int numMovement;

    public RunMessage(String nicknamePlayer, String error, int numMovement){
        super(nicknamePlayer,error);
        this.numMovement = numMovement;
    }

    public RunMessage(String nicknamePlayer){
        super(nicknamePlayer);
    }

    public int getNumMovement() {
        return numMovement;
    }

    public void setNumMovement(int numMovement) {
        this.numMovement = numMovement;
    }

    @Override
    public void accept(VisitorView visitorView) {
            visitorView.visitRun(this);
    }

    @Override
    public void accept(VirtualViewVisitorInterface virtualView) {
        virtualView.sendRun(this);
    }
}
