package it.polimi.isw2019.model;

import it.polimi.isw2019.model.weaponcard.AbstractWeaponCard;

import java.util.ArrayList;

public class SquareSpawn extends Square {

    private ArrayList<AbstractWeaponCard> weaponCards= new ArrayList<>();
    private String[][] descriptionWeaponCard;

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
        if (containsWeapon(weaponCard)){
            weaponCards.remove(weaponCard);
            //Cambiare lo stato della carta
        }
    }

    @Override
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
