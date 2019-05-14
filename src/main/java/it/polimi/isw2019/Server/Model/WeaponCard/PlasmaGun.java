package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.DamageTrackException;
import it.polimi.isw2019.Server.Model.Exception.NoEffectException;
import it.polimi.isw2019.Server.Model.GameBoard;
import it.polimi.isw2019.Server.Model.Player;

import java.util.ArrayList;

public class PlasmaGun extends AbstractWeaponCard{

    public PlasmaGun() {
        super(4, "Plasma Gun", ColorCube.BLUE, 3);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Deal 2 damage to 1 target you can see.\n");
        this.infoEffect.add("WITH WITH PHASE GLIDE: Move 1 or 2 squares. This effect can be used either before or after the basic effect.\n");
        this.infoEffect.add("WITH CHARGED SHOT: Deal 1 additional damage to 1 additional damage to your target. You have to pay a BLUE cube.\n");
        this.infoEffect.add("NOTES: The two moves have no ammo cost. You don't have" +
                " to be able to see your target when you play the card." +
                "For example, you can move 2 squares and shoot a target" +
                "you now see. You cannot use 1 move before shooting and " +
                "1 move after.");
        this.rechargeCube[0] = 0;
        this.rechargeCube[1] = 1;
        this.rechargeCube[2] = 1;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        if (firstDefender != null){
            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(firstDefender.getX(), firstDefender.getY(), null);

            if (visiblePlayers.contains(firstDefender)){

                try {
                    firstDefender.sufferDamageOrMark(attacker.getColor(), 2,0);
                } catch (DamageTrackException e) {
                    e.printStackTrace();
                }
                firstIsValid = true;
            } else {

                throw new ErrorEffectException();

            }


        } else {

            throw new ErrorEffectException();

        }

        /* AGGIUNGERE CONTROLLO CHE VEDE IL GIOCATORE */
    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        if (gameBoard.isSquareAvailableOnArena(attacker, x1, y1)){
//
            System.out.println("In attesa di changePosition");

        } else {

            throw new ErrorEffectException();

        }

        if (gameBoard.isSquareAvailableOnArena(attacker, x2, y2)){
//
            System.out.println("In attesa di changePosition");

        }


        /* AGGIUNGI MUOVI DI DUE */
    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException, ErrorEffectException, DamageTrackException {

        if (!machineGunAndPlasmaGunEffect(attacker, firstDefender, firstIsValid)){

            throw new ErrorEffectException();

        }
    }
}
