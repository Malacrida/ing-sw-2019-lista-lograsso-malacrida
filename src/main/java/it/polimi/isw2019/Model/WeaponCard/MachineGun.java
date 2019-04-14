package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class MachineGun extends AbstractWeaponCard {


    public MachineGun() {
        this.name = "Machine Gun";
        this.color = ColorCube.BLUE;
        this.infoEffect = new ArrayList<String>();
        infoEffect.add("BASIC EFFECT: Choose 1 or 2 targets you can see and deal 1 damage to each.\n");
        infoEffect.add("WITH FOCUS SHOT: Deal 1 additional damage to one of those targets. You have to pay a YELLOW cube.");
        infoEffect.add("WITH TURRET TRIPOD: Deal 1 additional damage to the other of those targets and/or deal 1 damage to a different target you can see. You have to pay a BLUE cube.\n");
        infoEffect.add("NOTES: If you deal both additional points of damage, they must be dealt ot 2 different targhets.\n" +
                "If you see only 2 targets, you deal 2 to each if you use both optional effects.\n" +
                "If you use the basic effect on only 1 target, you can still use the turret tripod to give it 1 additional damage");
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
