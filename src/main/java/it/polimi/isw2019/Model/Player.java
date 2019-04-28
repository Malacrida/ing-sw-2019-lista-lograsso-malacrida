package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.PowerUpCard.PowerUpCard;
import it.polimi.isw2019.Model.WeaponCard.AbstractWeaponCard;

import java.util.ArrayList;

public class Player {
    private String name;
    private String actionHeroComment; //frase effetto
    private int playerID;
    private ColorPlayer color;
    private ArrayList<AbstractWeaponCard> weaponCards = new ArrayList<>();
    private ArrayList<PowerUpCard> powerUpCards = new ArrayList<>();
    private AbstractPlayerBoard playerBoard;
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



    public void addScore (int point){
        score= score+point;
    }

    public void sufferDamage (ColorPlayer colorPlayer, int numDamage, int numMark){
        playerBoard.takeDamage(colorPlayer, numDamage);
        if (numMark>0){
            playerBoard.addMark(colorPlayer, numMark);
        }
    }

    public void giveMark (ColorPlayer colorPlayer, int numMark) {
        playerBoard.addMark(colorPlayer, numMark);
    }



}
