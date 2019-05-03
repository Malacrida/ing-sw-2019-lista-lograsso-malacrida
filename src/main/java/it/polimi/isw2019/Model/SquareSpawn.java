package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.WeaponCard.AbstractWeaponCard;

import java.util.ArrayList;

public class SquareSpawn extends Square {

    private ArrayList<AbstractWeaponCard> weaponCards= new ArrayList<>();


    SquareSpawn(Square squareN, Square squareE, Square squareS, Square squareO) {
        super(squareN, squareE, squareO, squareS, true);

    }

    public void spawnPlayer (Player player) throws NullPointerException{
        if (player!= null) {
            players.add(player);
        }
        else throw new NullPointerException();
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



    public int numOfWeaponCards(){

        return weaponCards.size();
    }


    @Override
    public void setWeaponCards(ArrayList<AbstractWeaponCard> weaponCards) {
        this.weaponCards=weaponCards;
    }

    @Override
    public void putNewWeaponCard(AbstractWeaponCard weaponCard) {
        if (weaponCards.size()<3){
            weaponCards.add(weaponCard);
        }

    }
}
