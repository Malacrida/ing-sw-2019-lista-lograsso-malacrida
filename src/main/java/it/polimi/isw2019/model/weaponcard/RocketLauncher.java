package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class RocketLauncher extends  AbstractWeaponCard {

    public RocketLauncher() {
        super(14, "Rocket Launcher", ColorCube.RED, 2, 6, 1);
        this.infoEffect[0] = "FIRST EFFECT: Deal 2 damage to 1 target you can see that is not on your square. Then you may move the target 1 square.";
        this.infoEffect[1] = "SECOND EFFECT: Move 1 or 2 squares. This effect can be used either before or after the basic effect.\n";
        this.infoEffect[2] = "THIRD EFFECT: During the basic effect, deal 1 damage to every player on your target's original square – including the target, even if you move it.\n";
        this.infoEffect[3] = "NOTES: If you use the rocket jump before the basic effect, you consider only your new square when determining if a target is legal. You can even move off a square so you can shoot someone on it. If you use the fragmenting warhead, you deal damage to everyone on the target's square before you move the target – your target will take 3 damage total.\n";
        this.rechargeCube = new ColorCube[2];
        this.rechargeCube[0] = ColorCube.RED;
        this.rechargeCube[1] = ColorCube.RED;
        this.paySecondEffect = new ColorCube[1];
        this.paySecondEffect[0] = ColorCube.BLUE;
        this.payThirdEffect = new ColorCube[1];
        this.payThirdEffect[0] = ColorCube.YELLOW;
    }


    /* PER UNA QUESTIONE DI EFFETTO O SCEGLI IL PRIMO O IL TERZO PERCHÉ UNO IMPLICA L'ALTRO QUINDI NEL TERZO È CONTENUTO IL PRIMO*/


    /**
     * Deal 2 damage to 1 target you can see that is not on your square. Then you may move the target 1 square.
     * @param gameBoard   is the Gameboard where players play
     * @param attacker    is the player who use Weapon card
     * @param defenders   are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */
    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        if ((!defenders.isEmpty()) && (visiblePlayers.contains(defenders.get(0))) && (!sameSquare(attacker.getX(), attacker.getY(), defenders.get(0).getX(), defenders.get(0).getY()))) {

            try {
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 2, 0);
            } catch (DamageTrackException e) {
                e.getMessage();
            }

            if ((coordinates[0] != -1) && (coordinates[1] != -1) && (gameBoard.isSquareAvailableOnArena(defenders.get(0), coordinates[0], coordinates[1]))) { //tenere conto che le prime coordinate sono per il movimento del defenders.get(0) quando viene invocato questa carta
//
                gameBoard.changePositionPlayer(defenders.get(0), coordinates[0], coordinates[1]);

            }

        } else {
            throw new ErrorEffectException();
        }

    }


    /**
     * Move 1 or 2 squares. This effect can be used either before or after the basic effect.
     * @param gameBoard   is the Gameboard where players play
     * @param attacker    is the player who use Weapon card
     * @param defenders   are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */
    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException {

        /* PAGARE UN BLU */

        if ((coordinates[2] != -1) && (coordinates[3] != -1) && (gameBoard.isSquareAvailableOnArena(defenders.get(0), coordinates[2], coordinates[3]))) {

            gameBoard.changePositionPlayer(attacker, coordinates[2], coordinates[3]);

            if ((coordinates[4] != -1) && (coordinates[5] != -1) && (gameBoard.isSquareAvailableOnArena(defenders.get(0), coordinates[4], coordinates[5]))) {

                gameBoard.changePositionPlayer(attacker, coordinates[4], coordinates[5]);

            } else {
                throw new ErrorEffectException();
            }

        } else
            throw new ErrorEffectException();
    }


    /**
     * During the basic effect, deal 1 damage to every player on your target's original square – including the target, even if you move it.
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */
    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException {

        /* PAGO UN GIALLO */

        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

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

            if ((coordinates[0] != -1) && (coordinates[1] != -1) && (gameBoard.isSquareAvailableOnArena(defenders.get(0), coordinates[0], coordinates[1]))) { //tenere conto che le prime coordinate sono per il movimento del defenders.get(0) quando viene invocato questa carta

                gameBoard.changePositionPlayer(defenders.get(0), coordinates[0], coordinates[1]);

            }

        } else {
            throw new ErrorEffectException();
        }
    }
}
