package it.polimi.isw2019.model;

import it.polimi.isw2019.model.ammotile.AmmoTile;
import it.polimi.isw2019.model.exception.InstanceArenaException;
import it.polimi.isw2019.model.powerupcard.PowerUpCard;
import it.polimi.isw2019.model.weaponcard.AbstractWeaponCard;
import it.polimi.isw2019.model.exception.AmmoTileUseException;
import it.polimi.isw2019.model.exception.OutOfBoundsException;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;


import java.util.ArrayList;

public class GameBoard implements GameBoardInterface{

    private ArrayList<AbstractWeaponCard> weaponCards= new ArrayList<>();
    //I colori indicano i punti di spawn
    private AbstractWeaponCard[] weaponCardsRed= new AbstractWeaponCard[3];
    private AbstractWeaponCard[] weaponCardsBlue= new AbstractWeaponCard[3];
    private AbstractWeaponCard[] weaponCardsYellow= new AbstractWeaponCard[3];
    private ArrayList<PowerUpCard> powerUpCards;
    private ArrayList<AmmoTile> ammoTiles;
    private Arena gameArena=null;
    private static GameBoard instance;
    private KillShotTrack killShotTrack;

    public GameBoard (){

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


    public AbstractWeaponCard[] createDeckForSpawnSquares (){
        AbstractWeaponCard[] deck = new AbstractWeaponCard[3];
        for (int i =0; i<3;i++){
            deck[i]= weaponCards.get(0);
            deck[i].changeState(StateCard.ON_BOARD);
            weaponCards.remove(0);
        }
        return deck;
    }

    //settare le carte nei punti spawn
    public void setWeaponCardsOnBoard (){

        weaponCardsRed = createDeckForSpawnSquares();
        weaponCardsBlue = createDeckForSpawnSquares();
        weaponCardsYellow = createDeckForSpawnSquares();

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

    public AbstractWeaponCard[] weaponCardsOnSquares (int x, int y)throws OutOfBoundsException {
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
    public ArrayList<Player> playersWhoCanSee(Player player){
        return gameArena.playerWhoSeeOnArena(player);
    }

    public void insertPlayer (Player player, ColorRoom colorRoom){
        gameArena.spawnPlayer(colorRoom, player);
    }

    public void changePositionPlayer(Player player, int x, int y){
        gameArena.movePlayer(player,x,y);
    }

    public boolean isSquareAvailableOnArena(Player player, int x, int y){
        return gameArena.isSquaresAvailable(player,x,y);
    }

    public Arena getGameArena() {
        return gameArena;
    }


    public KillShotTrack getKillShotTrack() {
        return killShotTrack;
    }

    public void setKillShotTrack(KillShotTrack killShotTrack) {
        this.killShotTrack = killShotTrack;
    }

    @Override
    public GameBoardInterface getGameBoardInterface() {
        return this;
    }

    @Override
    public ArenaInterface getArenaInterface() {
        return  gameArena.getArenaInterface();
    }

    @Override
    public WeaponCardInterface getWeaponCard(ColorCube color, int index) {
        if(color.equals(ColorCube.BLUE))
            return weaponCardsBlue[index].getWeaponCard();
        else if(color.equals(ColorCube.RED))
            return weaponCardsRed[index].getWeaponCard();
        else
            return weaponCardsYellow[index].getWeaponCard();
    }

    public AbstractWeaponCard[] getWeaponCardsRed() {
        return weaponCardsRed;
    }

    public AbstractWeaponCard[] getWeaponCardsBlue() {
        return weaponCardsBlue;
    }

    public AbstractWeaponCard[] getWeaponCardsYellow() {
        return weaponCardsYellow;
    }

    @Override
    public ArrayList<WeaponCardInterface> getWeaponCard(ColorCube color) {
        ArrayList<WeaponCardInterface> tmpWeaponCards = new ArrayList<>();

        if(color.equals(ColorCube.BLUE)){
            for(AbstractWeaponCard weaponCard: weaponCardsBlue){
                tmpWeaponCards.add(weaponCard.getWeaponCard());
            }
        }
        else if(color.equals(ColorCube.RED)){
            for(AbstractWeaponCard weaponCard : weaponCardsRed)
                tmpWeaponCards.add(weaponCard.getWeaponCard());
        }
        else if(color.equals(ColorCube.YELLOW)) {
            for (AbstractWeaponCard weaponCard : weaponCardsYellow)
                tmpWeaponCards.add(weaponCard.getWeaponCard());
        }

        return tmpWeaponCards;
    }
}
