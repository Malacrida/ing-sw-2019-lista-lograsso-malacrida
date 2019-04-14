package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class Thor extends AbstractWeaponCard{

    public Thor(){
        this.name = "T.H.O.R.";
        this.color = ColorCube.BLUE;
        this.infoEffect = new ArrayList<String>();
        this.infoEffect.add("BASIC EFFECT: Deal 2 damage to 1 target you can see.\n");
        this.infoEffect.add("WITH CHAIN REACTION: Deal 1 damage to a second target that your first target can see. You have to pay a BLUE cube\n");
        this.infoEffect.add("WITH HIGH REACTION: Deal 2 damage to a third target that your second target can see. You have to pay a BLUE cube\n" +
                "You cannot use this effect unless you first use the chain reaction");
        this.infoEffect.add("NOTES: This card constrains the order in which you can use its effects." +
                "(Most cards don't.)" +
                "Also note that each target must be a different player\n");
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
