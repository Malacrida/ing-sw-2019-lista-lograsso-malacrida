package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class ZX_2 extends AbstractWeaponCard {

    public ZX_2(int id, String name, ColorCube color) {
        super(17, "ZX_2", ColorCube.YELLOW);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT :Deal 1 damage and 2 marks to\n" +
                "1 target you can see");
        this.infoEffect.add("IN SCANNER MODE : Choose up to 3 targets you "+
                "can see and deal 1 mark to each.");
        this.infoEffect.add("NOTE : Remember that the 3 targets can be\n" +
                "in 3 different rooms.   ");
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
        for(int i = 0; i < 2; i++){
            putOneMark();
        }
        return false;
    }

    @Override
    public boolean secondEffect() {
        putOneMark();
        return false;
    }

    @Override
    public boolean thirdEffect() {
        return false;
    }
}
