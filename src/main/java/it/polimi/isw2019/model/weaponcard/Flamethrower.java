package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.NoEffectException;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;

import java.util.ArrayList;

public class Flamethrower extends AbstractWeaponCard {


    public Flamethrower() {
        super(12, "Flamethrower", ColorCube.RED, 1);
        this.infoEffect = new ArrayList<>();
        this.infoEffect.add("BASIC MODE: FChoose a square 1 move away and possibly a second square\n" +
                "1 more move away in the same direction. On each square, you may\n" +
                "choose 1 target and give it 1 damage.\n");
        this.infoEffect.add("IN BARBECUE MODE: Choose 2 squares as above. Deal 2 damage to\n" +
                "everyone on the first square and 1 damage to everyone on the second\n" +
                "square.\n");
        this.infoEffect.add("NOTES: This weapon cannot damage anyone in your square. However,\n" +
                "it can sometimes damage a target you can't see – the flame won't go\n" +
                "through walls, but it will go through doors. Think of it as a straight-line\n" +
                "blast of flame that can travel 2 squares in a cardinal direction.\n");
        this.rechargeCube[0] = 1;
        this.rechargeCube[1] = 0;
        this.rechargeCube[2] = 0;
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

        ArrayList<Player> visilePlayers = gameBoard.playersWhoCanSee(attacker); //LISTA GIOCATORI VISIBILI
        char dir1;
        char dir2;


        if (gameBoard.isSquareAvailableOnArena(attacker, defenders.get(0).getX(), defenders.get(0).getY())) { //SE È IN UNA CELLA ADIACENTE
            dir1 = direction(attacker, defenders.get(0)); //SALVO LA DIREZIONE

            try{
                defenders.get(0).sufferDamageOrMark(attacker.getColor(), 1, 0); //FAI UN DANNO
            } catch (DamageTrackException e) {
                e.getMessage();
            }

            dir2 = direction(attacker, defenders.get(1)); //SALVO LA DIREZIONE DEL SECONDO DIFENSORE RISPETTO ALL'ATTACCANTE

            if ((gameBoard.isSquareAvailableOnArena(defenders.get(0), defenders.get(1).getX(), defenders.get(1).getY())) && (dir1 == dir2)){ //SE LA SECONDA CELLA È ADIACENTE ALLA PRIMA E LA dir1 = dir2 (STESSA DIREZIONE) ALLORA FAI UN DANNO
                try{
                    defenders.get(1).sufferDamageOrMark(attacker.getColor(), 1, 0);
                } catch (DamageTrackException e) {
                    e.getMessage();
                }
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

        /* PAGARE DUE GIALLI */

        ArrayList<Player> playersListFirstSquare = gameBoard.playersInOneSquare(coordinates[0], coordinates[1], null); //LISTA GIOCATORI PRIMA CELLA
        char dir1 = direction(attacker, playersListFirstSquare.get(0)); //SALVO LA DIREZIONE

        if(gameBoard.isSquareAvailableOnArena(attacker, coordinates[0],  coordinates[1])){ //VERIFICO SE LA CELLA È ADIACENTE

            for (Player player:playersListFirstSquare) {

                try {
                    player.sufferDamageOrMark(attacker.getColor(), 2, 0); //DUE DANNI
                } catch (DamageTrackException e){
                    e.getMessage();
                }
            }
        }else {
            throw new ErrorEffectException();
        }

        ArrayList<Player> playersListSecondSquare = gameBoard.playersInOneSquare(coordinates[2], coordinates[3], null); //LISTA GIOCATORI DELLA SECONDA CELLA
        char dir2 = direction(attacker, playersListSecondSquare.get(0)); // SALVO LA DIREZIONE

        if ((gameBoard.isSquareAvailableOnArena(playersListFirstSquare.get(0), coordinates[2], coordinates[3])) && (dir1 == dir2)) { //SE LA CELLA È ADIACENTE ALLA PRIMA SELEZIONATA E LA DIREZIONE È UGUALE ALLORA FAI 1 DANNI A TUTTI

            for (Player player : playersListSecondSquare) {

                try {
                    player.sufferDamageOrMark(attacker.getColor(), 1, 0);
                } catch (DamageTrackException e) {
                    e.getMessage();
                }
            }
        }else {
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

        /*NON C'È L'EFFETTO */

        throw new NoEffectException();

    }


}
