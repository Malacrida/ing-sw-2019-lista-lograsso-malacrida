package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class Electroscythe extends AbstractWeaponCard {

    public Electroscythe() {
        this.name = "Electroscythe";
        this.color = ColorCube.BLUE;
        this.infoEffect = new ArrayList<String>();
        this.infoEffect.add("BASIC EFFECT: Deal 1 damage and to every other player on your square.\n");
        this.infoEffect.add("IN REAPER MODE: Deal 2 damage to every other player on your square. You have to pay a BLUE cube and a RED cube.\n");
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
