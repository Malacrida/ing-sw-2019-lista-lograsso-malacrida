package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.GameBoard;
import it.polimi.isw2019.model.Player;
import it.polimi.isw2019.model.StateCard;
import it.polimi.isw2019.model.exception.*;
import it.polimi.isw2019.model.powerupcard.PowerUpCard;

import java.awt.*;
import java.security.ProtectionDomain;
import java.util.ArrayList;

public abstract class AbstractWeaponCard implements WeaponCardInterface {
    protected int id;
    protected String name;
    protected ColorCube color;
    protected String[] infoEffect = new String[4];
    protected ArrayList<Player> deathPlayers;
    protected ColorCube[] rechargeCube; //pay first effect
    protected ColorCube[] paySecondEffect;
    protected ColorCube[] payThirdEffect;
    protected StateCard stateCard = StateCard.DECK;
    protected int maxPossibleEffects;
    protected int maxPossibleCoordinates;
    protected int maxPossibleDefenders;
    protected boolean firstIsValid = false;
    protected boolean secondIsValid = false;

    private String[][] weaponCardDescription;


    public AbstractWeaponCard(int id, String name, ColorCube color, int maxPossibleEffects, int maxPossibleCoordinates, int maxPossibleDefenders) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.maxPossibleEffects = maxPossibleEffects;
        this.maxPossibleCoordinates = maxPossibleCoordinates;
        this.maxPossibleDefenders = maxPossibleDefenders;
    }


//Methods

    public int getID(){
        return id;
    }

    public String getName(){
        return name;
    }

    public ColorCube[] getRechargeCube(){ return rechargeCube; }

    public ColorCube[] getPaySecondEffect(){
        return paySecondEffect;
    }

    public ColorCube[] getPayThirdEffect(){
        return payThirdEffect;
    }


    public ColorCube getColor(){
        return color;
    }

    public String[] getInfoEffect(){
        return infoEffect;
    }

    public StateCard getStateCard(){
        return stateCard;
    }

    public int getMaxPossibleEffects() {
        return maxPossibleEffects;
    }

    public int getMaxPossibleCoordinates() {return maxPossibleCoordinates; }

    public int getMaxPossibleDefenders() {return  maxPossibleDefenders; }

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

    protected boolean oneDistance (int x1, int y1, int x2, int y2) {
        if (oneDistanceX(x1, y1, x2, y2) && !oneDistanceY(x1, y1, x2, y2)){
            return true;
        }

        if (oneDistanceY(x1, y1, x2, y2) && !oneDistanceX(x1, y1, x2, y2)){
            return true;
        }

        else return false;
    }

    /* Cella distante almeno 1 o 2 celle (se move = 1 allora è 1 cella se è 2 allora è 2 celle) */

    protected boolean moreThanTwoDistance(int x1, int y1, int x2, int y2){
        return (x1 == x2) && (Math.abs(y1 - y2) > 1) || (y1 == y2) && (Math.abs(x1 - x2) > 1);
    }

    /* I due giocatori sono nella stessa stanza */

    protected boolean sameSquare (int x1, int y1, int x2, int y2){
        return (x1 == x2) && (y1 == y2);
    }

    protected boolean threePlayersSameSquare(Player p1, Player p2, Player p3){

        if (p1 != null && p2 != null && p3 != null) {
            return (sameSquare(p1.getX(), p1.getY(), p2.getX(), p2.getY())) || (sameSquare(p1.getX(), p1.getY(), p3.getX(), p3.getY())) || (sameSquare(p2.getX(), p2.getY(), p3.getX(), p3.getY()));
        }
        else if (p1 == null){
            return (sameSquare(p2.getX(), p2.getY(), p3.getX(), p3.getY()));
        }
        else if (p2 == null){
            return (sameSquare(p1.getX(), p1.getY(), p3.getX(), p3.getY()));
        }
        else {
            return (sameSquare(p1.getX(), p1.getY(), p2.getX(), p2.getY()));
        }
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

    /* danni a un giocatore nella stessa stanza dell'attaccante*/

    protected void damagesInSameSquare(Player attacker, Player defender, int damages) throws ErrorEffectException { //utile per SledgeHammer e CyberBlade
        if (sameSquare(attacker.getX(), attacker.getY(), defender.getX(), defender.getY())){
            try {
                defender.sufferDamageOrMark(attacker.getColor(),damages,0);
            }catch (DamageTrackException e){
                e.getMessage();
            }

        }

        else {
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
            this.firstIsValid = true;

        } else {
            throw new ErrorEffectException();
        }
    }

    protected boolean playersAreVisible (ArrayList<Player> visiblePlayers, Player firstPlayer, Player secondPlayer, Player thirdPlayer){

        return visiblePlayers.contains(firstPlayer) && visiblePlayers.contains(secondPlayer) && visiblePlayers.contains(thirdPlayer);

    }

    public void setWeaponCardDescription(){
        int numOfCubes = rechargeCube.length;
        weaponCardDescription = new String[6][];
        weaponCardDescription[0][0] = String.valueOf(id);
        weaponCardDescription[1][0] = name;
        weaponCardDescription[2][0] = color.getColorCubeRepresentation();
        for(int i=0; i<rechargeCube.length;i++)
            weaponCardDescription[3][i] = rechargeCube[i].getColorCubeRepresentation();
        for(int i=0;i<infoEffect.length; i++)
            weaponCardDescription[4][i] = infoEffect[i];
        weaponCardDescription[5][0] = stateCard.getStateCardRepresentation();


    }

    public void changeStateRepresentation(StateCard stateCard){
        weaponCardDescription[5][0] = stateCard.getStateCardRepresentation();
    }

    public int getNumCubes(){
        return rechargeCube.length;
    }
    public WeaponCardInterface getWeaponCard(){
        return this;
    }
    public String[][] getWeaponCardDescription(){
        return weaponCardDescription;
    }

    public int getNumMaxEffect(){
        return maxPossibleEffects;
    }
    public int getNumMaxDefenders(){
        return maxPossibleDefenders;
    }
    public int getNumMaxCoordinates(){
        return maxPossibleCoordinates;
    }

    /* public void handlePaymentPerEffect(Player player,int number, ArrayList<PowerUpCard> powerUpCards, ArrayList<ColorCube> colorCubes){

    }

    public void handleRecharge(Player player, ArrayList<PowerUpCard> powerUpCards, ArrayList<ColorCube> colorCubes) throws InvalidInsert, NoCubesException {
        if((powerUpCards.size() + colorCubes.size()) > rechargeCube.length){
            throw new InvalidInsert("you've inserted too many cubes");
        }
        else if((powerUpCards.size() + colorCubes.size()) < rechargeCube.length){
            throw new InvalidInsert("you've inserted too few cubes");
        }

        else{
            player.getRealPlayerBoard().removeCube(colorCubes);
           // player.getRealPlayerBoard().payment
        }
    }
*/
}