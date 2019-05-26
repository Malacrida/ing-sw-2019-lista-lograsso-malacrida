package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class Whisper extends AbstractWeaponCard {

    public Whisper() {
        super(5, "Whisper", ColorCube.BLUE, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("EFFECT: Deal 3 damage and 1 mark to 1 target you can see.\n" +
                "Your target must be at least 2 moves away from you.\n");
        this.infoEffect.add("NOTES: Notes: For example, in the 2-by-2 room, you cannot shoot\n" +
                "a target on an adjacent square, but you can shoot a target\n" +
                "on the diagonal. If you are beside a door, you can't shoot\n" +
                "a target on the other side of the door, but you can shoot\n" +
                "a target on a different square of that room.\n");
        this.rechargeCube[0] = 0;
        this.rechargeCube[1] = 1;
        this.rechargeCube[2] = 2;
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

        if((moreThanTwoDistance(attacker.getX(), attacker.getY(), defenders.get(0).getX(), defenders.get(0).getY())) && (visiblePlayers.contains(defenders.get(0)))){
            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 3, 1);
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
     *
     * @æuthor Davide Lista
     */


    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException {
        throw new NoEffectException();

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