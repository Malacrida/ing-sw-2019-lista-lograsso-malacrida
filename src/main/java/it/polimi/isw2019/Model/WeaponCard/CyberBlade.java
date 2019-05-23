package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorCube;
import it.polimi.isw2019.Model.Exception.DamageTrackException;
import it.polimi.isw2019.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Model.GameBoard;
import it.polimi.isw2019.Model.Player;

import java.util.ArrayList;

public class CyberBlade extends AbstractWeaponCard {

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
     * @param firstDefender
     * @param secondDefender
     * @param thirdDefender
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @throws ErrorEffectException
     * @throws DamageTrackException
     */

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException, DamageTrackException {

        if (firstDefender != null) {
            twoDamageInSameSquare(attacker, firstDefender);
        }
        else {
            throw new ErrorEffectException();
        }
    }

    /**
     *
     * @param gameBoard
     * @param attacker
     * @param firstDefender
     * @param secondDefender
     * @param thirdDefender
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     * @throws ErrorEffectException
     */

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2, int x3, int y3) throws ErrorEffectException, DamageTrackException {

        if(gameBoard.isSquareAvailableOnArena(attacker, x1, y1)){
//
            System.out.println("IN ATTESA DI CHANGEPOSITIONE");

        }
        else {
            throw new ErrorEffectException();
        }
    }


    /**
     *
     * @param gameBoard
     * @param attacker
     * @param firstDefender
     * @param secondDefender
     * @param thirdDefender
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @throws ErrorEffectException
     */
    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException, DamageTrackException {

        if (secondDefender != null){
            twoDamageInSameSquare(attacker, secondDefender);
        }
    }


}
