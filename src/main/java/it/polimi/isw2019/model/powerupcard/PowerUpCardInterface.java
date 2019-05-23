package it.polimi.isw2019.model.powerupcard;

import it.polimi.isw2019.model.StateCard;

public interface PowerUpCardInterface {

    public int getId();

    public String getName();

    public String getInfoEffect();

    public String getColor();

    public StateCard getCheckState();

    public void effect();
}
