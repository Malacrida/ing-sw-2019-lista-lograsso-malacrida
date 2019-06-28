package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

public class RunMessage extends MoveMessage {

    private int numMovement;
    private int[] featuresAvailable;

    public RunMessage(String nicknamePlayer, String error, int numMovement){
        super(nicknamePlayer,error);
        this.numMovement = numMovement;
    }

    public RunMessage(String nicknamePlayer, int[] featuresAvailable) {
        super(nicknamePlayer);
        this.featuresAvailable = featuresAvailable;
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

    public int[] getFeaturesAvailable() {
        return featuresAvailable;
    }

    public void setFeaturesAvailable(int[] featuresAvailable) {
        this.featuresAvailable = featuresAvailable;
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
