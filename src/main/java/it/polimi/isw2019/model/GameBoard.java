package it.polimi.isw2019.model;

import it.polimi.isw2019.model.ammotile.AmmoTile;
import it.polimi.isw2019.model.exception.InstanceArenaException;
import it.polimi.isw2019.model.powerupcard.PowerUpCard;
import it.polimi.isw2019.model.weaponcard.*;
import it.polimi.isw2019.model.exception.AmmoTileUseException;
import it.polimi.isw2019.model.exception.OutOfBoundsException;
import it.polimi.isw2019.utilities.Database;


import java.util.ArrayList;

public class GameBoard implements GameBoardInterface {

    private ArrayList<AbstractWeaponCard> weaponCards = new ArrayList<>();
    //I colori indicano i punti di spawn

    private AbstractWeaponCard[] weaponCardsRed = new AbstractWeaponCard[3];
    private AbstractWeaponCard[] weaponCardsBlue = new AbstractWeaponCard[3];
    private AbstractWeaponCard[] weaponCardsYellow = new AbstractWeaponCard[3];

    private ArrayList<AbstractWeaponCard> weaponCardsDischarged = new ArrayList<>();

    private ArrayList<PowerUpCard> powerUpCards = new ArrayList<>();
    private ArrayList<PowerUpCard> usedPowerUpCards = new ArrayList<>();

    private ArrayList<AmmoTile> ammoTiles;
    private ArrayList<AmmoTile> grabedAmmoTiles = new ArrayList<>();

    private Arena gameArena;
    private static GameBoard instance;
    private KillShotTrack killShotTrack;

    private String gameBoardDescription;

    public GameBoard() {
        //shuffle
        weaponCards.add(new CyberBlade());
        weaponCards.add(new Electroscythe());
        weaponCards.add(new Flamethrower());
        weaponCards.add(new Furnace());
        weaponCards.add(new GrenadeLauncher());
        weaponCards.add(new HeatSeeker());
        weaponCards.add(new Hellion());
        weaponCards.add(new LockRifle());
        weaponCards.add(new MachineGun());
        weaponCards.add(new PlasmaGun());
        weaponCards.add(new PowerGlove());
        weaponCards.add(new RailGun());
        weaponCards.add(new RocketLauncher());
        weaponCards.add(new ShockWave());
        weaponCards.add(new ShotGun());
        weaponCards.add(new SledgeHammer());
        weaponCards.add(new Thor());
        weaponCards.add(new TractorBeam());
        weaponCards.add(new VortexCannon());
        weaponCards.add(new Whisper());
        weaponCards.add(new ZX_2());

        gameArena = new Arena();

    }


    /**
     * method to choose arena
     *
     * @param num arena's identifier
     * @throws InstanceArenaException
     * @throws OutOfBoundsException
     */
    public void chooseArena(int num) throws InstanceArenaException, OutOfBoundsException {

        try {
            gameArena.chooseArena(num);
        } catch (OutOfBoundsException e) {
            throw new OutOfBoundsException();
        }


    }

    /**
     * set weapon cards
     *
     * @param weaponCards
     */

    public void setWeaponCards(ArrayList<AbstractWeaponCard> weaponCards) {
        this.weaponCards = weaponCards;
    }

    /**
     * set power up
     *
     * @param powerUpCards
     */

    public void setPowerUpCards(ArrayList<PowerUpCard> powerUpCards) {
        this.powerUpCards = powerUpCards;
    }

    /**
     * set ammo tile
     *
     * @param ammoTiles
     */

    public void setAmmoTiles(ArrayList<AmmoTile> ammoTiles) {
        this.ammoTiles = ammoTiles;
    }

    /**
     * creeate weapon card deck
     *
     * @return deck
     */

    public AbstractWeaponCard[] createDeckForSpawnSquares() {
        AbstractWeaponCard[] deck = new AbstractWeaponCard[3];
        for (int i = 0; i < 3; i++) {
            deck[i] = weaponCards.get(0);
            deck[i].changeState(StateCard.ON_BOARD);
            weaponCards.remove(0);
        }
        return deck;
    }

    /**
     * put weapon cad on board
     */

    //settare le carte nei punti spawn
    public void setWeaponCardsOnBoard() {

        weaponCardsRed = createDeckForSpawnSquares();
        weaponCardsBlue = createDeckForSpawnSquares();
        weaponCardsYellow = createDeckForSpawnSquares();
        gameArena.setWeaponsCardOnSquareSpawn(weaponCardsRed, weaponCardsBlue, weaponCardsYellow);
    }

    /**
     * put another weapon card when one is grab
     *
     * @param x first coordinate
     * @param y second  cordinate
     */

    //rimpiazzare le carte armi pescate dai punti spawn
    public void placeAnotherWeaponCards(int x, int y) {
        gameArena.placeAnotherWeaponCardsOnSquareSpawn(weaponCards.get(0), x, y);
        weaponCards.remove(weaponCards.get(0));

    }


