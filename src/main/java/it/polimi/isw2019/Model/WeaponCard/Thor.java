package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.StateCard;

import java.util.ArrayList;

public class Thor extends AbstractWeaponCard{

    public Thor(int id, String name, ColorCube color) {
        super(3, "T.H.O.R.", ColorCube.BLUE);
        this.infoEffect = new ArrayList<>();
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
        for(int i = 0; i < 2; i ++){
            doOneDamage();
        }
        return false;
    }

    @Override
    public boolean secondEffect() {
        doOneDamage(); //a un giocatore che il primo player a cui hai sparato può vedere
        return false;
    }

    @Override
    public boolean thirdEffect() {
        for(int i = 0; i < 2 ; i ++){
            doOneDamage(); //a un player che il secondo player a cui hai sparato può vedere (reazione a catena)
        }
        return false;
    }

}
