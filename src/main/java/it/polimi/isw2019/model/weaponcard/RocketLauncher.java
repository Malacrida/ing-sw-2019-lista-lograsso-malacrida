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
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if ((defenders.get(0) != null) && (visiblePlayers.contains(defenders.get(0))) && (!sameSquare(attacker.getX(), attacker.getY(), defenders.get(0).getX(), defenders.get(0).getY()))) {

            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 2, 0);
            } catch (DamageTrackException e) {
                e.printStackTrace();
            }

            if ((coordinates[0] != -1) && (coordinates[1] != -1) && (gameBoard.isSquareAvailableOnArena(defenders.get(0), coordinates[0], coordinates[1]))){ //tenere conto che le prime coordinate sono per il movimento del defenders.get(0) quando viene invocato questa carta
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
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        if ((coordinates[2] != -1) && (coordinates[3] != -1) && (gameBoard.isSquareAvailableOnArena(defenders.get(0), coordinates[2], coordinates[3]))){
//
            gameBoard.changePositionPlayer(attacker, coordinates[2], coordinates[3]);

            if ((coordinates[4] != -1) && (coordinates[5] != -1) && (gameBoard.isSquareAvailableOnArena(defenders.get(0), coordinates[4], coordinates[5]))){

                gameBoard.changePositionPlayer(attacker, coordinates[4], coordinates[5]);

            }

            else {
                throw new ErrorEffectException();
            }
        }

        else
            throw new ErrorEffectException();
        }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if (firstIsValid) {
            throw new ErrorEffectException();
        }

        if ((defenders.get(0) != null) && (visiblePlayers.contains(defenders.get(0))) && (!sameSquare(attacker.getX(), attacker.getY(), defenders.get(0).getX(), defenders.get(0).getY()))) {

            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 2, 0);
            } catch (DamageTrackException e) {
                e.getMessage();
            }

            ArrayList<Player> playerList = gameBoard.playersInOneSquare(defenders.get(0).getX(), defenders.get(0).getY(), defenders.get(0));

            for (Player aPlayerList : playerList) {

                try {
                    aPlayerList.sufferDamageOrMark(attacker.getColor(), 1, 0);
                } catch (DamageTrackException  e) {
                    e.getMessage();
                }

            }

        } else {
            throw new ErrorEffectException();
        }
    }
}
