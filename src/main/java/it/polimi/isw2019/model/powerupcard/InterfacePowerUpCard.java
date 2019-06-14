package it.polimi.isw2019.model.powerupcard;

import it.polimi.isw2019.model.ColorRoom;

public interface InterfacePowerUpCard {

    public String[][] getPowerUpCardRepresentation();

    public InterfacePowerUpCard getPowerUpCard();

    public int getNumberColorPayment();

    public int getIdPowerUpCard();

    ColorRoom getPowerUpColor();
}
