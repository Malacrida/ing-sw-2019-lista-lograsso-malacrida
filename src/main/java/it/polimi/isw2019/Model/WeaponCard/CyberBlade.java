package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Player;
import it.polimi.isw2019.Model.Square;

import java.util.ArrayList;

public class CyberBlade extends AbstractWeaponCard {

    public CyberBlade(int id, String name, ColorCube color) {
        super(16, "Cyber Blade", ColorCube.YELLOW);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT : Deal 2 damage to 1 target on your square.\n");
        this.infoEffect.add("WITH SHADOWSTEP: move 1 square before or after the basic effect");
        this.infoEffect.add("WITH SLICE AND DICE : to a different target on your square the shadowstep may be used before or after this effect.");
        this.infoEffect.add("NOTE : Combining all effects allows you to move onto a square and\n" +
                "whack 2 people; or whack somebody, move, and whack somebody else;\n" +
                "or whack 2 people and then move.   ");
    }


    @Override
    public boolean firstEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        if ((firstAttackSquare.findPlayer(attacker)) && (firstAttackSquare.findPlayer(firstDefender))) {

            firstDefender.sufferDamage(attacker.getColor(), 2, 0);
            return true;
        } else return false;

    }

    @Override
    public boolean secondEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {
        return false;
        //Aggiungere moveOneSquareAttacker

    }

    @Override
    public boolean thirdEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        if ((firstAttackSquare.findPlayer(attacker)) && (firstAttackSquare.findPlayer(firstDefender))) {

            firstDefender.sufferDamage(attacker.getColor(), 2, 0);

            return true;

        } else return false;
    }
}

