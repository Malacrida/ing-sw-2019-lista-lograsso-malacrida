package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.AmmoTile.AmmoTile;
import it.polimi.isw2019.Model.Exception.AmmoTileUseException;
import it.polimi.isw2019.Model.Exception.EndWeaponCardException;
import it.polimi.isw2019.Model.Exception.InstanceArenaException;
import it.polimi.isw2019.Model.Exception.OutOfRangeException;
import it.polimi.isw2019.Model.PowerUpCard.PowerUpCard;
import it.polimi.isw2019.Model.WeaponCard.AbstractWeaponCard;



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


    public void chooseArena (int num) throws InstanceArenaException, OutOfRangeException{
        try {
            gameArena = Arena.instanceArena();
        }
        catch (InstanceArenaException e){
            throw new InstanceArenaException();
        }
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
    public void placeAnotherWeaponCards (int x, int y){
        gameArena.placeAnotherWeaponCardsOnSquareSpawn(weaponCards.get(weaponCards.size()), x,y);

    }

    public AbstractWeaponCard takeWeaponCard (AbstractWeaponCard weaponCard, int x, int y) throws EndWeaponCardException {
        if (gameArena.containsWeaponOnSpawnSquare(x,y, weaponCard)){
            gameArena.takeWeaponCardsOnSquareSpawn(weaponCard, x,y);
        }
        if (!weaponCards.isEmpty()) {
            placeAnotherWeaponCards(x,y);
        }
        else throw new EndWeaponCardException();
        return weaponCard;
    }

    public PowerUpCard takePowerUpCard (PowerUpCard powerUpCard){
        powerUpCards.remove(powerUpCard);
        return powerUpCard;
    }

    public void setUpAmmoTileOnArena (ArrayList<AmmoTile> ammoTileOnArena){
        gameArena.setAmmoTilesOnSquare(ammoTileOnArena);
    }

    public void placeAmmoTile (AmmoTile ammoTile, int x, int y){
        gameArena.placeAnotherAmmoTileOnSquare(ammoTile, x, y);
    }

    public AmmoTile pickUpAmmoTile (int x, int y) throws AmmoTileUseException {
        try {
            return gameArena.takeAmmoTileOnSquare(x,y);
        }
        catch (AmmoTileUseException e){
            throw new AmmoTileUseException();
        }
    }

    public ArrayList<Player> playersInOneSquare (int x, int y, Player player){
        return gameArena.playersInOneSquareOnArena(x,y,player);
    }

    public void insertPlayer (Player player, ColorRoom colorRoom){
        gameArena.spawnPlayer(colorRoom, player);
    }

    public boolean changePositionPlayer (Player player, int x, int y){
        return gameArena.movePlayer(player,x,y);
    }

}
