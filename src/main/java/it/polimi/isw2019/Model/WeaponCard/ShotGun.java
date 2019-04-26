package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class ShotGun extends AbstractWeaponCard {

    public ShotGun(int id, String name, ColorCube color) {
        super(18, "Shot Gun", ColorCube.YELLOW);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC MODE : Deal 3 damage to 1 target on" +
                "your square. If you want, you may then move" +
                "the target 1 square.");
        this.infoEffect.add("IN LONG BARREL MODE:Deal 2 damage to" +
                "1 target on any square exactly one move" +
                "away.");
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
        for(int i = 0; i < 3; i++){
            doOneDamage();
        }
        moveOneSquare();
        return false;
    }

    @Override
    public boolean secondEffect() {
        for(int i = 0; i < 2; i++){
            doOneDamage();
        }
        return false;
    }

    @Override
    public boolean thirdEffect() {
        return false;
    }
}
