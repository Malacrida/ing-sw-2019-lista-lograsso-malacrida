package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class Thor extends AbstractWeaponCard{

    public Thor() {
        super(3, "T.H.O.R.", ColorCube.BLUE,3, 0, 3);
        this.infoEffect[0] = "FIRST EFFECT: Deal 2 damage to 1 target you can see.\n";
        this.infoEffect[1] = "SECOND EFFECT: Deal 1 damage to a second target that your first target can see. You have to pay a BLUE cube.\n";
        this.infoEffect[2] = "THIRD EFFECT: Deal 2 damage to a third target that your second target can see. You have to pay a BLUE cube. You cannot use this effect unless you first use the chain reaction\n";
        this.infoEffect[3] = "NOTES: This card constrains the order in which you can use its effects. (Most cards don't.) Also note that each target must be a different player\\n";
        this.rechargeCube = new ColorCube[2];
        this.rechargeCube[0] = ColorCube.BLUE;
        this.rechargeCube[1] = ColorCube.RED;
        this.paySecondEffect = new ColorCube[1];
        this.paySecondEffect[0] = ColorCube.BLUE;
        this.payThirdEffect = new ColorCube[1];
        this.payThirdEffect[0] = ColorCube.BLUE;
    }

    /**
     * Deal 2 damage to 1 target you can see.
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        twoDamageAndSetFirstIsValid(attacker, defenders.get(0), visiblePlayers);
    }

    /**
     * Deal 1 damage to a second target that your first target can see. You have to pay a BLUE cube.
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        /* PAGARE UN BLU */

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(defenders.get(0));

        if (firstIsValid && visiblePlayers.contains(defenders.get(1))){

            try {
                defenders.get(1).sufferDamageOrMark(attacker.getColor(), 1, 0);
            } catch (DamageTrackException e) {
                e.getMessage();
            }
            secondIsValid = true;

        } else {

            throw new ErrorEffectException();

        }
    }

    /**
     * Deal 2 damage to a third target that your second target can see. You have to pay a BLUE cube. You cannot use this effect unless you first use the chain reaction
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException {
        /*PAGA UN BLU */


        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(defenders.get(1));

        if(secondIsValid && visiblePlayers.contains(defenders.get(2))) {

            try {
                defenders.get(2).sufferDamageOrMark(attacker.getColor(), 2, 0);
            } catch (DamageTrackException e) {
                e.getMessage();
            }

        } else {

            throw new ErrorEffectException();

        }
    }

}
