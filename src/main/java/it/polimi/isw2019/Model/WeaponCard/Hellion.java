package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class Hellion extends AbstractWeaponCard {

    public Hellion(int id, String name, ColorCube color) {
        super(11, "Hellion", ColorCube.RED);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC MODE: Deal 1 damage to 1 target you can see at least\n" +
                "1 move away. Then give 1 mark to that target and everyone\n" +
                "else on that square.\n");
        this.infoEffect.add("IN NANO-TRACER MODE: Deal 1 damage to 1 target you can\n" +
                "see at least 1 move away. Then give 2 marks to that target\n" +
                "and everyone else on that square.\n");
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
        putOneMark();
        return false;
    }

    @Override
    public boolean secondEffect() {
        doOneDamage();
        for (int i = 0; i < 2; i++){
            putOneMark();
        }
        return false;
    }

    @Override
    public boolean thirdEffect() {
        return false;
    }
}
