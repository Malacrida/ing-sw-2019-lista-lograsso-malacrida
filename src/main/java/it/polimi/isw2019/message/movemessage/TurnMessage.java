package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class TurnMessage extends MoveMessage {

    private boolean respawn;
    private ArrayList<Integer> powerUpCard;
    private String idPlayer;

    public TurnMessage(String idPlayer, boolean respawn){
        super(idPlayer);
        this.respawn = respawn;
    }



    public TurnMessage(String idMoveMessage, int idPlayer, boolean respawn, ArrayList<Integer> powerUpCard){
        super(idMoveMessage,idPlayer);
        this.respawn = respawn;
        this.powerUpCard = powerUpCard;
    }



    public boolean isRespawn(){
        return this.respawn;
    }

    public ArrayList<Integer> getPowerUpCard(){
        return this.powerUpCard;
    }

    @Override
    public void accept(VisitorView visitorview) {

    }
}
