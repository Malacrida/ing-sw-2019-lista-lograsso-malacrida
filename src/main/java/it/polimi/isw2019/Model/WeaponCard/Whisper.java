package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class Whisper extends AbstractWeaponCard {

    public Whisper() {
        this.name = "Whisper";
        this.color = ColorCube.BLUE;
        this.infoEffect = new ArrayList<String>();
        this.infoEffect.add("EFFECT: Deal 3 damage and 1 mark to 1 target you can see.\n" +
                "Your target must be at least 2 moves away from you.\n");
        this.infoEffect.add("NOTES: Notes: For example, in the 2-by-2 room, you cannot shoot\n" +
                "a target on an adjacent square, but you can shoot a target\n" +
                "on the diagonal. If you are beside a door, you can't shoot\n" +
                "a target on the other side of the door, but you can shoot\n" +
                "a target on a different square of that room.\n");
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