    public AbstractWeaponCard takeWeaponCardFromSpawn(ColorRoom colorSpawn, int index) {
        if (colorSpawn.equals(ColorRoom.YELLOW)) {
            return weaponCardsYellow[index];
        } else if (colorSpawn.equals(ColorRoom.RED)) {
            return weaponCardsRed[index];
        } else {
            return weaponCardsBlue[index];
        }
    }

    /**
     * take a weapon card from gameboard
     *
     * @param weaponCard which player want grab
     * @param x          first coordinate
     * @param y          second coordinate
     * @return weapon card
     * @throws OutOfBoundsException
     */
    public AbstractWeaponCard takeWeaponCard(AbstractWeaponCard weaponCard, int x, int y) throws OutOfBoundsException {
        if (gameArena.containsWeaponOnSpawnSquare(x, y, weaponCard)) {
            gameArena.takeWeaponCardsOnSquareSpawn(weaponCard, x, y);
        } else throw new OutOfBoundsException("Card not present");
        if (!weaponCards.isEmpty()) {
            placeAnotherWeaponCards(x, y);
        }
        //else throw new EndWeaponCardException();
        return weaponCard;
    }

    /**
     * list of weapon cards in a square
     *
     * @param x first coordinate
     * @param y second cooridnate
     * @return weapon cards
     * @throws OutOfBoundsException
     */

    public AbstractWeaponCard[] weaponCardsOnSquares(int x, int y) throws OutOfBoundsException {
        if ((x == 1 && y == 0) || (x == 0 && y == 2) || (x == 2 && y == 3)) {
            return gameArena.getWeaponCardsOnSquares(x, y);
        } else throw new OutOfBoundsException("not a spawn square!");
    }

    /**
     * lenght of weapon card's array
     *
     * @return size of weapon card
     */

    public int sizeWeaponCards() {
        return weaponCards.size();
    }

    /**
     * take power up card
     *
     * @return power up
     */

    public PowerUpCard takePowerUpCard() {
        PowerUpCard powerUpCard = powerUpCards.get(0);
        powerUpCards.remove(0);
        return powerUpCard;
    }

    /**
     * lenght of power up card's array
     *
     * @return size of power up card
     */

    public int sizePowerUpCards() {
        return powerUpCards.size();
    }

    /**
     * setter ammo tile on arena
     *
     * @param numOfArena arena's identifier
     * @throws OutOfBoundsException exception
     */

    public void setUpAmmoTileOnArena(int numOfArena) throws OutOfBoundsException {
        ArrayList<AmmoTile> ammoTilesOnArena = new ArrayList<>();

        if (numOfArena == 1 || numOfArena == 2) {
            for (int i = 0; i < 8; i++) {
                ammoTilesOnArena.add(ammoTiles.get(0));
                ammoTiles.remove(0);
            }
        } else if (numOfArena == 3) {
            for (int i = 0; i < 7; i++) {
                ammoTilesOnArena.add(ammoTiles.get(0));
                ammoTiles.remove(0);
            }
        } else if (numOfArena == 4) {
            for (int i = 0; i < 9; i++) {
                ammoTilesOnArena.add(ammoTiles.get(0));
                ammoTiles.remove(0);
            }
        } else {
            System.out.println(" ko ammo 1 ");
            throw new OutOfBoundsException();
        }


        gameArena.setAmmoTilesOnSquare(ammoTilesOnArena);
    }

    public void placeAmmoTile(int x, int y) {
        gameArena.placeAnotherAmmoTileOnSquare(ammoTiles.get(0), x, y);
        ammoTiles.remove(0);
        /**
         * position of ammotile
         * @param ammoTile card
         * @param x first coordinate
         * @param y second coordinate
         */
    }

        public void placeAmmoTile (AmmoTile ammoTile,int x, int y){
            gameArena.placeAnotherAmmoTileOnSquare(ammoTile, x, y);
        }

        /**
         * size ammo tile deck
         * @return
         */

        public int sizeAmmoTiles () {
            return ammoTiles.size();
        }

        /**
         *
         * @param x
         * @param y
         * @return
         * @throws AmmoTileUseException
         */

        public AmmoTile pickUpAmmoTile ( int x, int y) throws AmmoTileUseException {
            try {
                AmmoTile ammoTile = gameArena.takeAmmoTileOnSquare(x, y);
                System.out.println(ammoTile.toString());
                return ammoTile;
            } catch (AmmoTileUseException e) {
                throw new AmmoTileUseException();
            }
        }

        /**
         * getter method for a specific ammo tile
         * @param x first coordinate
         * @param y second coordinate
         * @return ammotile
         */

        public AmmoTile getAmmoTileOnSquare ( int x, int y){
            return gameArena.getAmmoTileOnSquare(x, y);
        }

        /**
         * method to use ammo tile
         * @param x first coordinate
         * @param y second coordinate
         * @return method to use ammotile
         */

        public boolean useAmmoTileOnSquare ( int x, int y){
            return gameArena.useAmmoTileOnSquare(x, y);
        }

        /**
         * Player who are in one square
         * @param x first coordinate
         * @param y second coordinate
         * @param player player in that square
         * @return method to see list of players in that square
         */

