package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.PowerUpCard.PowerUpCardInterface;
import it.polimi.isw2019.Model.WeaponCard.AbstractWeaponCard;

import java.util.ArrayList;

public class GameBoard {

    private ArrayList<AbstractWeaponCard> weaponCards;
    private ArrayList<PowerUpCardInterface> powerUpCards;
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

    public AbstractWeaponCard takeWeaponCard (AbstractWeaponCard weaponCards, SquareSpawn squareSpawn){
        if (squareSpawn.containsWeapon(weaponCards)){
            squareSpawn.takeWeapon(weaponCards);
        }
        return weaponCards;
    }

    public PowerUpCardInterface takePowerUpCard (PowerUpCardInterface powerUpCard){
        powerUpCards.remove(powerUpCard);
        return powerUpCard;
    }

}
