package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class ShotGun extends AbstractWeaponCard {

    public ShotGun() {
        super(18, "Shot Gun", ColorCube.YELLOW, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC MODE : Deal 3 damage to 1 target on" +
                "your square. If you want, you may then move" +
                "the target 1 square.");
        this.infoEffect.add("IN LONG BARREL MODE:Deal 2 damage to" +
                "1 target on any square exactly one move" +
                "away.");
        this.rechargeCube[0] = 0;
        this.rechargeCube[1] = 2;
        this.rechargeCube[2] = 0;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {


        threeDamageInSameSquare(attacker, defenders.get(0));

        if ((coordinates[0] != -1) && (coordinates[1] != -1) && (gameBoard.isSquareAvailableOnArena(defenders.get(0), coordinates[0], coordinates[1]))){
//
            gameBoard.changePositionPlayer(defenders.get(0), coordinates[0], coordinates[1]);

        } else {
            throw new ErrorEffectException();
        }

    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        if (gameBoard.isSquareAvailableOnArena(attacker, defenders.get(0).getX(), defenders.get(0).getY())){
            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 2, 0);
            } catch (DamageTrackException e) {
                e.getMessage();
            }
        } else {
            throw new ErrorEffectException();
        }

    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException {
        throw new NoEffectException();
    }

}
