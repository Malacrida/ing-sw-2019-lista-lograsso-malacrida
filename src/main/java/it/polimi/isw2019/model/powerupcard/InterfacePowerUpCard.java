package it.polimi.isw2019.model.powerupcard;

import it.polimi.isw2019.model.ColorRoom;

public interface InterfacePowerUpCard {

    String[][] getPowerUpCardRepresentation();

    InterfacePowerUpCard getPowerUpCard();

    String infoEffect();

    String getName();

    int getNumberColorPayment();

    int getIdPowerUpCard();

    ColorRoom getPowerUpColor();


}
