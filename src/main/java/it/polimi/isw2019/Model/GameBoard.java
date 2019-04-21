package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.AmmoTile.AmmoTile;
import it.polimi.isw2019.Model.Exception.EndWeaponCard;
import it.polimi.isw2019.Model.Exception.OutOfRangeException;
import it.polimi.isw2019.Model.PowerUpCard.PowerUpCard;
import it.polimi.isw2019.Model.WeaponCard.AbstractWeaponCard;
import it.polimi.isw2019.Model.WeaponCard.WeaponCard;

import java.util.ArrayList;

public class GameBoard {

    private ArrayList<AbstractWeaponCard> weaponCards= new ArrayList<>();
    //I colori indicano i punti di spawn
    private ArrayList<AbstractWeaponCard> weaponCardsRed= new ArrayList<>();
    private ArrayList<AbstractWeaponCard> weaponCardsBlue= new ArrayList<>();
    private ArrayList<AbstractWeaponCard> weaponCardsYellow= new ArrayList<>();
    private ArrayList<PowerUpCard> powerUpCards;
    private Arena gameArena=null;
    private static GameBoard instance;

    GameBoard (){

    }

    public static GameBoard instanceGameBoard (){
        if (instance==null){
            instance= new GameBoard();
        }
        return instance;
    }

    public void chooseArena (int num) throws OutOfRangeException{
        gameArena.instanceArena();
        try {
            gameArena.chooseArena(num);
        }
        catch (OutOfRangeException e){
            throw new OutOfRangeException ();
        }

    }

    public void setWeaponCards(ArrayList<AbstractWeaponCard> weaponCards) {
        this.weaponCards = weaponCards;
    }

    public void setPowerUpCards(ArrayList<PowerUpCard> powerUpCards) {
        this.powerUpCards = powerUpCards;
    }

    //settare le carte nei punti spawn
    public void setWeaponCardsOnBoard (){
        //I colori indicano i punti di spawn
        for (int i=0; i<9; i++){
            if (i%3==0){
                weaponCardsRed.add(weaponCards.get(weaponCards.size()));
                //cambio di stato
                weaponCards.remove(weaponCards.size());
            }
            if (i%3==1){
                weaponCardsBlue.add(weaponCards.get(weaponCards.size()));
                //cambio di stato
                weaponCards.remove(weaponCards.size());
            }
            if (i%3==2){
                weaponCardsYellow.add(weaponCards.get(weaponCards.size()));
                //cambio di stato
                weaponCards.remove(weaponCards.size());
            }
        }
        gameArena.setWeaponsCardOnSquareSpawn(weaponCardsRed,weaponCardsBlue,weaponCardsBlue);
    }

    //rimpiazzare le carte armi pescate dai punti spawn
    public void placeAnotherWeaponCards (Position position){
        gameArena.placeAnotherWeaponCardsOnSquareSpawn(weaponCards.get(weaponCards.size()), position);

    }

    public WeaponCard takeWeaponCard (AbstractWeaponCard weaponCard, Position playerPosition) throws EndWeaponCard {
        if (gameArena.containsWeaponOnSpawnSquare(playerPosition, weaponCard)){
            gameArena.takeWeaponCardsOnSquareSpawn(weaponCard, playerPosition);
        }
        if (!weaponCards.isEmpty()) {
            placeAnotherWeaponCards(playerPosition);
        }
        else throw new EndWeaponCard();
        return weaponCard;
    }

    public PowerUpCard takePowerUpCard (PowerUpCard powerUpCard){
        powerUpCards.remove(powerUpCard);
        return powerUpCard;
    }

}
