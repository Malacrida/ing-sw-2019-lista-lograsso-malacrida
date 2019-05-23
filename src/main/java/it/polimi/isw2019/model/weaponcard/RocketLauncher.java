package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class RocketLauncher extends  AbstractWeaponCard {

    public RocketLauncher() {
        super(14, "Rocket Launcher", ColorCube.RED, 2);
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


    /* PER UNA QUESTIONE DI EFFETTO O SCEGLI IL PRIMO O IL TERZO PERCHÉ UNO IMPLICA L'ALTRO QUINDI NEL TERZO È CONTENUTO IL PRIMO*/
    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if ((firstDefender != null) && (visiblePlayers.contains(firstDefender)) && (!sameSquare(attacker.getX(), attacker.getY(), firstDefender.getX(), firstDefender.getY()))) {

            try {
                firstDefender.sufferDamageOrMark(attacker.getColor(), 2, 0);
            } catch (DamageTrackException e) {
                e.printStackTrace();
            }

            if ((x1 != -1) && (y1 != -1) && (gameBoard.isSquareAvailableOnArena(firstDefender, x1, y1))){ //tenere conto che le prime coordinate sono per il movimento del FIRSTDEFENDER quando viene invocato questa carta
//
                System.out.println("In attesa di changePosition");

            } else {

                throw new ErrorEffectException();

            }

            firstIsValid = true;

        } else {

            throw new ErrorEffectException();

        }

    }

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2, int x3, int y3) throws ErrorEffectException, DamageTrackException {

        if ((x2 != -1) && (y2 != -1) && (gameBoard.isSquareAvailableOnArena(firstDefender, x2, y2))){
//
            System.out.print("In attesa di changePosition");

            if ((x3 != -1) && (y3 != -1) && (gameBoard.isSquareAvailableOnArena(firstDefender, x3, y3))){
                System.out.print("In attesa di changePosition");
            }
            else {
                throw new ErrorEffectException();
            }

        } else {

            throw new ErrorEffectException();

        }

    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, Player firstDefender, Player secondDefender, Player thirdDefender, int x1, int y1, int x2, int y2) throws ErrorEffectException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if (!firstIsValid){
            if ((firstDefender != null) && (visiblePlayers.contains(firstDefender)) && (!sameSquare(attacker.getX(), attacker.getY(), firstDefender.getX(), firstDefender.getY()))) {

                try {
                    firstDefender.sufferDamageOrMark(attacker.getColor(), 2, 0);
                } catch (DamageTrackException e) {
                    e.printStackTrace();
                }

                ArrayList<Player> playerList = gameBoard.playersInOneSquare(firstDefender.getX(), firstDefender.getY(), firstDefender);

                for (Player aPlayerList : playerList) {

                    try {
                        aPlayerList.sufferDamageOrMark(attacker.getColor(), 1, 0);
                    } catch (DamageTrackException  e) {
                        e.printStackTrace();
                    }

                }
            } else {

                throw new ErrorEffectException();

            }
        }

        else{
            throw new ErrorEffectException();
        }

    }
}
