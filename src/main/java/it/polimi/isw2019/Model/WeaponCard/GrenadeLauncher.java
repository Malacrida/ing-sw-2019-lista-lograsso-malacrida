package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class GrenadeLauncher extends AbstractWeaponCard {

    public GrenadeLauncher(int id, String name, ColorCube color) {
        super(13, "Grenade Launcher", ColorCube.RED);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Deal 1 damage to 1 target you can see. Then you may move\n" +
                "the target 1 square.\n");
        this.infoEffect.add("WITH EXTRA GRENADE: Deal 1 damage to every player on a square you can\n" +
                "see. You can use this before or after the basic effect's move.\n");
        this.infoEffect.add("NOTES: For example, you can shoot a target, move it onto a square with\n" +
                "other targets, then damage everyone including the first target.\n" +
                "Or you can deal 2 to a main target, 1 to everyone else on that square,\n" +
                "then move the main target. Or you can deal 1 to an isolated target and\n" +
                "1 to everyone on a different square. If you target your own square,\n" +
                "you will not be moved or damaged.\n");
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
        moveOneSquare();
        return false;
    }

    @Override
    public boolean secondEffect() {
        doOneDamage();
        return false;
    }

    @Override
    public boolean thirdEffect() {

        return false;
    }
}