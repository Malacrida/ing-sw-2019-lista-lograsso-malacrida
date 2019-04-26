package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class HeatSeeker extends AbstractWeaponCard {

    public HeatSeeker(int id, String name, ColorCube color) {
        super(10, "HeatSeeker", ColorCube.RED);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("EFFECT: Choose 1 target you cannot see and deal 3 damage " +
                "to it.");
        this.infoEffect.add("NOTE : Yes, this can only hit targets you cannot see. ");
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
        for (int i = 0; i < 3; i++){
            doOneDamage();
        }
        return false;
    }

    @Override
    public boolean secondEffect() {
        return false;
    }

    @Override
    public boolean thirdEffect() {
        return false;
    }
}
