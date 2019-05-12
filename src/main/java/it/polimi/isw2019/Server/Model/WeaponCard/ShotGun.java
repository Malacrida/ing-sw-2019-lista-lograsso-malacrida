package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.KillShotException;
import it.polimi.isw2019.Server.Model.Exception.NoEffectException;
import it.polimi.isw2019.Server.Model.Exception.OverKillException;
import it.polimi.isw2019.Server.Model.GameBoard;
import it.polimi.isw2019.Server.Model.Player;

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
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {


        if (sameSquare(attacker, firstDefender)){

            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 3, 0);
            } catch (KillShotException | OverKillException e) {
                e.printStackTrace();
            }


        } else {

            throw new ErrorEffectException();

        }

        if (gameBoard.changePositionPlayer(firstDefender, x1, y1, false)){

            System.out.println("In attesa di changePosition");

        } else {
            throw new ErrorEffectException();
        }

    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        if (oneDistance(attacker, firstDefender)){

            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 2, 0);
            } catch (KillShotException | OverKillException e) {
                e.printStackTrace();
            }

        } else {

            throw new ErrorEffectException();

        }
    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws NoEffectException {

        throw new NoEffectException();

    }

}
