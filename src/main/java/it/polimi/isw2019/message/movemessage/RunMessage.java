package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.view.VisitorView;

public class RunMessage extends MoveMessage {

    private String nicknamePlayer;
    private int numMovement;

    public RunMessage(String nicknamePlayer, int numMovement){
        super(nicknamePlayer);
        this.numMovement = numMovement;
    }

    @Override
    public String getNicknamePlayer() {
        return nicknamePlayer;
    }

    public void setNicknamePlayer(String nicknamePlayer) {
        this.nicknamePlayer = nicknamePlayer;
    }

    public int getNumMovement() {
        return numMovement;
    }

    public void setNumMovement(int numMovement) {
        this.numMovement = numMovement;
    }

    @Override
    public void visit(VisitorView visitorView) {
            visitorView.visitRun(this);
    }
}
