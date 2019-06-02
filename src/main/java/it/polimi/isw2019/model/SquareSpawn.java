package it.polimi.isw2019.model;

import it.polimi.isw2019.model.weaponcard.AbstractWeaponCard;

import java.util.ArrayList;

public class SquareSpawn extends Square {

    private AbstractWeaponCard[] weaponCards= new AbstractWeaponCard[3];

    SquareSpawn() {
        super(true);

    }

    @Override
    public boolean containsWeapon (AbstractWeaponCard weaponCard){
        for (int i=0; i<3; i++){
            if (weaponCards[i]==weaponCard) return true;
        }
        return false;
    }

    @Override
    public void takeWeapon (AbstractWeaponCard weaponCard){
        for (int i=0; i<3; i++){
            if (weaponCards[i]==weaponCard) {
                weaponCards[i].changeState(StateCard.HOLDING);
                weaponCards[i]=null;
            }
        }
    }

    @Override
    public AbstractWeaponCard[] getWeaponCards() {
        return weaponCards;
    }

    public int numOfWeaponCards(){
        int cont =0;
        for (int i=0; i<3; i++){
            if (weaponCards[i]!=null) cont++;
        }
        return cont;
    }


    @Override
    public void setWeaponCards(AbstractWeaponCard[] weaponCards) {
        this.weaponCards=weaponCards;
    }

    @Override
    public void putNewWeaponCard(AbstractWeaponCard weaponCard) {
        if (numOfWeaponCards()<3){
            for (int i=0; i<3; i++){
                if (weaponCards[i]== null) weaponCards[i]= weaponCard;
            }
        }

    }
}
