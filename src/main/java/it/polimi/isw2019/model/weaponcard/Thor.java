package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class Thor extends AbstractWeaponCard{

    public Thor() {
        super(3, "T.H.O.R.", ColorCube.BLUE,3);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Deal 2 damage to 1 target you can see.\n");
        this.infoEffect.add("WITH CHAIN REACTION: Deal 1 damage to a second target that your first target can see. You have to pay a BLUE cube\n");
        this.infoEffect.add("WITH HIGH REACTION: Deal 2 damage to a third target that your second target can see. You have to pay a BLUE cube\n" +
                "You cannot use this effect unless you first use the chain reaction");
        this.infoEffect.add("NOTES: This card constrains the order in which you can use its effects." +
                "(Most cards don't.)" +
                "Also note that each target must be a different player\n");
        this.rechargeCube[0] = 1;
        this.rechargeCube[1] = 0;
        this.rechargeCube[2] = 1;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        twoDamageAndSetFirstIsValid(attacker, defenders.get(0), visiblePlayers);
    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        if (firstIsValid){

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

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException {

        if(secondIsValid) {

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
