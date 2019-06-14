package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class TractorBeam extends AbstractWeaponCard {

    public TractorBeam() {
        super(7, "Tractor Beam", ColorCube.BLUE, 1, 4, 1);
        this.infoEffect[0] = "FIRST EFFECT: Move a target 0,1 or 2 squares to a square you can see, and give it 1 damage.\n";
        this.infoEffect[1] = "SECOND EFFECT: Choose a target 0,1 or 2 moves away from you. Move the target to your square and deal 3 damage to it.\n";
        this.infoEffect[2] = "THIRD EFFECT: This effect doesn't exist";
        this.infoEffect[3] = "NOTES: You can move a target even if you can't see it. The target ends up in a place where you can see and damage it. The moves do not have to be in the same direction.\n";
        this.rechargeCube = new ColorCube[1];
        this.rechargeCube[0] = ColorCube.BLUE;
    }

    /**
     *
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {
        if(defenders.get(0) != null){
            if (coordinates[0] != -1 && coordinates[1] != -1){
                gameBoard.changePositionPlayer(defenders.get(0), coordinates[0], coordinates[1]);
                if (coordinates[2] != -1 && coordinates[3] != -1){
                    gameBoard.changePositionPlayer(defenders.get(0), coordinates[2], coordinates[3]);
                }
            }

            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 1, 0);
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
     */

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        /*PAGA GIALLO ROSSO*/

        if(defenders.get(0) != null) {
            if (coordinates[0] != -1 && coordinates[1] != -1) {
                gameBoard.changePositionPlayer(defenders.get(0), coordinates[0], coordinates[1]);
                if (coordinates[2] != -1 && coordinates[3] != -1) {
                    gameBoard.changePositionPlayer(defenders.get(0), coordinates[2], coordinates[3]);
                }
            }

            if (sameSquare(attacker.getX(), attacker.getY(), defenders.get(1).getX(), defenders.get(1).getY())) {
                try {
                    defenders.get(1).sufferDamageOrMark(attacker.getColor(), 3, 0);
                } catch (DamageTrackException e) {
                    e.getMessage();
                }
            } else {
                throw new ErrorEffectException();
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
