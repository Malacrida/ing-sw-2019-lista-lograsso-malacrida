package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class ChoicePowerUpCard extends MoveMessage{

    private ArrayList<InterfacePowerUpCard> powerUpCards;

    public ChoicePowerUpCard(String nicknamePlayer) {
        super(nicknamePlayer);
        this.powerUpCards = new ArrayList<>();
    }

    @Override
    public void accept(VisitorView visitorview) {
            visitorview.powerUpChoice(this);
    }

    public ArrayList<InterfacePowerUpCard> getPowerUpCards(){
        return powerUpCards;
    }

    public void addPowerUpCard(InterfacePowerUpCard powerUpCard){
        powerUpCards.add(powerUpCard);
    }
}
