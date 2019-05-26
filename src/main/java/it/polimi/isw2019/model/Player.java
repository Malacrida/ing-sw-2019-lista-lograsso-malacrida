package it.polimi.isw2019.model;

import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.OutOfBoundsException;
import it.polimi.isw2019.model.powerupcard.PowerUpCard;
import it.polimi.isw2019.model.weaponcard.AbstractWeaponCard;

import java.util.ArrayList;

public class Player {
    private String name;
    private String actionHeroComment; //frase effetto
    private int playerID;
    private ColorPlayer color;
    private boolean firstPlayer;
    private ArrayList<AbstractWeaponCard> weaponCards = new ArrayList<>(); // cariche?
    private ArrayList<PowerUpCard> powerUpCards = new ArrayList<>();
    private PlayerBoard playerBoard;
    private int score; // punteggio del giocatore
    // x, y, colorRoom show the player's position
    private int x;
    private int y;
    private ColorRoom colorRoom;


    public Player(String name, String actionHeroComment, int playerID) {
        this.name = name;
        this.actionHeroComment=actionHeroComment;
        this.playerID=playerID;
        //value out of range of play
        x=-1;
        y=-1;
        colorRoom= null;
    }

    public void setPlayerBoardAndColor (PlayerBoard playerBoard, ColorPlayer color) {
        this.playerBoard = playerBoard;
        this.color = color;
    }

    public void setFirstPlayer(boolean firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    public Player getPlayer() {
      return this;
   }
    public String getName() {
      return this.name;
   }
    public ColorPlayer getColor(){
      return this.color;
   }
    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }
    public ColorRoom getColorRoom() {
        return colorRoom;
    }
    public int getScore() {
        return score;
    }
    public int getPlayerID() {
        return playerID;
    }

    //Nel model un metodo che unisce questo del player e quello con la gameboard
    //Dalla playerMove la carta da scartare
    //Ricordarsi il cambio di stato
    //Inserimento di una nuova carta

    public void takeWeaponCards(AbstractWeaponCard insertWeaponCard, AbstractWeaponCard removeWeaponCard) {
        if (weaponCards.size()<3) {
            weaponCards.add(insertWeaponCard);
        }
        else{
            weaponCards.remove(removeWeaponCard);
            weaponCards.add(insertWeaponCard);
        }
    }

    //nel model un metodo che unisce questo del player e quello con la gameboard
    //Dalla playerMove la carta da scartare
    //Ricordarsi il cambio di stato
    //Inserimento di una nuova carta

    public void takePowerUpCard (PowerUpCard insertPowerUpCard, PowerUpCard removePowerUpCard){
        if (powerUpCards.size()<3){
            powerUpCards.add(insertPowerUpCard);
        }
        else {
            powerUpCards.remove(removePowerUpCard);
            powerUpCards.add(insertPowerUpCard);
        }

    }

    //Cambio di stato della carta
    public void usePowerUpCard (PowerUpCard powerUpCard){
        //Uso della powerUp
        powerUpCards.remove(powerUpCard);
    }

    //non fare test
    public void reloadWeaponCard (AbstractWeaponCard weaponCard) throws OutOfBoundsException {
        int [] price = new int[3];
        //price = weaponCard.getPrice ();
        for (int i=0; i<3; i++){
            if (i==0){
                playerBoard.removeRedCubes(price[0]);
            }
            if (i==1){
                playerBoard.removeYellowCubes(price[1]);
            }
            if (i==2){
                playerBoard.removeBlueCubes(price[2]);
            }
        }
    }

    //non fare test
    public void payEffect (int costRed, int costYellow, int costBlue ) throws OutOfBoundsException {
        try {
            if (costRed > 0) {
                playerBoard.removeRedCubes(costRed);
            }
        }
        catch (OutOfBoundsException e){
            throw new OutOfBoundsException(e.getInfo());
        }
        try {

            if (costYellow > 0) {
                playerBoard.removeYellowCubes(costYellow);
            }
        }
        catch (OutOfBoundsException e){
            throw new OutOfBoundsException(e.getInfo());
        }
        try {
            if (costBlue==2){
                playerBoard.removeBlueCubes(costBlue);
            }
        }
        catch (OutOfBoundsException e){
            throw new OutOfBoundsException(e.getInfo());
        }

    }


    public void addScore (int point){
        score= score+point;
    }


    /**
     * Give damage token or mark token to the player
     * @param colorPlayer!= null Color of the player who give damage
     * @param numDamage>=0 number of damage
     * @param numMark>=0 number of mark
     * @throws DamageTrackException if player, who take damage, have 11 or 12 damage token
     */
    public void sufferDamageOrMark (ColorPlayer colorPlayer, int numDamage, int numMark)throws DamageTrackException {

        try {
            if (numDamage>0){
                playerBoard.takeDamage(colorPlayer, numDamage);
            }
        }
        catch (DamageTrackException e){
            throw new DamageTrackException(colorPlayer);
        }
        finally {
            if (numMark>0){
                playerBoard.addMark(colorPlayer, numMark);
            }
        }
    }



    public int damageDoByAnotherPlayer(ColorPlayer colorPlayer){
        return playerBoard.numOfDamagesOfOneColor(colorPlayer);
    }

    public int markDoByAnotherPlayer(ColorPlayer colorPlayer){
        return playerBoard.numOfMarkOfOneColor(colorPlayer);
    }

    public int playerDamage () {
        return playerBoard.numOfDamages();
    }



    public ColorPlayer firstPlayerDoDamage (){
        return playerBoard.firstBlood();
    }

    public ColorPlayer lastPlayerDoDamage () { return playerBoard.killShot();}

    public int getNumberOfSkulls (){
        return playerBoard.getPlayerSkulls();
    }

    public void changePosition (int x, int y, ColorRoom colorRoom){
        this.x=x;
        this.y=y;
        this.colorRoom=colorRoom;
    }

    public void changeSquare(int x, int y){
        this.x=x;
        this.y=y;
    }

    public void changeRoom (ColorRoom colorRoom){
        this.colorRoom=colorRoom;
    }

    public void playerDeath (){
        playerBoard.resetAfterDeath();
    }

    public ArrayList<AbstractWeaponCard> getWeaponCards(){
        return this.weaponCards;
    }

    public ArrayList<PowerUpCard> getPowerUpCards(){
        return this.powerUpCards;
    }


}
