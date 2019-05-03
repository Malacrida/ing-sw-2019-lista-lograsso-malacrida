package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.WeaponCard.AbstractWeaponCard;

import java.util.ArrayList;

public class SquareSpawn extends Square {

    private ArrayList<AbstractWeaponCard> weaponCards= new ArrayList<>();


    SquareSpawn() {
        super(true);

    }

    @Override
    public boolean containsWeapon (AbstractWeaponCard weaponCard){
        if (weaponCards.contains(weaponCard)) return true;
        else return false;
    }

    @Override
    public void takeWeapon (AbstractWeaponCard weaponCard){
        if (weaponCards.contains(weaponCard)){
            weaponCards.remove(weaponCard);
            //Cambiare lo stato della carta
        }
    }

    public ArrayList<AbstractWeaponCard> getWeaponCards() {
        return weaponCards;
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
