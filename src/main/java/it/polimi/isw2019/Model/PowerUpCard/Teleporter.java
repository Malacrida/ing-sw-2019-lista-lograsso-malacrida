package it.polimi.isw2019.Model.PowerUpCard;

import it.polimi.isw2019.Model.PowerUpCard.AbstractPowerUpCard;
import it.polimi.isw2019.Model.StateCard;

public class Teleporter extends AbstractPowerUpCard {

    private String name = "Teleporter";

    private StateCard stateCard;

    @Override
    public String getEffect() {
        return null;
    }

    @Override
    public boolean isAllowed() {
        return false;
    }
}
