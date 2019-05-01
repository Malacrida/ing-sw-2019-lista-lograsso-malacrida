package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Player;
import it.polimi.isw2019.Model.Square;

import java.util.ArrayList;

public class SledgeHammer extends AbstractWeaponCard {

    public SledgeHammer(int id, String name, ColorCube color) {
        super(21, "SledgeHammer", ColorCube.YELLOW);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("Deal 2 damage to 1 target on your square.");
        this.infoEffect.add("Deal 3 damage to 1 target on your square, then move that target 0, 1, or 2 squares in one direction.");
        this.infoEffect.add("NOTES: Remember that moves go through" +
                "doors, but not walls.");
    }

    @Override
    public boolean firstEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        /*CONTORLLO NELLA CHE I DUE GIOCATORI SONO NELLA STESSA STANZA*/

        firstDefender.sufferDamage(attacker.getColor(), 2, 0);

        return false;
    }

    @Override
    public boolean secondEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        /*CONTORLLO NELLA CHE I DUE GIOCATORI SONO NELLA STESSA STANZA*/

        firstDefender.sufferDamage(attacker.getColor(), 3, 0);

        /*MUOVI DI DUE */

        return false;
    }

    @Override
    public boolean thirdEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {
        return false;
    }

}