package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorCube;
import it.polimi.isw2019.Server.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Server.Model.Exception.KillShotException;
import it.polimi.isw2019.Server.Model.Exception.OverKillException;
import it.polimi.isw2019.Server.Model.GameBoard;
import it.polimi.isw2019.Server.Model.Player;

import java.util.ArrayList;

public class RocketLauncher extends  AbstractWeaponCard {

    public RocketLauncher() {
        super(14, "Rocket Launcher", ColorCube.RED, 3);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT : basic effect: Deal 2 damage to 1 target you can see that is not on your" +
                "square. Then you may move the target 1 square.");
        this.infoEffect.add("WITH ROCKET JUMP:Move 1 or 2 squares. This effect can be used either" +
                "before or after the basic effect");
        this.infoEffect.add("WITH FREGMETING WARHEAD: During the basic effect, deal 1 damage to" +
                "every player on your target's original square – including the target," +
                "even if you move it.");
        this.infoEffect.add(" NOTE :If you use the rocket jump before the basic effect, you consider" +
                "only your new square when determining if a target is legal. You can" +
                "even move off a square so you can shoot someone on it. If you use the" +
                "fragmenting warhead, you deal damage to everyone on the target's" +
                "square before you move the target – your target will take 3 damage total. ");
        this.rechargeCube[0] = 2;
        this.rechargeCube[1] = 0;
        this.rechargeCube[2] = 0;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        if (firstDefender != null){

            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 2, 0);
            } catch (KillShotException | OverKillException e) {
                e.printStackTrace();
            }

            if (gameBoard.changePositionPlayer(firstDefender, x1, y1, false)){

                System.out.print("In attesa di changePosition");

            } else {

                throw new ErrorEffectException();

            }

            firstIsValid = true;

        } else {

            throw new ErrorEffectException();

        }

    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        if (gameBoard.changePositionPlayer(firstDefender, x1, y1, false)){

            System.out.print("In attesa di changePosition");

        } else {

            throw new ErrorEffectException();

        }

    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        ArrayList<Player> playerList = gameBoard.playersInOneSquare(firstDefender.getX(), firstDefender.getY(), firstDefender);
        if (firstIsValid){

            for (Player aPlayerList : playerList) {

                try {
                    aPlayerList.sufferDamageOrMark(attacker.getColor(), 1, 0);
                } catch (KillShotException | OverKillException e) {
                    e.printStackTrace();
                }

            }
        } else {
            throw new ErrorEffectException();
        }
    }
}
