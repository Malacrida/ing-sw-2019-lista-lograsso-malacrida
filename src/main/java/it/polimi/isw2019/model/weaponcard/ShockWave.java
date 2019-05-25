package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class ShockWave extends AbstractWeaponCard {

    public ShockWave() {
        super(20, "Shock Wave", ColorCube.YELLOW, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT : choose up to 3 targets on different squares, each exactly 1 move away deal 1 damage to each target.");
        this.infoEffect.add("TSUNAMI MODE : Deal 1 damage to all targets that are exactly 1 move away\n");
        this.rechargeCube[0] = 0;
        this.rechargeCube[1] = 1;
        this.rechargeCube[2] = 0;
    }

    /**
     *
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     *
     * @æuthor Davide Lista
     */
    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {
        if ((!threePlayersSameSquare(defenders.get(0), defenders.get(1), defenders.get(2))) && threeSquaresAvailable(gameBoard, attacker, defenders.get(0), defenders.get(1), defenders.get(2))){
            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 1, 0);
                defenders.get(1).sufferDamageOrMark(attacker.getColor(), 1, 0);
                defenders.get(2).sufferDamageOrMark(attacker.getColor(), 1, 0);
            } catch (DamageTrackException e) {
                e.getMessage();
            }
        } else {

            throw new ErrorEffectException();

        }
    }

    /**
     *
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     *
     * @æuthor Davide Lista
     */
    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {
        ArrayList<Player> firstSquare= gameBoard.playersInOneSquare(coordinates[0], coordinates[1], null);
        ArrayList<Player> secondSquare = gameBoard.playersInOneSquare(coordinates[2], coordinates[3], null);
        ArrayList<Player> thirdSquare = gameBoard.playersInOneSquare(coordinates[4], coordinates[5], null);

        if (!firstSquare.isEmpty() && !secondSquare.isEmpty() && !thirdSquare.isEmpty()) {

            oneDamageAllPlayersInOneSquare(attacker, firstSquare);
            oneDamageAllPlayersInOneSquare(attacker, secondSquare);
            oneDamageAllPlayersInOneSquare(attacker, thirdSquare);

        } else {
            throw new ErrorEffectException();
        }
    }


    /**
     * This effect doesn't exist
     * @throws NoEffectException there isn't this effect
     *
     * @æuthor Davide Lista
     */


    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException {

        throw new NoEffectException();

    }


}
