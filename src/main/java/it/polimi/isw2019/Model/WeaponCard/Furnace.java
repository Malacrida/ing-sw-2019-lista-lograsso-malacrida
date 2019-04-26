package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class Furnace extends AbstractWeaponCard {

    public Furnace(int id, String name, ColorCube color) {
        super(9, "Furnace", ColorCube.RED);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Choose a room you can see, but not the room\n" +
                "you are in. Deal 1 damage to everyone in that room.\n");
        this.infoEffect.add("IN COZY FIRE MODE: Choose a square exactly one move\n" +
                "away. Deal 1 damage and 1 mark to everyone on that\n" +
                "square.\n");
    }


    @Override
    public int getID() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ArrayList<String> getInfoEffect() {
        return infoEffect;
    }

    @Override
    public ArrayList<ColorCube> getRechargecube() {
        return rechargeCube;
    }

    @Override
    public ColorCube getColor() {
        return color;
    }

    @Override
    public StateCard checkState() {
        return stateCard;
    }

    @Override
    public boolean firstEffect() {
        doOneDamage();
        return false;
    }

    @Override
    public boolean secondEffect() {
        doOneDamage();
        putOneMark();
        return false;
    }

    @Override
    public boolean thirdEffect() {

        return false;
    }
}
