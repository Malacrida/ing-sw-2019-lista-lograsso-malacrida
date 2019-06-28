package it.polimi.isw2019.model;

import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.powerupcard.PowerUpCardInterface;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;

import java.util.ArrayList;

public interface PlayerInterface {

    public String toString();

    public String getName();
    public String getActionHeroComment();
    public PlayerBoardInterface getPlayerBoard();
    public int getScore();
    public ArrayList<InterfacePowerUpCard> getPowerUpCard();
    public ArrayList<WeaponCardInterface> getWeaponCard();

    public PlayerInterface getPlayerInterface();
    //toString del player

}
