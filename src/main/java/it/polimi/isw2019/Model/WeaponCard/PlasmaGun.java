package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class PlasmaGun extends AbstractWeaponCard{

    public PlasmaGun(int id, String name, ColorCube color) {
        super(4, "Plasma Gun", ColorCube.BLUE);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Deal 2 damage to 1 target you can see.\n");
        this.infoEffect.add("WITH WITH PHASE GLIDE: Move 1 or 2 squares. This effect can be used either before or after the basic effect.\n");
        this.infoEffect.add("WITH CHARGED SHOT: Deal 1 additional damage to 1 additional damage to your target. You have to pay a BLUE cube.\n");
        this.infoEffect.add("NOTES: The two moves have no ammo cost. You don't have" +
                " to be able to see your target when you play the card." +
                "For example, you can move 2 squares and shoot a target" +
                "you now see. You cannot use 1 move before shooting and " +
                "1 move after.");
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
        for (int i = 0; i < 2; i++){
            doOneDamage();
        }
        return false;
    }

    @Override
    public boolean secondEffect() {
        moveOneSquare();
        return false;
    }

    @Override
    public boolean thirdEffect() {
        doOneDamage();
        return false;
    }

}
