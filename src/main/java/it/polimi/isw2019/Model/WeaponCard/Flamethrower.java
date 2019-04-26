package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class Flamethrower extends AbstractWeaponCard {


    public Flamethrower(int id, String name, ColorCube color) {
        super(12, "Flamethrower", ColorCube.RED);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC MODE: Choose a square 1 move away and possibly a second square\n" +
                "1 more move away in the same direction. On each square, you may\n" +
                "choose 1 target and give it 1 damage.\n");
        this.infoEffect.add("IN BARBECUE MODE: Choose 2 squares as above. Deal 2 damage to\n" +
                "everyone on the first square and 1 damage to everyone on the second\n" +
                "square.\n");
        this.infoEffect.add("NOTES: This weapon cannot damage anyone in your square. However,\n" +
                "it can sometimes damage a target you can't see – the flame won't go\n" +
                "through walls, but it will go through doors. Think of it as a straight-line\n" +
                "blast of flame that can travel 2 squares in a cardinal direction.\n");
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
        return false;
    }

    @Override
    public boolean secondEffect() {
        for(int i = 0; i < 2; i++){
            doOneDamage();
        }
        doOneDamage();
        return false;
    }

    @Override
    public boolean thirdEffect() {
        return false;
    }
}
