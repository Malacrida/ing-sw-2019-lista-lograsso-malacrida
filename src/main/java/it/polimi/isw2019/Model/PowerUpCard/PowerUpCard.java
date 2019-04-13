package it.polimi.isw2019.Model.PowerUpCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

public interface PowerUpCard {

    public String getName();

    public String getInfoEffect();

    public int getId();

    public ColorCube getColor();

    public StateCard getCheckState();
}
