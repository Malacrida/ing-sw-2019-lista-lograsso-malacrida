package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Player;
import it.polimi.isw2019.Model.Square;

import java.util.ArrayList;

public class LockRifle extends AbstractWeaponCard {

    public LockRifle(int id, String name, ColorCube color) {
        super(1, "Lock Rifle", ColorCube.BLUE);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Deal 2 damage and 1 mark to 1 target you can see.\n");
        this.infoEffect.add("WITH DECOND LOCK: Deal 1 mark to a different target you can see. You have to pay a RED cube");
    }

    @Override
    public boolean firstEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        if (firstAttackSquare.findPlayer(firstDefender)){
            firstDefender.sufferDamage(attacker.getColor(), 2, 1);
            return true;
        }

        else {
            return false;
        }
    }

    @Override
    public boolean secondEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {

        if (this.firstEffect(attacker, firstAttackSquare, firstDefender, secondAttackSquare, secondDefender, thirdAttackSquare, thirdDefender)) {

            if(secondAttackSquare.findPlayer(secondDefender)){
                secondDefender.sufferDamage(attacker.getColor(), 0, 1);
                return true;
            }
            else return false;
        }
        else return false;
    }

    @Override
    public boolean thirdEffect(Player attacker, Square firstAttackSquare, Player firstDefender, Square secondAttackSquare, Player secondDefender, Square thirdAttackSquare, Player thirdDefender) {
        //lancia eccezione perché non esiste questo effetto

        return false;
    }


}
