package it.polimi.isw2019.Server.Model;

import it.polimi.isw2019.Server.Model.WeaponCard.AbstractWeaponCard;

import java.util.ArrayList;

public interface IntrerfaceSquare {

    public void setWeaponCards(ArrayList<AbstractWeaponCard> weaponCards);
    public void putNewWeaponCard(AbstractWeaponCard weaponCard);
    public void takeWeapon (AbstractWeaponCard weaponCard);
    public boolean containsWeapon (AbstractWeaponCard weaponCard);
}
