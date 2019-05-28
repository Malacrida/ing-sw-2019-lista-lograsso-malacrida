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
        this.infoEffect[0] = "FIRST EFFECT : Deal 1 damage to 1 target you can see. Then you may move the target 1 square.\n";
        this.infoEffect[1] = "SECOND EFFECT: Deal 1 damage to every player on a square you can see. You can use this before or after the basic effect's move.\n";
        this.infoEffect[2] = "THIRD EFFECT : This effect doesn't exist;\n";
        this.infoEffect[3] = "NOTE : For example, you can shoot a target, move it onto a square with other targets, then damage everyone including the first target. Or you can deal 2 to a main target, 1 to everyone else on that square, then move the main target. Or you can deal 1 to an isolated target and 1 to everyone on a different square. If you target your own square, you will not be moved or damaged.\n";
        this.rechargeCube = new ColorCube[1];
        this.rechargeCube[0] = ColorCube.RED;
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

        ArrayList<Player> visiblePlayers;

        if (!defenders.isEmpty()) { //se ha inserito almeno un giocatore da attaccare

            visiblePlayers = gameBoard.playersWhoCanSee(attacker);

            if (visiblePlayers.contains(defenders.get(0))) { //vedo se è presente in quelli visibili
                try {
                    defenders.get(0).sufferDamageOrMark(attacker.getColor(), 1, 0); //fai un danno
                } catch ( DamageTrackException e) {
                    e.getMessage();
                }
                if ((coordinates[0] != -1) && (coordinates[1] != -1) && (gameBoard.isSquareAvailableOnArena(defenders.get(0), coordinates[0], coordinates[1]))) { //se ha inserito delle coordinate valide e la cella è adiacente a quella dove il difensore si trova
//
                    gameBoard.changePositionPlayer(defenders.get(0), coordinates[0], coordinates[1]); //allora lo sposti

                }

            } else {
                throw new ErrorEffectException();
            }
        } else {
            throw new ErrorEffectException();
        }
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

    //per fare questo effetto, devono essere riempite le coordinate [3] e [4]

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException {

        /*PAGO UN ROSSO*/

        if(coordinates[2] != -1 && coordinates[3] != -1){ //SE HA INSERITO ALTRE 2 COORDINATE VALIDE
            ArrayList<Player> squarePlayers = gameBoard.playersInOneSquare(coordinates[2], coordinates[3], null); //tutti i giocatori all'interno di quella quadrato
            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker); //tutti i giocatori visibili

            if (visiblePlayers.contains(squarePlayers.get(0))){ //se almeno uno è visibile

                oneDamageAllPlayersInOneSquare(attacker, squarePlayers); //dai un danno a tutti i giocatori presenti nel quadrato

            }else{
                throw new ErrorEffectException();
            }

        }else{
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
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException {

        /* NON ESISTE QUESTO EFFETTO*/
        throw new NoEffectException();
    }

}
