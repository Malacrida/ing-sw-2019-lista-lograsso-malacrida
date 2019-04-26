package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class LockRifle extends AbstractWeaponCard {

    public LockRifle(int id, String name, ColorCube color) {
        super(1, "Lock Rifle", ColorCube.BLUE);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Deal 2 damage and 1 mark to 1 target you can see.\n");
        this.infoEffect.add("WITH DECOND LOCK: Deal 1 mark to a different target you can see. You have to pay a RED cube");
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
        //fai due danni e un marchio
        for(int i = 0; i < 2; i++){
            doOneDamage();
        }
        putOneMark();
        return false;
    }

    @Override
    public boolean secondEffect() {
        this.firstEffect();
        putOneMark(); //giocatore differente
        return false;
    }

    @Override
    public boolean thirdEffect() {
        return false;
    }
}
