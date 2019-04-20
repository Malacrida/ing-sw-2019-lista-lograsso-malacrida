package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;
import java.util.ArrayList;

public class SledgeHammer extends AbstractWeaponCard {

    public SledgeHammer(int id, String name, ColorCube color) {
        super(21, "SledgeHammer", ColorCube.YELLOW);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("Deal 2 damage to 1 target on your square.");
        this.infoEffect.add("Deal 3 damage to 1 target on your square, then move that target 0, 1, or 2 squares in one direction.");
        this.infoEffect.add("NOTES: Remember that moves go through" +
                "doors, but not walls.");
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEffect() {
        return null;
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
}