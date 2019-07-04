package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class LockRifle extends AbstractWeaponCard {

    public LockRifle() {
        super(1, "Lock Rifle", ColorCube.BLUE, 2, 0, 2);
        this.infoEffect[0] = "FIRST EFFECT: Deal 2 damage and 1 mark to 1 target you can see.";
        this.infoEffect[1] = "SECOND EFFECT: Deal 1 mark to a different target you can see. You have to pay a RED cube.\n";
        this.infoEffect[2] = "THIRD EFFECT: This effect doesn't exist;\n";
        this.infoEffect[3] = "NOTES: You can use all effects.\n";
        this.rechargeCube = new ColorCube[2];
        this.rechargeCube[0] = ColorCube.BLUE;
        this.rechargeCube[1] = ColorCube.BLUE;
        this.paySecondEffect = new ColorCube[1];
        this.paySecondEffect[0] = ColorCube.RED;
        setWeaponCardDescription();
    }

    /**
     * Deal 2 damage and 1 mark to 1 target you can see.
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */


    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if (visiblePlayers.contains(defenders.get(0))){
            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 2, 1);
                controlPlayersDamages(gameBoard, defenders.get(0));
            } catch ( DamageTrackException e) {
                e.getMessage();
            }
            this.firstIsValid = true;

        } else {
            throw new ErrorEffectException();
        }
    }

    /**
     * Deal 1 mark to a different target you can see. You have to pay a RED cube.
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */


    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        /* PAGARE UN ROSSO */

        if (this.firstIsValid){

            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

            if (visiblePlayers.contains(defenders.get(1))){
                try {
                    defenders.get(1).sufferDamageOrMark(attacker.getColor(), 0, 1);
                } catch ( DamageTrackException e) {
                    e.getMessage();
                }
            } else {

                throw new ErrorEffectException();

            }
        }
        else {
            throw new ErrorEffectException();
        }
    }

    /**
     * This effect doesn't exist
     * @throws NoEffectException there isn't this effect
     */


    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException {

        //lancia eccezione perché non esiste questo effetto

        throw new NoEffectException();

    }


}
