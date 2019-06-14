package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class StartTurn extends MoveMessage {

    private boolean respawn;
    private ArrayList<InterfacePowerUpCard> powerUpCard;
    private String nickname;

    public StartTurn(String idPlayer, boolean respawn){
        super(idPlayer);
        this.respawn = respawn;
    }



    public StartTurn(String nickname, String error, boolean respawn, ArrayList<InterfacePowerUpCard> powerUpCard){
        super(nickname,error);
        this.respawn = respawn;
        this.powerUpCard = powerUpCard;
    }



    public boolean isRespawn(){
        return this.respawn;
    }

    public ArrayList<InterfacePowerUpCard> getPowerUpCard(){
        return this.powerUpCard;
    }

    @Override
    public void accept(VisitorView visitorview) {

    }
}
