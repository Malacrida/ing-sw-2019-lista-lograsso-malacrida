package it.polimi.isw2019.Model.AmmoTile;

import it.polimi.isw2019.Model.Cube;
import it.polimi.isw2019.Model.PowerUpCard.AbstractPowerUpCard;
import it.polimi.isw2019.Model.PowerUpCard.PowerUpCard;
import it.polimi.isw2019.Model.StateCard;

public interface AmmoTile {

    public int getId();

    public Cube getCube();

    public int getIdPowerUpCard();

    public StateCard checkState();

    public boolean checkCard();

    public boolean isAllowed();
}
