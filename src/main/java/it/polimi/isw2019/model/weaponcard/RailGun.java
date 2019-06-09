package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class RailGun extends AbstractWeaponCard {

    public RailGun() {
        super(15, "Rail Gun", ColorCube.YELLOW, 1, 4,2 );
        this.infoEffect[0] = "FIRST EFFECT: Choose a cardinal direction and 1 target in that direction deal 3 damage to it";
        this.infoEffect[1] = "SECOND EFFECT: Choose a cardinal direction and 1 or 2 targets in that direction deal 2 damage to each";
        this.infoEffect[2] = "THIRD EFFECT: This effect doesn't exist";
        this.infoEffect[3] = "NOTES: Basically, you're shooting in a straight line and ignoring walls.\n You don't have to pick a target on the other side of a wall – it could even\n" +
                " be someone on your own square – but shooting through walls sure is\n" +
                " fun. There are only 4 cardinal directions. You imagine facing one wall or\n" +
                " door, square-on, and firing in that direction. Anyone on a square in that\n" +
                " direction (including yours) is a valid target. In piercing mode,\n" +
                " the 2 targets can be on the same square or on different squares.";
        this.rechargeCube = new ColorCube[3];
        this.rechargeCube[0] = ColorCube.YELLOW;
        this.rechargeCube[1] = ColorCube.YELLOW;
        this.rechargeCube[2] = ColorCube.BLUE;
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
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException { //DA VEDERE CON SARA

        char direction = direction(attacker, defenders.get(0));

        if(direction != 'n'){

            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 3, 0);
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

        char firstDirection = direction(attacker, defenders.get(0));
        char secondDirection = direction(attacker, defenders.get(0));

        if ((firstDirection != 'n') && (firstDirection == secondDirection)){

            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 2, 0);
            } catch (DamageTrackException  e) {
                e.getMessage();
            }
            try {
                defenders.get(1).sufferDamageOrMark(attacker.getColor(), 2, 0);
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
