package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class CyberBlade extends AbstractWeaponCard{

    public CyberBlade(){
        super(16, "Cyber Blade", ColorCube.YELLOW, 3);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT : Deal 2 damage to 1 target on your square.\n");
        this.infoEffect.add("WITH SHADOWSTEP: move 1 square before or after the basic effect");
        this.infoEffect.add("WITH SLICE AND DICE : to a different target on your square the shadowstep may be used before or after this effect.");
        this.infoEffect.add("NOTE : Combining all effects allows you to move onto a square and\n" +
                "whack 2 people; or whack somebody, move, and whack somebody else;\n" +
                "or whack 2 people and then move.   ");
        this.rechargeCube[0] = 1;
        this.rechargeCube[1] = 1;
        this.rechargeCube[2] = 0;
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
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        if (defenders.get(0) != null) {
            twoDamageInSameSquare(attacker, defenders.get(0));
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
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException {

        if(gameBoard.isSquareAvailableOnArena(attacker, coordinates[0], coordinates[1])){
//
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

        if (defenders.get(1) != null){
            twoDamageInSameSquare(attacker, defenders.get(1));
        }
    }


}
