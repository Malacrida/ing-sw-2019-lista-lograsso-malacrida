package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
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

    @Override
    public void accept(VirtualViewVisitorInterface virtualView) {
        virtualView.sendPowerUpChoice(this);
    }

    /**
     * Getter of Power Up cards
     * @return power up cards in deck
     */
    public ArrayList<InterfacePowerUpCard> getPowerUpCards(){
        return powerUpCards;
    }

    public void addPowerUpCard(InterfacePowerUpCard powerUpCard){
        powerUpCards.add(powerUpCard);
    }
}
