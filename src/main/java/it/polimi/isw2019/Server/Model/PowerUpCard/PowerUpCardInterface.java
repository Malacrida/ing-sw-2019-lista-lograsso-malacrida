package it.polimi.isw2019.Server.Model.PowerUpCard;

import it.polimi.isw2019.Server.Model.StateCard;

public interface PowerUpCardInterface {

    public int getId();

    public String getName();

    public String getInfoEffect();

    public String getColor();

    public StateCard getCheckState();

    public void effect();
}
