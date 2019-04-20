package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class VortexCannon extends AbstractWeaponCard {

    public VortexCannon(int id, String name, ColorCube color) {
        super(8, "Vortex Cannon", ColorCube.RED);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: basic effect: Choose a square you can see, but not your" +
                "square. Call it the vortex. Choose a target on the vortex" +
                "or 1 move away from it. Move it onto the vortex and give it" +
                "2 damage.");
        this.infoEffect.add("WITH BLACK HOLE :Choose up to 2 other targets on the" +
                "vortex or 1 move away from it. Move them onto the vortex" +
                "and give them each 1 damage.");
        this.infoEffect.add("NOTE :The 3 targets must be different, but some might" +
                "start on the same square. It is legal to choose targets on" +
                "your square, on the vortex, or even on squares you can't" +
                "see. They all end up on the vortex. ");
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
