package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.WeaponCard.AbstractWeaponCard;

import java.util.ArrayList;

public interface IntrerfaceSquare {

    public void setWeaponCards(ArrayList<AbstractWeaponCard> weaponCards);
    public void putNewWeaponCard(AbstractWeaponCard weaponCard);
    public void takeWeapon (AbstractWeaponCard weaponCard);
    public boolean containsWeapon (AbstractWeaponCard weaponCard);
}
