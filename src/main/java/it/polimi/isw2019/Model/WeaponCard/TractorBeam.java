package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.ErrorEffect;
import it.polimi.isw2019.Model.Exception.NoEffectException;
import it.polimi.isw2019.Model.Player;

import java.util.ArrayList;

public class TractorBeam extends AbstractWeaponCard {

    public TractorBeam() {
        super(7, "Tractor Beam", ColorCube.BLUE, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Move a target 0,1 or 2 squares to a square you can see, and give it 1 damage.\n");
        this.infoEffect.add("IN PUNISHER MODE: Choose a target 0,1 or 2 moves away from you. Move the target to your square and deal 3 damage to it.\n");
        this.infoEffect.add("NOTES: You can move a target even if you can't see it. The target ends up in a place where you can see and damage it. The moves do not have to be in the same direction.\n");
    }

    @Override
    public void firstEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffect {

        /*MUOVI DI DUE*/

        if(firstDefender != null){

            firstDefender.sufferDamage(attacker.getColor(), 1, 0);

        } else {

            throw new ErrorEffect();

        }

    }

    @Override
    public void secondEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffect {

        /*MUOVI DI DUE*/

        if(secondDefender != null){

            secondDefender.sufferDamage(attacker.getColor(), 3, 0);

        } else {

            throw new ErrorEffect();

        }
    }

    @Override
    public void thirdEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException {
        throw new NoEffectException();

    }

}
