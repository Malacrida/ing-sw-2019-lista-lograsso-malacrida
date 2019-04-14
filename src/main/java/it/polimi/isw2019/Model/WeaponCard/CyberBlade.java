package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class CyberBlade extends AbstractWeaponCard {

    public CyberBlade(){
        this.name = "CyberBlade";
        this.infoEffect = new ArrayList<String>();
        this.color= ColorCube.YELLOW;
        this.infoEffect.add("BASIC EFFECT : Deal 2 damage to 1 target on your square.\n");
        this.infoEffect.add("WITH SHADOWSTEP: move 1 square before or after the basic effect");
        this.infoEffect.add("WITH SLICE AND DICE : to a different target on your square the shadowstep may be used before or after this effect.");
        this.infoEffect.add("NOTE : Combining all effects allows you to move onto a square and\n" +
                "whack 2 people; or whack somebody, move, and whack somebody else;\n" +
                "or whack 2 people and then move.   ");
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
