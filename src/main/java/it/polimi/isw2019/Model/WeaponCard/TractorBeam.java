package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class TractorBeam extends AbstractWeaponCard {

    public TractorBeam(int id, String name, ColorCube color) {
        super(7, "Tractor Beam", ColorCube.BLUE);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Move a target 0,1 or 2 squares to a square you can see, and give it 1 damage.\n");
        this.infoEffect.add("IN PUNISHER MODE: Choose a target 0,1 or 2 moves away from you. Move the target to your square and deal 3 damage to it.\n");
        this.infoEffect.add("NOTES: You can move a target even if you can't see it. The target ends up in a place where you can see and damage it. The moves do not have to be in the same direction.\n");
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
