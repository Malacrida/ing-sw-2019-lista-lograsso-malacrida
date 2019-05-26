package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class PlasmaGun extends AbstractWeaponCard{

    public PlasmaGun() {
        super(4, "Plasma Gun", ColorCube.BLUE, 3);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Deal 2 damage to 1 target you can see.\n");
        this.infoEffect.add("WITH WITH PHASE GLIDE: Move 1 or 2 squares. This effect can be used either before or after the basic effect.\n");
        this.infoEffect.add("WITH CHARGED SHOT: Deal 1 additional damage to 1 additional damage to your target. You have to pay a BLUE cube.\n");
        this.infoEffect.add("NOTES: The two moves have no ammo cost. You don't have" +
                " to be able to see your target when you play the card." +
                "For example, you can move 2 squares and shoot a target" +
                "you now see. You cannot use 1 move before shooting and " +
                "1 move after.");
        this.rechargeCube[0] = 0;
        this.rechargeCube[1] = 1;
        this.rechargeCube[2] = 1;
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

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        twoDamageAndSetFirstIsValid(attacker, defenders.get(0), visiblePlayers);
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

        if ((coordinates[0] != -1) && (coordinates[1] != -1) && (gameBoard.isSquareAvailableOnArena(attacker, coordinates[0], coordinates[1]))){
            gameBoard.changePositionPlayer(attacker, coordinates[0], coordinates[1]);

        } else {
            throw new ErrorEffectException();
        }

        if ((coordinates[2] != -1) && (coordinates[3] != -1) && (gameBoard.isSquareAvailableOnArena(attacker, coordinates[2], coordinates[3]))){
            gameBoard.changePositionPlayer(attacker, coordinates[2], coordinates[3]);

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
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException {

        if (!oneDamageIfFirstIsValid(attacker, defenders.get(0), firstIsValid)){ //se quella funzione torna falso allora lancia l'eccezione
            throw new ErrorEffectException();
        }
    }
}
