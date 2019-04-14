package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class ShotGun extends AbstractWeaponCard {
    public ShotGun(){
        this.name = "ShotGun";
        this.infoEffect = new ArrayList<String>();
        this.color= ColorCube.YELLOW;
        this.infoEffect.add("BASIC MODE : Deal 3 damage to 1 target on" +
                "your square. If you want, you may then move" +
                "the target 1 square.");
        this.infoEffect.add("IN LONG BARREL MODE:Deal 2 damage to" +
                "1 target on any square exactly one move" +
                "away.");
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
