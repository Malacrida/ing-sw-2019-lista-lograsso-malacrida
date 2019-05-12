package it.polimi.isw2019.Server.Model;

import it.polimi.isw2019.Server.Model.AmmoTile.AmmoTile;
import it.polimi.isw2019.Server.Model.Exception.InstanceArenaException;
import it.polimi.isw2019.Server.Model.PowerUpCard.PowerUpCard;
import it.polimi.isw2019.Server.Model.WeaponCard.AbstractWeaponCard;
import it.polimi.isw2019.Server.Model.Exception.AmmoTileUseException;
import it.polimi.isw2019.Server.Model.Exception.OutOfBoundsException;


import java.util.ArrayList;

public class GameBoard {

    private ArrayList<AbstractWeaponCard> weaponCards= new ArrayList<>();
    //I colori indicano i punti di spawn
    private ArrayList<AbstractWeaponCard> weaponCardsRed= new ArrayList<>();
    private ArrayList<AbstractWeaponCard> weaponCardsBlue= new ArrayList<>();
    private ArrayList<AbstractWeaponCard> weaponCardsYellow= new ArrayList<>();
    private ArrayList<PowerUpCard> powerUpCards;
    private ArrayList<AmmoTile> ammoTiles;
    private Arena gameArena=null;
    private static GameBoard instance;

    GameBoard (){

    }




    public void chooseArena (int num) throws InstanceArenaException, OutOfBoundsException {

        gameArena= new Arena();
        try {
            gameArena.chooseArena(num);
        }
        catch (OutOfBoundsException e){
            throw new OutOfBoundsException ();
        }


    }

    public void setWeaponCards(ArrayList<AbstractWeaponCard> weaponCards) {
        this.weaponCards = weaponCards;
    }

    public void setPowerUpCards(ArrayList<PowerUpCard> powerUpCards) {
        this.powerUpCards = powerUpCards;
    }

    public void setAmmoTiles(ArrayList<AmmoTile> ammoTiles) {
        this.ammoTiles = ammoTiles;
    }

    //settare le carte nei punti spawn
    public void setWeaponCardsOnBoard (){
        //I colori indicano i punti di spawn
        for (int i=0; i<9; i++){
            if (i%3==0){
                weaponCardsRed.add(weaponCards.get(0));
                //cambio di stato
                weaponCards.remove(0);
            }
            if (i%3==1){
                weaponCardsBlue.add(weaponCards.get(0));
                //cambio di stato
                weaponCards.remove(0);
            }
            if (i%3==2){
                weaponCardsYellow.add(weaponCards.get(0));
                //cambio di stato
                weaponCards.remove(0);
            }
        }
        gameArena.setWeaponsCardOnSquareSpawn(weaponCardsRed,weaponCardsBlue,weaponCardsYellow);
    }

    //rimpiazzare le carte armi pescate dai punti spawn
    public void placeAnotherWeaponCards (int x, int y){
        gameArena.placeAnotherWeaponCardsOnSquareSpawn(weaponCards.get(0), x,y);
        weaponCards.remove(weaponCards.get(0));

    }

    public AbstractWeaponCard takeWeaponCard (AbstractWeaponCard weaponCard, int x, int y) throws OutOfBoundsException {
        if (gameArena.containsWeaponOnSpawnSquare(x,y, weaponCard)){
            gameArena.takeWeaponCardsOnSquareSpawn(weaponCard, x,y);
        }
        else throw new OutOfBoundsException("Card not present");
        if (!weaponCards.isEmpty()) {
            placeAnotherWeaponCards(x,y);
        }
        //else throw new EndWeaponCardException();
        return weaponCard;
    }

    public ArrayList<AbstractWeaponCard> weaponCardsOnSquares (int x, int y)throws OutOfBoundsException {
        if ((x==1 && y==0)|| (x==0 && y==2)|| (x==2 && y==3)){
            return gameArena.getWeaponCardsOnSquares(x,y);
        }
        else throw new OutOfBoundsException("Non hai selezionato una casella di spawn");
    }

    public int sizeWeaponCards (){
        return weaponCards.size();
    }

    public PowerUpCard takePowerUpCard (){
        PowerUpCard powerUpCard = powerUpCards.get(0);
        powerUpCards.remove(0);
        return powerUpCard;
    }

    public int sizePowerUpCards (){
        return powerUpCards.size();
    }

    public void setUpAmmoTileOnArena (int numOfArena)throws OutOfBoundsException{
        ArrayList<AmmoTile> ammoTilesOnArena = new ArrayList<>();

        if (numOfArena==1 ||numOfArena==2){
            for (int i=0; i<8; i++){
                ammoTilesOnArena.add(ammoTiles.get(0));
                ammoTiles.remove(0);
            }
        }
        else if(numOfArena==3){
            for (int i=0; i<7; i++){
                ammoTilesOnArena.add(ammoTiles.get(0));
                ammoTiles.remove(0);
            }
        }
        else if(numOfArena==4){
            for (int i=0; i<9; i++){
                ammoTilesOnArena.add(ammoTiles.get(0));
                ammoTiles.remove(0);
            }
        }
        else throw new OutOfBoundsException();

        gameArena.setAmmoTilesOnSquare(ammoTilesOnArena);
    }

    public void placeAmmoTile (AmmoTile ammoTile, int x, int y){
        gameArena.placeAnotherAmmoTileOnSquare(ammoTile, x, y);
    }

    public int sizeAmmoTiles (){
        return ammoTiles.size();
    }

    public AmmoTile pickUpAmmoTile (int x, int y) throws AmmoTileUseException {
        try {
            return gameArena.takeAmmoTileOnSquare(x,y);
        }
        catch (AmmoTileUseException e){
            throw new AmmoTileUseException();
        }
    }

    public AmmoTile getAmmoTileOnSquare (int x, int y){
       return gameArena.getAmmoTileOnSquare(x,y);
    }

    public boolean useAmmoTileOnSquare(int x, int y){
        return gameArena.useAmmoTileOnSquare(x,y);
    }

    //Player who are in one square
    public ArrayList<Player> playersInOneSquare (int x, int y, Player player){
        return gameArena.playersInOneSquareOnArena(x,y,player);
    }

    //Player who can see on arena by player who attack
    public ArrayList<Player> playersWhoCanSee (int x, int y, Player player){
        return gameArena.playerWhoSeeOnArena(x,y,player);
    }

    public void insertPlayer (Player player, ColorRoom colorRoom){
        gameArena.spawnPlayer(colorRoom, player);
    }

    public boolean changePositionPlayer (Player player, int x, int y, boolean teleporter){
        return gameArena.movePlayer(player,x,y, teleporter);
    }

}
