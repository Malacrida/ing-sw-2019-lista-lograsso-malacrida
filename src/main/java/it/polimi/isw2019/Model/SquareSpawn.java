package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.WeaponCard.AbstractWeaponCard;

import java.util.ArrayList;

public class SquareSpawn extends Square {

    private ArrayList<AbstractWeaponCard> weaponCards;

    SquareSpawn(Square squareN, Square squareE, Square squareS, Square squareO) {
        super(squareN, squareE, squareO, squareS, true);
    }

    public void spawnPlayer (Player player){

    }

    public boolean containsWeapon (AbstractWeaponCard weaponCard){
        if (weaponCards.contains(weaponCard)) return true;
        else return false;
    }

    public void takeWeapon (AbstractWeaponCard weaponCard){
        if (weaponCards.contains(weaponCard)){
            weaponCards.remove(weaponCard);
            //Cambiare lo stato della carta
        }
    }

    public void putNewWeponCard(AbstractWeaponCard weaponCard){
        if (weaponCards.size()<3){
            weaponCards.add(weaponCard);
        }

    }
}
