package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.NoEffectException;
import it.polimi.isw2019.Server.Model.Player;

import java.util.ArrayList;

public class ShotGun extends AbstractWeaponCard {

    public ShotGun() {
        super(18, "Shot Gun", ColorCube.YELLOW, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC MODE : Deal 3 damage to 1 target on" +
                "your square. If you want, you may then move" +
                "the target 1 square.");
        this.infoEffect.add("IN LONG BARREL MODE:Deal 2 damage to" +
                "1 target on any square exactly one move" +
                "away.");
    }

    @Override
    public void firstEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {


        if (sameSquare(attacker, firstDefender)){

            firstDefender.sufferDamage(attacker.getColor(), 3, 0);


        } else {

            throw new ErrorEffectException();

        }

        /* MUOVI UNO firstDefender*/

    }

    @Override
    public void secondEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        if (oneDistance(attacker, firstDefender)){

            firstDefender.sufferDamage(attacker.getColor(), 2, 0);

        } else {

            throw new ErrorEffectException();

        }
    }

    @Override
    public void thirdEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException {

        throw new NoEffectException();

    }

}
