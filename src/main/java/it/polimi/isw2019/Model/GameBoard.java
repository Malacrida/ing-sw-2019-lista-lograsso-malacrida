package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.AmmoTile.AmmoTile;
import it.polimi.isw2019.Model.PowerUpCard.PowerUpCard;
import it.polimi.isw2019.Model.WeaponCard.AbstractWeaponCard;
import it.polimi.isw2019.Model.WeaponCard.WeaponCard;

import java.util.ArrayList;

public class GameBoard {

    private ArrayList<WeaponCard> weaponCards;
    private ArrayList<PowerUpCard> powerUpCards;
    private Arena gameArena;
    private GameBoard instance;

    GameBoard (){

    }

    public GameBoard instanceGameBoard (){
        if (instance==null){
            instance= new GameBoard();
        }
        return instance;
    }

    public void chooseArena (int num){
        gameArena.instanceArena(num);
    }

    public WeaponCard takeWeaponCard (AbstractWeaponCard weaponCard, SquareSpawn squareSpawn){
        if (squareSpawn.containsWeapon(weaponCard)){
            squareSpawn.takeWeapon(weaponCard);
        }
        return weaponCard;
    }

    public PowerUpCard takePowerUpCard (PowerUpCard powerUpCard){
        powerUpCards.remove(powerUpCard);
        return powerUpCard;
    }

}
