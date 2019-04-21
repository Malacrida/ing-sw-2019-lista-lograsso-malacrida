package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.WeaponCard.WeaponCard;

import java.util.ArrayList;

public class Player {
   private String name;
   private ColorPlayer color;
   private Position position;
   private ArrayList<WeaponCard> weaponCards = new ArrayList<>();
   private PlayerBoard playerBoard;


    public Player(String name, ColorPlayer color, PlayerBoard playerBoard) {
        this.name = name;
        this.color = color;
        position= new Position(-1, -1,null); // valori fuori dal range accettabile della mappa
        this.playerBoard = playerBoard;
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
    public void takeWeaponCards(WeaponCard insertWeaponCard, WeaponCard removeWeaponCard) {
        if (weaponCards.size()<3) {
            weaponCards.add(insertWeaponCard);
        }
        else{
            weaponCards.remove(removeWeaponCard);
            weaponCards.add(insertWeaponCard);
        }
    }




}
