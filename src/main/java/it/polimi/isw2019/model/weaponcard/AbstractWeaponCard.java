package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;
import it.polimi.isw2019.model.StateCard;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoEffectException;

import java.util.ArrayList;

public abstract class AbstractWeaponCard{
    protected int id;
    protected String name;
    protected ColorCube color;
    protected ArrayList<String> infoEffect;
    protected ArrayList<Player> deathPlayers;
    protected int[] rechargeCube = new int[3]; //LEGENDA [0] -> RED [1] -> YELLOW [2] -> BLUE
    protected StateCard stateCard = StateCard.DECK;
    protected int maxPossibleEffects;
    protected boolean firstIsValid = false;
    protected boolean secondIsValid = false;

    private String[][] weaponCardDescription;


    public AbstractWeaponCard(int id, String name, ColorCube color, int maxPossibleEffects) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.maxPossibleEffects = maxPossibleEffects;
    }

    //Methods


    public int getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int[] getRechargecube(){ return rechargeCube; }

    public ColorCube getColor(){
        return color;
    }

    public ArrayList<String> getInfoEffect(){
        return infoEffect;
    }

    public StateCard checkState(){
        return stateCard;
    }

    public void changeState(StateCard newStateCard) {
        this.stateCard = newStateCard;
    }


    /* EFFETTI (abstract) */

    /*
    * attackSquare -> square in cui vuole attaccare
    * attacker -> Player attaccante
    * defender -> Player colpito
    */

    /**
     * FIRSTEFFECT
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     * @throws NoEffectException there is not the effect
     * @throws DamageTrackException there is a problem with Damage Track
     *
     * @æuthor Davide Lista
     */
    public abstract void firstEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException, ErrorEffectException, DamageTrackException;

    /**
     * SECONDEFFECT
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     * @throws NoEffectException there is not the effect
     * @throws DamageTrackException there is a problem with Damage Track
     *
     * @æuthor Davide Lista
     */

    public abstract void secondEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException, ErrorEffectException, DamageTrackException;

    /**
     * THIRDEFFECT
     * @param gameBoard is the Gameboard where players play
     * @param attacker is the player who use Weapon card
     * @param defenders are players attacked
     * @param coordinates some coordinates used to move players or to indicate squares to attack players
     * @throws ErrorEffectException there is a problem during effect
     * @throws NoEffectException there is not the effect
     * @throws DamageTrackException there is a problem with Damage Track
     *
     * @æuthor Davide Lista
     */

    public abstract void thirdEffect(GameBoard gameBoard, Player attacker, ArrayList<Player> defenders, int[] coordinates) throws NoEffectException, ErrorEffectException, DamageTrackException;

