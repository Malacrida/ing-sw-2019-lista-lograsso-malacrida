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
    public int[] getFeaturesAvailable() {
        return new int[0];
    }

    @Override
    public void setFeaturesAvailable(int[] featuresAvailable) {

    }

    @Override
    public void accept(VisitorView visitorview) {
            visitorview.powerUpChoice(this);
    }

    public ArrayList<InterfacePowerUpCard> getPowerUpCards(){
        return powerUpCards;
    }

    public void setPowerUpCards(ArrayList<InterfacePowerUpCard> powerUpCards) {
        this.powerUpCards = powerUpCards;
    }

    public void addPowerUpCard(InterfacePowerUpCard powerUpCard){
        powerUpCards.add(powerUpCard);
    }
}
