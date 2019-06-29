package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class ShotGun extends AbstractWeaponCard {

    public ShotGun() {
        super(18, "Shot Gun", ColorCube.YELLOW, 1, 2, 1);
        this.infoEffect[0] = "FIRST EFFECT: Deal 3 damage to 1 target on your square. If you want, you may then move the target 1 square.\n";
        this.infoEffect[1] = "SECOND EFFECT: Deal 2 damage to 1 target on any square exactly one move away.\n";
        this.infoEffect[2] = "THIRD EFFECT: This effect doesn't exist.\n";
        this.infoEffect[3] = "NOTES: You can use only one effect.\n";
        this.rechargeCube = new ColorCube[2];
        this.rechargeCube[0] = ColorCube.YELLOW;
        this.rechargeCube[1] = ColorCube.YELLOW;

    }

    /**
     * Deal 3 damage to 1 target on your square. If you want, you may then move the target 1 square.
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */
    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        if (!defenders.isEmpty()) {
            damagesInSameSquare(attacker, defenders.get(0), 3);

            if ((coordinates[0] != -1) && (coordinates[1] != -1) && (gameBoard.isSquareAvailableOnArena(defenders.get(0), coordinates[0], coordinates[1]))) {

                gameBoard.changePositionPlayer(defenders.get(0), coordinates[0], coordinates[1]);
            }
        } else {
            throw new ErrorEffectException();
        }
    }


        /**
         * Deal 2 damage to 1 target on any square exactly one move away.
         * @param gameBoard is the Gameboard where players play
         * @param attacker is the player who use Weapon card
         * @param defenders are players attacked
         * @param coordinates some coordinates used to move players or to indicate squares to attack players
         * @throws ErrorEffectException there is a problem during effect
         */

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        if (gameBoard.isSquareAvailableOnArena(attacker, defenders.get(0).getX(), defenders.get(0).getY())){
            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 2, 0);
            } catch (DamageTrackException e) {
                e.getMessage();
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
