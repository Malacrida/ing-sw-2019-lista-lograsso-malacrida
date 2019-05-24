package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class GrenadeLauncher extends AbstractWeaponCard {

    public GrenadeLauncher() {
        super(13, "Grenade Launcher", ColorCube.RED, 2);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Deal 1 damage to 1 target you can see. Then you may move\n" +
                "the target 1 square.\n");
        this.infoEffect.add("WITH EXTRA GRENADE: Deal 1 damage to every player on a square you can\n" +
                "see. You can use this before or after the basic effect's move.\n");
        this.infoEffect.add("NOTES: For example, you can shoot a target, move it onto a square with\n" +
                "other targets, then damage everyone including the first target.\n" +
                "Or you can deal 2 to a main target, 1 to everyone else on that square,\n" +
                "then move the main target. Or you can deal 1 to an isolated target and\n" +
                "1 to everyone on a different square. If you target your own square,\n" +
                "you will not be moved or damaged.\n");
        this.rechargeCube[0] = 1;
        this.rechargeCube[1] = 0;
        this.rechargeCube[2] = 0;
    }

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        ArrayList<Player> visiblePlayers;

        if (defenders.get(0) != null) {

            visiblePlayers = gameBoard.playersWhoCanSee(attacker);

            if (visiblePlayers.contains(defenders.get(0))) {
                try {
                    defenders.get(0).sufferDamageOrMark(attacker.getColor(), 1, 0);
                } catch ( DamageTrackException e) {
                    e.getMessage();
                }
                if ((coordinates[0] != -1) && (coordinates[1] != -1) && (gameBoard.isSquareAvailableOnArena(defenders.get(0), coordinates[0], coordinates[1]))) {
//
                    gameBoard.changePositionPlayer(defenders.get(0), coordinates[2], coordinates[3]);

                }

            } else {
                throw new ErrorEffectException();
            }
        } else {
            throw new ErrorEffectException();
        }
    }

    //per fare questo effetto, devono essere riempite le coordinate [3] e [4]

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException {

        if(coordinates[2] != -1 && coordinates[3] != -1){
            ArrayList<Player> squarePlayers = gameBoard.playersInOneSquare(coordinates[2], coordinates[3], null);
            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

            if (visiblePlayers.contains(squarePlayers.get(0))){

                oneDamageAllPlayersInOneSquare(attacker, squarePlayers);

            }else{
                throw new ErrorEffectException();
            }

        }else{
            throw new ErrorEffectException();
        }





    }

    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException {

        /* NON ESISTE QUESTO EFFETTO*/
        throw new NoEffectException();
    }

}
