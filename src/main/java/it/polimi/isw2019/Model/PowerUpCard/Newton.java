package it.polimi.isw2019.Model.PowerUpCard;

import it.polimi.isw2019.Model.StateCard;

public class Newton extends AbstractPowerUpCard {

    private String name = "Newton";

    private StateCard stateCard = StateCard.DECK;

    @Override
    public String getEffect() {
        return null;
    }

    @Override
    public boolean isAllowed() {
        return false;
    }
}
