package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.WeaponCard.AbstractWeaponCard;
import it.polimi.isw2019.Model.WeaponCard.WeaponCard;

import java.util.ArrayList;

public class Player {
   private String name;
   private ColorPlayer color;
   private Position position;
   private ArrayList<AbstractWeaponCard> weaponCards = new ArrayList<>();
   private PlayerBoard playerBoard;
   private ArrayList <ColorPlayer> damanges = new ArrayList<>();
   private ArrayList <ColorPlayer> marks = new ArrayList<>();
   private int skulls; //numero teschi = numero di morti subite
   private int score; // punteggio del giocatore
   // x, y, colorRoom show the player's position
   private int x;
   private int y;
   private ColorRoom colorRoom;


    public Player(String name, ColorPlayer color, PlayerBoard playerBoard) {
        this.name = name;
        this.color = color;
        this.playerBoard = playerBoard;
        skulls=0;
        //value out of range of play
        x=-1;
        y=-1;
        colorRoom= null;
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

    public Position getPosition() {
        return position;
    }

    //nel model un metodo che unisce questo del player e quello con la gameboard
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

    //Numero di danni totali subiti dal giocatore e controllo sulla morte
    public int numOfDamanges () throws KillShot, OverKill {
        if (damanges.size()==11) throw new KillShot();
        if (damanges.size()>=12) throw new OverKill(damanges.get(11));
        return damanges.size();
    }

    //numero di marchi di un tipo di colore posseduti dal player
    public int numOfMarkOfOneColor (ColorPlayer colorPlayer){
        int cont=0;
        for (int i=0; i<marks.size(); i++){
            if (marks.get(i)==colorPlayer){
                cont++;
            }
        }
        return cont;
    }

    //rimzione dei marchi di un colore posseduti dal player
    public void removeMarkOfOneColor (ColorPlayer colorPlayer){
        for (int i=0; i<marks.size(); i++){
            if (marks.get(i)==colorPlayer){
                marks.remove(i);
            }
        }
    }

    //Aggiungere i danni al giocatore
    public void takeDamage (ColorPlayer colorPlayer, int numberOfDamage){
        for (int i=0; i<numberOfDamage; i++){
            damanges.add(colorPlayer);
        }
        if (numOfMarkOfOneColor(colorPlayer)>0){
            for (int i=0; i<numOfMarkOfOneColor(colorPlayer); i++){
                damanges.add(colorPlayer);
            }
        }
    }

    //Aggiunge marchi ma nel caso supera i tre marchi dello stesso colore non vengono aggiunti
    // ma non si lancia nemmeno un eccezione
    public void addMark (ColorPlayer colorPlayer, int numberOfMark) {
        for (int i=0; i<numberOfMark; i++){
            if (numOfMarkOfOneColor(colorPlayer)<3){
                marks.add(colorPlayer);
            }
            else break;
        }
    }

    public void deathPlayer (){
        skulls++;
        damanges.clear();
    }

    public void addScore (int point){
        score= score+point;
    }




}
