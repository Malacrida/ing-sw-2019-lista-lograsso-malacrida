package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;
import java.util.ArrayList;

public class SledgeHammer extends AbstractWeaponCard {

    public SledgeHammer(int id, String name, ColorCube color) {
        super(21, "SledgeHammer", ColorCube.YELLOW);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("Deal 2 damage to 1 target on your square.");
        this.infoEffect.add("Deal 3 damage to 1 target on your square, then move that target 0, 1, or 2 squares in one direction.");
        this.infoEffect.add("NOTES: Remember that moves go through" +
                "doors, but not walls.");
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
        for(int i = 0; i < 2; i++){
            doOneDamage();
        }
        return false;
    }

    @Override
    public boolean secondEffect() {
        for(int i = 0; i < 3; i++){
            doOneDamage();
        }
        moveOneSquare();
        return false;
    }

    @Override
    public boolean thirdEffect() {
        return false;
    }
}