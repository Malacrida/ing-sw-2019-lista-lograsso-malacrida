package it.polimi.isw2019.Model.PowerUpCard;

import it.polimi.isw2019.Model.StateCard;

public interface PowerUpCardInterface {

    public String getName();

    public String getInfoEffect();

    public int getId();

    public String getColor();

    public StateCard getCheckState();
}
