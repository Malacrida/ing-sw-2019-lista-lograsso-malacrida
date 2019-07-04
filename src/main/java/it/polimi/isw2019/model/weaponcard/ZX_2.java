package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class ZX_2 extends AbstractWeaponCard {

    public ZX_2() {
        super(17, "ZX_2", ColorCube.YELLOW, 1, 0, 3);
        this.infoEffect[0] = "FIRST EFFECT: Deal 1 damage and 2 marks to 1 target you can see.\n";
        this.infoEffect[1] = "SECOND EFFECT: Choose up to 3 targets you can see and deal 1 mark to each.\n";
        this.infoEffect[2] = "THIRD EFFECT: This effect doesn't exist.\n";
        this.infoEffect[3] = "NOTES: Remember that the 3 targets can be in 3 different rooms.\n";
        this.rechargeCube = new ColorCube[2];
        this.rechargeCube[0] = ColorCube.YELLOW;
        this.rechargeCube[1] = ColorCube.RED;
        setWeaponCardDescription();
    }

    /**
     * Deal 1 damage and 2 marks to 1 target you can see.
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if((!defenders.isEmpty()) && (visiblePlayers.contains(defenders.get(0)))){
            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 1, 2);
                controlPlayersDamages(gameBoard, defenders.get(1));
            } catch (DamageTrackException e) {
                e.getMessage();
            }

        } else {

            throw new ErrorEffectException();

        }


    }

    /**
     * Choose up to 3 targets you can see and deal 1 mark to each.
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if((!defenders.isEmpty()) && (defenders.size() < 3)){

            for (Player defender : defenders) {
                if (visiblePlayers.contains(defender)) {
                    try {
                        defender.sufferDamageOrMark(attacker.getColor(), 0, 1);
                    } catch (DamageTrackException e) {
                        e.getMessage();
                    }
                }
            }

        } else {
            throw new ErrorEffectException();
        }



    }

    /**
     * This effect doesn't exist
     * @throws NoEffectException there isn't this effect
     */


    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException {
        throw new NoEffectException();

    }
}
