package it.polimi.isw2019.Server.Message.MoveMessage;

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
}
