package it.polimi.isw2019.model;

import it.polimi.isw2019.model.weaponcard.AbstractWeaponCard;

import java.util.ArrayList;

public class SquareSpawn extends Square {

    private AbstractWeaponCard[] weaponCards;

    SquareSpawn() {
        super(true);
        weaponCards =  new AbstractWeaponCard[3];
    }

    /**
     * method to see if there are all weapon card on that square
     * @param weaponCard
     * @return boolean
     */

    @Override
    public boolean containsWeapon (AbstractWeaponCard weaponCard){
        for (int i=0; i<3; i++){
            if (weaponCards[i]==weaponCard) return true;
        }
        return false;
    }

    /**
     * method to take a wepon card
     * @param weaponCard
     */
    @Override
    public void takeWeapon (AbstractWeaponCard weaponCard){
        for (int i=0; i<3; i++){
            if (weaponCards[i].getID() == weaponCard.getID()) {
                weaponCards[i].changeState(StateCard.HOLDING);
                weaponCards[i]=null;
            }
        }
    }

    /**
     * getter weapon cards' list
     * @return
     */

    @Override
    public AbstractWeaponCard[] getWeaponCards() {
        return weaponCards;
    }

    /**
     * method to count weapon cards
     * @return number of weapon cards
     */

    public int numOfWeaponCards(){
        int cont =0;
        for (int i=0; i<3; i++){
            if (weaponCards[i]!=null) cont++;
        }
        return cont;
    }

    /**
     * setter of weapon cards
     * @param weaponCards
     */

    @Override
    public void setWeaponCards(AbstractWeaponCard[] weaponCards) {
        this.weaponCards=weaponCards;
    }

    /**
     * insert new weapon card
     * @param weaponCard
     */

    @Override
    public void putNewWeaponCard(AbstractWeaponCard weaponCard) {
        if (numOfWeaponCards()<3){
            for (int i=0; i<3; i++){
                if (weaponCards[i]== null) weaponCards[i]= weaponCard;
            }
        }

    }

}
