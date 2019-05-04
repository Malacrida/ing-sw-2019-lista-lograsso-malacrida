package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.ErrorEffect;
import it.polimi.isw2019.Model.Player;

import java.util.ArrayList;

public class Thor extends AbstractWeaponCard{

    public Thor() {
        super(3, "T.H.O.R.", ColorCube.BLUE,3);
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
    public void firstEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffect {

        if (firstDefender != null) {

            firstDefender.sufferDamage(attacker.getColor(), 2, 0);
            this.firstIsValid = true;

        } else {

            throw new ErrorEffect();
        }
    }

    @Override
    public void secondEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffect{

        if (this.firstIsValid){

            secondDefender.sufferDamage(attacker.getColor(), 1, 0);
            this.secondIsValid = true;

        } else {

            throw new ErrorEffect();

        }
    }

    @Override
    public void thirdEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffect {

        if(this.secondIsValid) {

            thirdDefender.sufferDamage(attacker.getColor(), 2, 0);

        } else {

            throw new ErrorEffect();

        }
    }

}