/*METODI DI CONTROLLO*/

    /* I due giocatori distano di 1 sull'asse delle X e hanno la stessa Y*/

    protected boolean oneDistanceY (int x1, int y1, int x2, int y2){
        return (x1 == x2) && (Math.abs(y1 - y2) == 1);
    }

    /* I due giocatori distano di 1 sull'asse delle X e hanno la stessa Y*/

    protected boolean oneDistanceX (int x1, int y1, int x2, int y2){
        return (y1 == y2) && (Math.abs(x1 - x2) == 1);
    }

    /* Cella distante almeno 1 o 2 celle (se move = 1 allora è 1 cella se è 2 allora è 2 celle) */

    protected boolean moreThanOneOrTwoDistance(int x1, int y1, int x2, int y2, int minDistance){

        if (minDistance == 1) {
            return (x1 == x2) && (Math.abs(y1 - y2) >= 1) || (y1 == y2) && (Math.abs(x1 - x2) >= 1);
        }

        else if (minDistance == 2) {
            return (x1 == x2) && (Math.abs(y1 - y2) > 1) || (y1 == y2) && (Math.abs(x1 - x2) > 1);
        }
        else return false;
    }

    /* I due giocatori sono nella stessa stanza */

    protected boolean sameSquare (int x1, int y1, int x2, int y2){
        return (x1 == x2) && (y1 == y2);
    }

    protected boolean threePlayersSameSquare(Player p1, Player p2, Player p3){
        return (sameSquare(p1.getX(), p1.getY(), p2.getX(), p2.getY())) || (sameSquare(p1.getX(), p1.getY(), p3.getX(), p3.getY())) || (sameSquare(p2.getX(), p2.getY(), p3.getX(), p3.getY()));
    }

    protected boolean threeSquaresAvailable(GameBoard gameBoard, Player attacker, Player p1, Player p2, Player p3){
        return gameBoard.isSquareAvailableOnArena(attacker, p1.getX(), p1.getY()) && gameBoard.isSquareAvailableOnArena(attacker, p2.getX(), p2.getY()) && gameBoard.isSquareAvailableOnArena(attacker, p3.getX(), p3.getY());
    }

    protected char direction(Player firstPlayer, Player secondPlayer) {

        int x1 = firstPlayer.getX();
        int y1 = firstPlayer.getY();
        int x2 = secondPlayer.getX();
        int y2 = secondPlayer.getY();
        char dir;

        if (x1 == x2){

            dir = 'x';

            return dir;

        }

        else if (y1 == y2) {

            dir = 'y';

            return dir;

        }

        else {

            dir = 'n';

            return dir;

        }

    }

    /* METODI DANNI NO DUPLICAZIONE*/

    /* fai un danno se il primo effetto è valido */

    protected boolean oneDamageIfFirstIsValid(Player attacker, Player defender, boolean firstIsValid){

        if(firstIsValid){
            try {
                defender.sufferDamageOrMark(attacker.getColor(), 1,0);
            } catch (DamageTrackException e) {
                e.getMessage();
            }
            return true;
        } else {
            return false;
        }
    }

    /* 1 danno a tutti i player in una cella */

    protected void oneDamageAllPlayersInOneSquare(Player attacker, ArrayList<Player> playerList) {
        for (Player aPlayerList : playerList) {

            try {

                aPlayerList.sufferDamageOrMark(attacker.getColor(), 1, 0);

            } catch (DamageTrackException e) {
                e.getMessage();
            }
        }
    }

    /*2 danni a un giocatore nella stessa stanza dell'attaccante*/

    protected void twoDamageInSameSquare (Player attacker, Player defender) throws ErrorEffectException { //utile per SledgeHammer e CyberBlade
        if (sameSquare(attacker.getX(), attacker.getY(), defender.getX(), defender.getY())){
            try {
                defender.sufferDamageOrMark(attacker.getColor(),2,0);
            }catch (DamageTrackException e){
                e.getMessage();
            }

        }

        else {
            throw new ErrorEffectException();
        }
    }

    /* 3 danni a un giocatore nella stessa stanza dell'attaccante */
    protected void threeDamageInSameSquare (Player attacker, Player defender) throws ErrorEffectException {

        if (sameSquare(attacker.getX(), attacker.getY(), defender.getX(),  defender.getY())){
            try {
                defender.sufferDamageOrMark(attacker.getColor(), 3, 0);
            } catch (DamageTrackException e) {
                e.getMessage();
            }
        } else {
            throw new ErrorEffectException();
        }
    }

    protected void twoDamageAndSetFirstIsValid (Player attacker, Player defender, ArrayList<Player> visiblePlayers) throws ErrorEffectException {

        if ((defender != null) && (visiblePlayers.contains(defender))){

            try {
                defender.sufferDamageOrMark(attacker.getColor(), 2,0);
            } catch (DamageTrackException e) {
                e.getMessage();
            }
            firstIsValid = true;

        } else {
            throw new ErrorEffectException();
        }
    }

    protected boolean playersAreVisible (ArrayList<Player> visiblePlayers, Player firstPlayer, Player secondPlayer, Player thirdPlayer){

        return visiblePlayers.contains(firstPlayer) && visiblePlayers.contains(secondPlayer) && visiblePlayers.contains(thirdPlayer);

    }
}