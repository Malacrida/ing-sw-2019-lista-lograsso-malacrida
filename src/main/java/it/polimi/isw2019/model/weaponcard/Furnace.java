package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.NoEffectException;

import java.util.ArrayList;

public class Furnace extends AbstractWeaponCard {

    public Furnace() {
        super(9, "Furnace", ColorCube.RED,1, 2, 0);
        this.infoEffect[0] = "FIRST EFFECT : Choose a room you can see, but not the room you are in. Deal 1 damage to everyone in that room.\n";
        this.infoEffect[1] = "SECOND EFFECT: Choose a square exactly one move away. Deal 1 damage and 1 mark to everyone on that square.\n";
        this.infoEffect[2] = "THIRD EFFECT : This effect doesn't exist;\n";
        this.infoEffect[3] = "NOTE : You can use only one effect.\n";
        this.rechargeCube = new ColorCube[2];
        this.rechargeCube[0] = ColorCube.RED;
        this.rechargeCube[1] = ColorCube.BLUE;
        setWeaponCardDescription();
    }

    /**
     * Add 1 damage to all players in one square
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param x1 first coordinate of square
     * @param y1 second coordinate of square
     */

    private void damageFurnace(GameBoard gameBoard, Player attacker, int x1, int y1) throws DamageTrackException {

        ArrayList<Player> playerList = gameBoard.playersInOneSquare(x1, y1, null);

        for (Player aPlayerList : playerList){
            aPlayerList.sufferDamageOrMark(attacker.getColor(), 1, 0);
            controlPlayersDamages(gameBoard, aPlayerList);
        }

    }

    /**
     * Add 1 damage and 1 mark to all players in one square
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param players array list of defenders
     */

    private void damageAndMarkFurnace(GameBoard gameBoard, Player attacker, ArrayList<Player> players) throws DamageTrackException {

        for (Player aPlayerList : players){
            aPlayerList.sufferDamageOrMark(attacker.getColor(), 1, 1);
            controlPlayersDamages(gameBoard, aPlayerList);
        }

    }

    /**
     * Choose a room you can see, but not the room you are in. Deal 1 damage to everyone in that room.
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */

    @Override
    public void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException { //DA RIVEDERE

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
     * Choose a square exactly one move away. Deal 1 damage and 1 mark to everyone on that square.
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     */

    @Override
    public void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws ErrorEffectException, DamageTrackException {

        ArrayList<Player> playerList;

        if(gameBoard.isSquareAvailableOnArena(attacker, coordinates[0], coordinates[1])){ //se è una cella visibile

            if(oneDistance(attacker.getX(), attacker.getY(), coordinates[0], coordinates[1])) { //se dista esattamente 1
                playerList = gameBoard.playersInOneSquare(coordinates[0], coordinates[1], null); //lista di tutti i giocatori all'interno di quella cella

                if(!playerList.isEmpty()){ // se c'è qualche giocatore dentro la stanza
                    damageAndMarkFurnace(gameBoard, attacker, defenders); // fai il danno
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
     */


    @Override
    public void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException {

        /* NON ESISTE L'EFFETTO */

        throw new NoEffectException();
    }


}