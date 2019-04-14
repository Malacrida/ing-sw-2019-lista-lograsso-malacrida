package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class Hellion extends AbstractWeaponCard {


    public Hellion() {
        this.name = "Hellion";
        this.color = ColorCube.RED;
        this.infoEffect = new ArrayList<String>();
        this.infoEffect.add("BASIC MODE: Deal 1 damage to 1 target you can see at least\n" +
                "1 move away. Then give 1 mark to that target and everyone\n" +
                "else on that square.\n");
        this.infoEffect.add("IN NANO-TRACER MODE: Deal 1 damage to 1 target you can\n" +
                "see at least 1 move away. Then give 2 marks to that target\n" +
                "and everyone else on that square.\n");
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
