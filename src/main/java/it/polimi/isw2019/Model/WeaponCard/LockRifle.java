package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

public class LockRifle extends AbstractWeaponCard {
    private String name = "Lock Rifle";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEffect() {
        return null;
    }

    @Override
    public int getID() {
        return 0;
    }


    @Override
    public ColorCube getRechargecube() {
        return null;
    }

    @Override
    public ColorCube getColor() {
        return null;
    }

    @Override
    public StateCard checkState() {
        return null;
    }

    @Override
    public boolean isAllowed() {
        return false;
    }
}
