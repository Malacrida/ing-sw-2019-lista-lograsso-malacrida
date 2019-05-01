package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Player;
import it.polimi.isw2019.Model.Square;

import java.util.ArrayList;

public class MachineGun extends AbstractWeaponCard {

    public MachineGun(int id, String name, ColorCube color) {
        super(2, "Machine Gun", ColorCube.BLUE);
        this.infoEffect = new ArrayList<>();
        infoEffect.add("BASIC EFFECT: Choose 1 or 2 targets you can see and deal 1 damage to each.\n");
        infoEffect.add("WITH FOCUS SHOT: Deal 1 additional damage to one of those targets. You have to pay a YELLOW cube.");
        infoEffect.add("WITH TURRET TRIPOD: Deal 1 additional damage to the other of those targets and/or deal 1 damage to a different target you can see. You have to pay a BLUE cube.\n");
        infoEffect.add("NOTES: If you deal both additional points of damage, they must be dealt ot 2 different targhets.\n" +
                "If you see only 2 targets, you deal 2 to each if you use both optional effects.\n" +
                "If you use the basic effect on only 1 target, you can still use the turret tripod to give it 1 additional damage");
    }

    @Override
    public boolean firstEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        /* Se il primo giocatore da attaccare è nella prima stanza selezionata allora dai un danno */

        if(firstAttackSquare.findPlayer(firstDefender)){

            firstDefender.sufferDamage(attacker.getColor(), 1, 0);

            /* Se seleziona il secondo giocatore da attaccare ed è nella seconda stanza selezionata allora dai un danno */

            if ((secondDefender != null) && (secondAttackSquare.findPlayer(secondDefender))){

                secondDefender.sufferDamage(attacker.getColor(), 1, 0);

            }

            return true;
        }

        else return false;
    }

    @Override
    public boolean secondEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        /* Se il primo effetto è valido allora aggiunge un danno al primo giocatore a cui ha sparato*/

       firstDefender.sufferDamage(attacker.getColor(), 1, 0);

       return true;
    }



    /*DA SISTEMARE QUESTO EFFETTO*/
    @Override
    public boolean thirdEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        secondDefender.sufferDamage(attacker.getColor(), 1, 0);

        if ((thirdDefender != null) && (thirdAttackSquare.findPlayer(thirdDefender))){

            thirdDefender.sufferDamage(attacker.getColor(), 1, 0);

            return true;

        }

        else return false;
    }
}
