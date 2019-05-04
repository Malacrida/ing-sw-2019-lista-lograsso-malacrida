package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.ErrorEffect;
import it.polimi.isw2019.Model.Exception.NoEffectException;
import it.polimi.isw2019.Model.Player;

import java.util.ArrayList;

public class SledgeHammer extends AbstractWeaponCard {

    public SledgeHammer() {
        super(21, "SledgeHammer", ColorCube.YELLOW, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("Deal 2 damage to 1 target on your square.");
        this.infoEffect.add("Deal 3 damage to 1 target on your square, then move that target 0, 1, or 2 squares in one direction.");
        this.infoEffect.add("NOTES: Remember that moves go through" +
                "doors, but not walls.");
    }

    @Override
    public void firstEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffect {

        if (sameSquare(attacker, firstDefender)){

            firstDefender.sufferDamage(attacker.getColor(), 2, 0);

        } else {

            throw new ErrorEffect();

        }
    }

    @Override
    public void secondEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffect{

        if (sameSquare(attacker, firstDefender)){

            firstDefender.sufferDamage(attacker.getColor(), 3, 0);

            /* MUOVI DI 2 */

        } else {

            throw new ErrorEffect();

        }

    }

    @Override
    public void thirdEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException {
        throw new NoEffectException();

    }

}