package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;

import java.util.ArrayList;

public class CyberBlade extends AbstractWeaponCard{

    public CyberBlade(){
        super(16, "Cyber Blade", ColorCube.YELLOW, 3, 2, 2);

        this.infoEffect[0] = "FIRST EFFECT : Deal 2 damage to 1 target on your square.\n";
        this.infoEffect[1] = "SECOND EFFECT: move 1 square before or after the basic effect\n";
        this.infoEffect[2] = "THIRD EFFECT : to a different target on your square the shadowstep may be used before or after this effect.";
        this.infoEffect[3] = "NOTE : Combining all effects allows you to move onto a square and whack 2 people; or whack somebody, move, and whack somebody else;\n" +
                "or whack 2 people and then move.";
        this.rechargeCube = new ColorCube[2];
        this.rechargeCube[0] = ColorCube.YELLOW;
        this.rechargeCube[1] = ColorCube.RED;
    }

    /**
     * Deal 2 damage to 1 target on attacker's square
     * @param gameBoard
     * @param attacker
     * @param defenders
     * @param coordinates
     * @throws ErrorEffectException
     * @throws DamageTrackException
     */

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException { //OKAY

        if (!defenders.isEmpty()) {
            damagesInSameSquare(attacker, defenders.get(0),2);
        }
        else {
            throw new ErrorEffectException();
        }
    }

    /**
     * Move 1 square
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     *
     * @æuthor Davide Lista
     */

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException { //OKAY

        if(gameBoard.isSquareAvailableOnArena(attacker, coordinates[0], coordinates[1])){

            gameBoard.changePositionPlayer(attacker, coordinates[0], coordinates[1]);

        }
        else {
            throw new ErrorEffectException();
        }
    }

    /**
     * To a different target on attacker's square the shadowstep may be used before or after this effect
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     *
     * @æuthor Davide Lista
     */
    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException {

        //AGGIUNGERE PAGARE UN GIALLO

        if (defenders.get(1) != null){
            damagesInSameSquare(attacker, defenders.get(1), 2);
        }
    }


}
