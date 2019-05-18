package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.DamageTrackException;
import it.polimi.isw2019.Server.Model.Exception.NoEffectException;
import it.polimi.isw2019.Server.Model.GameBoard;
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
        this.rechargeCube[0] = 1;
        this.rechargeCube[1] = 0;
        this.rechargeCube[2] = 1;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        if (firstDefender != null) {

            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(firstDefender.getX(), firstDefender.getY(), null);

            if (visiblePlayers.contains(firstDefender)) {
                try {
                    firstDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);
                } catch ( DamageTrackException e) {
                    e.printStackTrace();
                }
                /* Se seleziona il secondo giocatore da attaccare ed è nella seconda stanza selezionata allora dai un danno */

                if (secondDefender != null) {
                    if (visiblePlayers.contains(secondDefender)) {

                        try {
                            secondDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);
                        } catch (DamageTrackException e) {
                            e.printStackTrace();
                        }

                        this.firstIsValid = true;
                    } else {

                        throw new ErrorEffectException();
                    }

                }

            } else {
                throw new ErrorEffectException();
            }
        } else {

            throw new ErrorEffectException();
        }
    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        /* Se il primo effetto è valido allora aggiunge un danno al primo giocatore a cui ha sparato*/

        if (!machineGunAndPlasmaGunEffect(attacker, firstDefender, firstIsValid)){

            throw new ErrorEffectException();

        }
    }



    /*DA SISTEMARE QUESTO EFFETTO*/
    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        if (firstIsValid){

            try {
                secondDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);
            } catch (DamageTrackException e) {
                e.printStackTrace();
            }

            if (thirdDefender != null){

                try {
                    thirdDefender.sufferDamageOrMark(attacker.getColor(), 1, 0);
                } catch (DamageTrackException e) {
                    e.printStackTrace();
                }

            }

        } else {

            throw new ErrorEffectException();

        }
    }
}
