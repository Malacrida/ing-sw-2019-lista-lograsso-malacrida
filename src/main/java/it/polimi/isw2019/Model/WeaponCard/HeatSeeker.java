package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class HeatSeeker extends AbstractWeaponCard {
    public HeatSeeker(){
        this.name = "HeatSeeker";
        this.infoEffect = new ArrayList<String>();
        this.color= ColorCube.RED;
        this.infoEffect.add("EFFECT: Choose 1 target you cannot see and deal 3 damage " +
                "to it.");
        this.infoEffect.add("NOTE : Yes, this can only hit targets you cannot see. ");
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
