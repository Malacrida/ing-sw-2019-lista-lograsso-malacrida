package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Player;

import java.util.ArrayList;

public class MachineGun extends AbstractWeaponCard {

    public MachineGun() {
        super(2, "Machine Gun", ColorCube.BLUE, 3);
        this.infoEffect = new ArrayList<>();
        infoEffect.add("BASIC EFFECT: Choose 1 or 2 targets you can see and deal 1 damage to each.\n");
        infoEffect.add("WITH FOCUS SHOT: Deal 1 additional damage to one of those targets. You have to pay a YELLOW cube.");
        infoEffect.add("WITH TURRET TRIPOD: Deal 1 additional damage to the other of those targets and/or deal 1 damage to a different target you can see. You have to pay a BLUE cube.\n");
        infoEffect.add("NOTES: If you deal both additional points of damage, they must be dealt ot 2 different targhets.\n" +
                "If you see only 2 targets, you deal 2 to each if you use both optional effects.\n" +
                "If you use the basic effect on only 1 target, you can still use the turret tripod to give it 1 additional damage");
    }

    @Override
    public void firstEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        /*Verificare se lo vede*/
        if (firstDefender != null){

            firstDefender.sufferDamage(attacker.getColor(), 1, 0);
            /* Se seleziona il secondo giocatore da attaccare ed è nella seconda stanza selezionata allora dai un danno */

            if (secondDefender != null){

                secondDefender.sufferDamage(attacker.getColor(), 1, 0);

                this.firstIsValid = true;
            }
        }
        else {

            throw new ErrorEffectException();

        }

    }

    @Override
    public void secondEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        /* Se il primo effetto è valido allora aggiunge un danno al primo giocatore a cui ha sparato*/

        if (firstIsValid){

            firstDefender.sufferDamage(attacker.getColor(), 1, 0);

        } else {

            throw new ErrorEffectException();

        }
    }



    /*DA SISTEMARE QUESTO EFFETTO*/
    @Override
    public void thirdEffect(Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        if (firstIsValid){

            secondDefender.sufferDamage(attacker.getColor(), 1, 0);

            if (thirdDefender != null){

                thirdDefender.sufferDamage(attacker.getColor(), 1, 0);

            }

        } else {

            throw new ErrorEffectException();

        }
    }
}
