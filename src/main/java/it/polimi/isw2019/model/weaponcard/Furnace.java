package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Furnace extends AbstractWeaponCard {

    public Furnace() {
        super(9, "Furnace", ColorCube.RED,1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC EFFECT: Choose a room you can see, but not the room\n" +
                "you are in. Deal 1 damage to everyone in that room.\n");
        this.infoEffect.add("IN COZY FIRE MODE: Choose a square exactly one move\n" +
                "away. Deal 1 damage and 1 mark to everyone on that\n" +
                "square.\n");
        this.rechargeCube[0] = 1;
        this.rechargeCube[1] = 0;
        this.rechargeCube[2] = 1;
    }

    private void damageFurnace(@NotNull GameBoard gameBoard, Player attacker, int x1, int y1){

        ArrayList<Player> playerList = gameBoard.playersInOneSquare(x1, y1, null);

        for (Player aPlayerList : playerList){

            try {

                aPlayerList.sufferDamageOrMark(attacker.getColor(), 1, 0);
            } catch ( DamageTrackException e) {
                e.getMessage();
            }

        }

    }

    private void damageAndMarkFurnace(GameBoard gameBoard, Player attacker, int x1, int y1){

        ArrayList<Player> playerList = gameBoard.playersInOneSquare(x1,y1, null);

        for (Player aPlayerList : playerList){

            try {

                aPlayerList.sufferDamageOrMark(attacker.getColor(), 1, 0);
            } catch ( DamageTrackException e) {
                e.getMessage();
            }

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

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        ArrayList<Player> playerList;

        if ((coordinates[0] != -1) && (coordinates[1] != -1) && (gameBoard.isSquareAvailableOnArena(attacker, coordinates[0], coordinates[1]))) { //controllo se la stanza è visibile dall'attaccante
            playerList = gameBoard.playersInOneSquare(coordinates[0], coordinates[1], null);


            if (playerList != null) {

                damageFurnace(gameBoard, attacker, coordinates[0], coordinates[1]);

            } else { // se la stanza è vuota allora errore

                throw new ErrorEffectException();

            }
        }
        else {
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

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        ArrayList<Player> playerList;

        if(gameBoard.isSquareAvailableOnArena(attacker, coordinates[0], coordinates[1])){ //se è una stanza visibile

            if((oneDistanceX(attacker.getX(), attacker.getY(), coordinates[0], coordinates[1])) || (oneDistanceY(attacker.getX(), attacker.getY(), coordinates[0], coordinates[1]))) { //se dista esattamente 1
                playerList = gameBoard.playersInOneSquare(coordinates[0], coordinates[1], null);

                if(playerList != null){ // se c'è qualche giocatore dentro la stanza
                    damageAndMarkFurnace(gameBoard, attacker, coordinates[0], coordinates[2]); // fai il danno
                }
                else{
                    throw new ErrorEffectException();
                }
            }
            else{
                throw new ErrorEffectException();
            }

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

        /* NON ESISTE L'EFFETTO */

        throw new NoEffectException();
    }


}
