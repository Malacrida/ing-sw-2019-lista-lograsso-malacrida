package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.NoEffectException;
import it.polimi.isw2019.Server.Model.Player;

import java.util.ArrayList;

public class LockRifle extends AbstractWeaponCard {

    public LockRifle() {
        super(1, "Lock Rifle", ColorCube.BLUE, 2);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Deal 2 damage and 1 mark to 1 target you can see.\n");
        this.infoEffect.add("WITH DECOND LOCK: Deal 1 mark to a different target you can see. You have to pay a RED cube");
    }

    @Override
    public void firstEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        if (firstDefender != null){

            firstDefender.sufferDamage(attacker.getColor(), 2, 1);

            this.firstIsValid = true;

        } else {

            throw new ErrorEffectException();

        }
    }

    @Override
    public void secondEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException{

        if (secondDefender != null){

            secondDefender.sufferDamage(attacker.getColor(), 0, 1);

        } else {

            throw new ErrorEffectException();

        }

    }

    @Override
    public void thirdEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException {

        //lancia eccezione perché non esiste questo effetto

        throw new NoEffectException();

    }


}