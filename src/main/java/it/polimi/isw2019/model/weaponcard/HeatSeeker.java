package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.NoEffectException;

import java.util.ArrayList;

public class HeatSeeker extends AbstractWeaponCard {

    public HeatSeeker() {
        super(10, "HeatSeeker", ColorCube.RED, 1, 0, 1);
        this.infoEffect[0] = "FIRST EFFECT: Choose 1 target you cannot see and deal 3 damage to it.\n";
        this.infoEffect[1] = "SECOND EFFECT: This effect doesn't exist;\n";
        this.infoEffect[2] = "THIRD EFFECT: This effect doesn't exist;\n";
        this.infoEffect[3] = "NOTES: Yes, this can only hit targets you cannot see.\n";
        this.rechargeCube = new ColorCube[3];
        this.rechargeCube[0] = ColorCube.RED;
        this.rechargeCube[1] = ColorCube.RED;
        this.rechargeCube[2] = ColorCube.YELLOW;
    }

    /**
     * Choose 1 target you cannot see and deal 3 damage to it.
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if (!defenders.isEmpty() && !visiblePlayers.contains(defenders.get(0))) {

            defenders.get(0).sufferDamageOrMark(attacker.getColor(), 3, 0);

        } else throw new ErrorEffectException();

    }

    /**
     * This effect doesn't exist
     * @throws NoEffectException there isn't this effect
     */

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException {
        // NON ESISTE L'EFFETTO
        throw new NoEffectException();
    }

    /**
     * This effect doesn't exist
     * @throws NoEffectException there isn't this effect
     */


    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException {
        /* NON ESISTE L'EFFETTO */
        throw new NoEffectException();
    }

}
