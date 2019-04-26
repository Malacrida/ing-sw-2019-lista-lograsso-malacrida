package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class MachineGun extends AbstractWeaponCard {

    public MachineGun(int id, String name, ColorCube color) {
        super(2, "Machine Gun", ColorCube.BLUE);
        this.infoEffect = new ArrayList<>();
        infoEffect.add("BASIC EFFECT: Choose 1 or 2 targets you can see and deal 1 damage to each.\n");
        infoEffect.add("WITH FOCUS SHOT: Deal 1 additional damage to one of those targets. You have to pay a YELLOW cube.");
        infoEffect.add("WITH TURRET TRIPOD: Deal 1 additional damage to the other of those targets and/or deal 1 damage to a different target you can see. You have to pay a BLUE cube.\n");
        infoEffect.add("NOTES: If you deal both additional points of damage, they must be dealt ot 2 different targhets.\n" +
                "If you see only 2 targets, you deal 2 to each if you use both optional effects.\n" +
                "If you use the basic effect on only 1 target, you can still use the turret tripod to give it 1 additional damage");
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
        doOneDamage(); //a 2 players che puoi vedere
        return false;
    }

    @Override
    public boolean secondEffect() {
        doOneDamage(); //al primo player che hai sparato
        return false;
    }

    @Override
    public boolean thirdEffect() {
        doOneDamage(); //al secondo player che hai sparato e a un terzo player
        return false;
    }
}
