package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class ChoicePowerUpCard extends MoveMessage{

    private ArrayList<InterfacePowerUpCard> powerUpCards;
    //phrase : respawn, firstTurn, scelta
    private String phrase;

    public ChoicePowerUpCard(String nicknamePlayer, String error, ArrayList<InterfacePowerUpCard> powerUpCard) {
        super(nicknamePlayer, error);
    }

    @Override
    public void accept(VisitorView visitorview) {
            visitorview.powerUpChoice(this);
    }

    public ArrayList<InterfacePowerUpCard> getPowerUpCards(){
        return powerUpCards;
    }
}
