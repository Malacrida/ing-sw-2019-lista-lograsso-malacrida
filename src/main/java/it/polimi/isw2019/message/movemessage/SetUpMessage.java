package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class SetUpMessage extends MoveMessage {

    private ArrayList<String> colorAvailable;


    public SetUpMessage(String idMoveMessage, int idPlayer, ArrayList<String> colorAvailable) {
        super(idMoveMessage, idPlayer);
        this.colorAvailable = colorAvailable;
    }
    public ArrayList<String> getColorAvailable() {
        return this.colorAvailable;
    }

    @Override
    public void accept(VisitorView visitorView) {
        visitorView.visitSetupView(this);
    }
}
