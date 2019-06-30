package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class SledgeHammer extends AbstractWeaponCard {

    public SledgeHammer() {
        super(21, "SledgeHammer", ColorCube.YELLOW, 1, 4, 1);
        this.infoEffect[0] = "FIRST EFFECT: Deal 2 damage to 1 target on your square.\n";
        this.infoEffect[1] = "SECOND EFFECT: Deal 3 damage to 1 target on your square, then move that target 0, 1, or 2 squares in one direction.\n";
        this.infoEffect[2] = "THIRD EFFECT: This effect doesn't exist.\n";
        this.infoEffect[3] = "NOTES: Remember that moves go through doors, but not walls.\n";
        this.rechargeCube = new ColorCube[1];
        this.rechargeCube[0] = ColorCube.YELLOW;
        setWeaponCardDescription();
        this.paySecondEffect = new ColorCube[1];
        this.paySecondEffect[0] = ColorCube.RED;
    }

    /**
     * Deal 2 damage to 1 target on your square.
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {
        if(!defenders.isEmpty()){
            damagesInSameSquare(attacker, defenders.get(0),2);
        } else {
            throw new ErrorEffectException();
        }
    }

    /**
     * Deal 3 damage to 1 target on your square, then move that target 0, 1, or 2 squares in one direction.
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException, ErrorEffectException, DamageTrackException {
        /*PAGA UN ROSSO*/
        if(!defenders.isEmpty()){
            damagesInSameSquare(attacker, defenders.get(0),3);
            if ((coordinates[0] != -1) && (coordinates[1] != -1) && (gameBoard.isSquareAvailableOnArena(defenders.get(0), coordinates[0], coordinates[1]))){

                gameBoard.changePositionPlayer(defenders.get(0), coordinates[0], coordinates[1]);
            }
            if ((coordinates[2] != -1) && (coordinates[3] != -1) && (gameBoard.isSquareAvailableOnArena(defenders.get(0), coordinates[0], coordinates[1]))){

                gameBoard.changePositionPlayer(defenders.get(0), coordinates[2], coordinates[3]);
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