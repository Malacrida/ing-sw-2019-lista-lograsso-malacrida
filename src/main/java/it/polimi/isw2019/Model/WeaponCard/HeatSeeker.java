package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.ErrorEffect;
import it.polimi.isw2019.Model.Exception.NoEffectException;
import it.polimi.isw2019.Model.Player;

import java.util.ArrayList;

public class HeatSeeker extends AbstractWeaponCard {

    public HeatSeeker() {
        super(10, "HeatSeeker", ColorCube.RED, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("EFFECT: Choose 1 target you cannot see and deal 3 damage " +
                "to it.");
        this.infoEffect.add("NOTE : Yes, this can only hit targets you cannot see. ");
    }

    @Override
    public void firstEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffect {

        /* AGGIUNGERE CONTROLLO BERSAGLIO NON VISIBILE*/
        if (firstDefender != null){

            firstDefender.sufferDamage(attacker.getColor(), 3, 0);

        } else {

            throw new ErrorEffect();

        }

    }

    @Override
    public void secondEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException{
        /* NON ESISTE L'EFFETTO */
        throw new NoEffectException();
    }

    @Override
    public void thirdEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException{
        /* NON ESISTE L'EFFETTO */
        throw new NoEffectException();
    }

}