        public ArrayList<Player> playersInOneSquare ( int x, int y, Player player){
            return gameArena.playersInOneSquareOnArena(x, y, player);
        }

        /**
         * get list of visible players
         * @param player players that attacker can see
         * @return list of visible players
         */

        //Player who can see on arena by player who attack
        public ArrayList<Player> playersWhoCanSee (Player player){
            return gameArena.playerWhoSeeOnArena(player);
        }

        /**
         * spawn a player in a square spawn
         * @param player who want spawn
         * @param colorRoom spawn color room
         */

        public void insertPlayer (Player player, ColorRoom colorRoom){
            gameArena.spawnPlayer(colorRoom, player);
        }

        /**
         * move player
         * @param player who want change position
         * @param x first coordinate
         * @param y second coordinate
         */

        public void changePositionPlayer (Player player,int x, int y){
            gameArena.movePlayer(player, x, y);
        }

        /**
         * boolean to see if that square is available for that player
         * @param player who want know if that square is available
         * @param x first coordinate
         * @param y second coordinate
         * @return boolean
         */

        public boolean isSquareAvailableOnArena (Player player,int x, int y){
            return gameArena.isSquaresAvailable(player, x, y);
        }

        /**
         * get arena
         * @return arena
         */

        public Arena getGameArena () {
            return gameArena;
        }

        /**
         * getter of kill shot track
         * @return kill shot track
         */

        public KillShotTrack getKillShotTrack () {
            return killShotTrack;
        }

        /**
         * setter of kill shot track
         * @param killShotTrack
         */

        public void setKillShotTrack (KillShotTrack killShotTrack){
            this.killShotTrack = killShotTrack;
        }

        @Override
        public GameBoardInterface getGameBoardInterface () {
            return this;
        }

        @Override
        public ArenaInterface getArenaInterface () {
            return gameArena.getArenaInterface();
        }

        @Override
        public WeaponCardInterface getWeaponCard (ColorCube color,int index){

            if (color.equals(ColorCube.BLUE))
                return weaponCardsBlue[index].getWeaponCard();
            else if (color.equals(ColorCube.RED))
                return weaponCardsRed[index].getWeaponCard();
            else
                return weaponCardsYellow[index].getWeaponCard();
        }

        public AbstractWeaponCard[] getWeaponCardsRed () {
            return weaponCardsRed;
        }

        public AbstractWeaponCard[] getWeaponCardsBlue () {
            return weaponCardsBlue;
        }

        public AbstractWeaponCard[] getWeaponCardsYellow () {
            return weaponCardsYellow;
        }

        @Override
        public ArrayList<WeaponCardInterface> getWeaponCard (ColorRoom color){
            ArrayList<WeaponCardInterface> tmpWeaponCards = new ArrayList<>();
            System.out.println("color " + color.getColorRoomRepresentation());
            if (color.equals(ColorRoom.BLUE)) {
                for (AbstractWeaponCard weaponCard : weaponCardsBlue) {
                    tmpWeaponCards.add(weaponCard.getWeaponCard());
                }
            } else if (color.equals(ColorRoom.RED)) {
                System.out.println("ok red");
                for (AbstractWeaponCard weaponCard : weaponCardsRed)
                    tmpWeaponCards.add(weaponCard.getWeaponCard());
            } else if (color.equals(ColorRoom.YELLOW)) {
                for (AbstractWeaponCard weaponCard : weaponCardsYellow)
                    tmpWeaponCards.add(weaponCard.getWeaponCard());
            }

            //System.out.println("size : " + tmpWeaponCards.size());
            return tmpWeaponCards;
        }


        public void addPowerUpCardDiscarded (PowerUpCard powerUpCard){
            usedPowerUpCards.add(powerUpCard);
        }
        public void addAmmoTileDiscarded (AmmoTile ammoTile){
            grabedAmmoTiles.add(ammoTile);
        }


    /*public void setGameBoardDescription(){
        getGameArena().setArenaRepresentation();
        gameBoardDescription = getGameArena().getArenaRepresentation();
        gameBoardDescription += "Red Spawn : ";
       // StringBuilder st = new StringBuilder();
        //ci penso
        for(int i = 0 ; i < weaponCardsRed.length; i++)
            gameBoardDescription += weaponCardsRed[i].getName() + " ";
        gameBoardDescription += "\n";
        gameBoardDescription += "Blue Spawn : ";
        for(int i = 0 ; i < weaponCardsBlue.length; i++)
            gameBoardDescription += weaponCardsBlue[i].getName() + " ";
        gameBoardDescription += "\n";
        gameBoardDescription += "Yellow Spawn : ";
        for(int i = 0 ; i < weaponCardsYellow.length; i++)
            gameBoardDescription += weaponCardsYellow[i].getName() + " ";
        gameBoardDescription += "\n";
        //introdurre anche i vari danni
        gameBoardDescription += "Num Skull : "+ killShotTrack.getNumSkull() + "\n";


    }*/

        public String getGameBoardDescription () {
            return gameBoardDescription;
        }

    }

