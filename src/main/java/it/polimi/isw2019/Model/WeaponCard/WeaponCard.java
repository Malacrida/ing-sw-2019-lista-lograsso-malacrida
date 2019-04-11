package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

public interface WeaponCard {
    public String getName();

    public String getEffect();

    public int getID();

    public ColorCube getRechargecube();

    public ColorCube getColor();

    public StateCard checkState();

    public boolean isAllowed();

}
