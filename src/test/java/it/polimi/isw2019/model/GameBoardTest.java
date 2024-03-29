package it.polimi.isw2019.model;

import it.polimi.isw2019.model.ammotile.AmmoTile;
import it.polimi.isw2019.model.exception.AmmoTileUseException;
import it.polimi.isw2019.model.exception.InstanceArenaException;
import it.polimi.isw2019.model.exception.OutOfBoundsException;
import it.polimi.isw2019.model.powerupcard.PowerUpCard;
import it.polimi.isw2019.model.weaponcard.*;
import it.polimi.isw2019.utilities.Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameBoardTest {

    GameBoard gameBoard;
    GameBoard gameBoard2;

    AbstractWeaponCard weaponCard1;
    AbstractWeaponCard weaponCard2;
    AbstractWeaponCard weaponCard3;
    AbstractWeaponCard weaponCard4;
    AbstractWeaponCard weaponCard5;
    AbstractWeaponCard weaponCard6;
    AbstractWeaponCard weaponCard7;
    AbstractWeaponCard weaponCard8;
    AbstractWeaponCard weaponCard9;
    AbstractWeaponCard anotherWeaponCard;
    ArrayList<AbstractWeaponCard> weaponCards= new ArrayList<>();


    AmmoTile ammoTile;
    ArrayList<AmmoTile> ammoTiles= new ArrayList<>();

    PowerUpCard powerUpCard;
    ArrayList<PowerUpCard> powerUpCards = new ArrayList<>();

    Player player;

    @Before
    public void setUp() throws Exception {


        weaponCard1= new Electroscythe();
        weaponCard2= new GrenadeLauncher();
        weaponCard3= new PlasmaGun();
        weaponCard4= new GrenadeLauncher();
        weaponCard5= new HeatSeeker();
        weaponCard6= new GrenadeLauncher();
        weaponCard7= new PowerGlove();
        weaponCard8= new Electroscythe();
        weaponCard9= new GrenadeLauncher();
        anotherWeaponCard= new CyberBlade();

        weaponCards.add(weaponCard1);
        weaponCards.add(weaponCard2);
        weaponCards.add(weaponCard3);
        weaponCards.add(weaponCard4);
        weaponCards.add(weaponCard5);
        weaponCards.add(weaponCard6);
        weaponCards.add(weaponCard7);
        weaponCards.add(weaponCard8);
        weaponCards.add(weaponCard9);



        try {
            gameBoard = new GameBoard();
            Database db = new Database();
            gameBoard.chooseArena(4);
            gameBoard.setPowerUpCards(db.loadPowerUpCards());
            ArrayList<AmmoTile> deckAmmoTiles = db.loadAmmoTiles();
            gameBoard.setAmmoTiles(deckAmmoTiles);
            gameBoard.setUpAmmoTileOnArena(4);
            for (int i=0; i<12; i++){
                ammoTiles.add(deckAmmoTiles.get(i));
            }


            powerUpCards = db.loadPowerUpCards();

        } catch (OutOfBoundsException | InstanceArenaException e) {
            fail();
        }



        player = new Player("name", "Comment");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSetWeaponCards() {

        gameBoard.setWeaponCards(weaponCards);
        assert (gameBoard.sizeWeaponCards()==9);
    }

    @Test
    public void testSetPowerUpCards() {

        gameBoard.setPowerUpCards(powerUpCards);
        assertEquals  (24, gameBoard.sizePowerUpCards());
    }

    @Test
    public void testSetAmmoTiles() {
        gameBoard.setAmmoTiles(ammoTiles);
        assert (gameBoard.sizeAmmoTiles()==ammoTiles.size());
    }

    @Test
    public void testSetWeaponCardsOnBoard() {
        ArrayList <AbstractWeaponCard> weaponCards1= weaponCards;

        gameBoard.setWeaponCards(weaponCards1);
        gameBoard.setWeaponCardsOnBoard();
        assertEquals (0, gameBoard.sizeWeaponCards());
        try {
            AbstractWeaponCard[] weaponCardsRed =gameBoard.weaponCardsOnSquares(1,0);

            assertEquals(weaponCard1,weaponCardsRed[0]);
            assertEquals(weaponCard2,weaponCardsRed[1]);
            assertEquals(weaponCard3,weaponCardsRed[2]);

            AbstractWeaponCard[] weaponCardsBlue =gameBoard.weaponCardsOnSquares(0,2);

            assertEquals(weaponCard4,weaponCardsBlue[0]);
            assertEquals(weaponCard5,weaponCardsBlue[1]);
            assertEquals(weaponCard6,weaponCardsBlue[2]);


            AbstractWeaponCard [] weaponCardsYellow =gameBoard.weaponCardsOnSquares(2,3);

            assertEquals(weaponCard7,weaponCardsYellow[0]);
            assertEquals(weaponCard8,weaponCardsYellow[1]);
            assertEquals(weaponCard9,weaponCardsYellow[2]);
        }
        catch (OutOfBoundsException e){
            fail();
        }

        try {
            AbstractWeaponCard[] square = gameBoard.weaponCardsOnSquares(3,1);
            assertNull(square);
            fail();
        }catch (OutOfBoundsException e){

        }
    }


    @Test
    public void testTakeWeaponCard() {
        ArrayList <AbstractWeaponCard> weaponCards1= weaponCards;
        weaponCards1.add(anotherWeaponCard);
        assert (weaponCards1.size()==10);

        gameBoard.setWeaponCards(weaponCards1);
        assert (gameBoard.sizeWeaponCards()==10);
        gameBoard.setWeaponCardsOnBoard();
        assert (gameBoard.sizeWeaponCards()==1);


        try {
            gameBoard.takeWeaponCard(weaponCard7,2,3);
        }
        catch (OutOfBoundsException e){
            fail();
        }
        try {
            AbstractWeaponCard[] tempCard  = gameBoard.weaponCardsOnSquares(2,3);
            ArrayList <AbstractWeaponCard> weaponCardsPresent = new ArrayList<>();
            weaponCardsPresent.add(tempCard[0]);
            weaponCardsPresent.add(tempCard[1]);

            assertTrue(weaponCardsPresent.contains(anotherWeaponCard));
            assertFalse(weaponCardsPresent.contains(weaponCard3));
        }
        catch (OutOfBoundsException  e){

            fail();
        }

        try {
            gameBoard.takeWeaponCard(weaponCard1,1,0);
            gameBoard.takeWeaponCard(weaponCard4,1,0);
            gameBoard.takeWeaponCard(weaponCard7,1,0);

            AbstractWeaponCard[] tempCard = gameBoard.weaponCardsOnSquares(1,0);
            ArrayList <AbstractWeaponCard> weaponCardsPresent = new ArrayList<>();
            weaponCardsPresent.add(tempCard[0]);
            weaponCardsPresent.add(tempCard[1]);
            weaponCardsPresent.add(tempCard[2]);
            assertFalse(weaponCardsPresent.contains(weaponCard1));
            assertFalse(weaponCardsPresent.contains(weaponCard4));
            assertFalse(weaponCardsPresent.contains(weaponCard7));
            gameBoard.takeWeaponCard(weaponCard1,1,0);
            fail();
        }
        catch (OutOfBoundsException e){

        }

    }

    @Test
    public void testTakePowerUpCard() {
        gameBoard.setPowerUpCards(powerUpCards);
        PowerUpCard powerUpCardDrawn = gameBoard.takePowerUpCard();
        assertEquals (23,gameBoard.sizePowerUpCards());
    }

    @Test
    public void testSetUpAmmoTileOnArena() {

        gameBoard.setAmmoTiles(ammoTiles);


        try {
            gameBoard.setUpAmmoTileOnArena(4);
            assert(gameBoard.sizeAmmoTiles()==3);
        }
        catch (OutOfBoundsException e){
            fail();
        }

        try {
            gameBoard.setUpAmmoTileOnArena(7);
            fail();
        }
        catch (OutOfBoundsException e){

        }
    }

    @Test
    public void testPlaceAmmoTile() {
        gameBoard.placeAmmoTile(0,0);
        assertTrue (gameBoard.useAmmoTileOnSquare(0,0));
    }

    @Test
    public void testPickUpAmmoTile() {
        AmmoTile ammoTileTaken;
        gameBoard.setAmmoTiles(ammoTiles);


        try {
            gameBoard.setUpAmmoTileOnArena(4);
            assert(gameBoard.sizeAmmoTiles()==3);
            assertTrue (gameBoard.useAmmoTileOnSquare(1,2));
        }
        catch (OutOfBoundsException e){
            fail();
        }

        try {

            ammoTileTaken=gameBoard.pickUpAmmoTile(1,2);
            assertFalse (gameBoard.useAmmoTileOnSquare(1,2));
            assertNotNull(ammoTileTaken);


        }
        catch (AmmoTileUseException e){
            fail();
        }


        try {
            gameBoard.pickUpAmmoTile(1,0);
            fail();
        }
        catch (AmmoTileUseException e){
            assertFalse (gameBoard.useAmmoTileOnSquare(1,2));
        }


    }


    @Test
    public void testInsertPlayer() {

        gameBoard.insertPlayer(player, ColorRoom.RED);
        ArrayList<Player> players= gameBoard.playersInOneSquare(1,0,null);
        assertTrue(players.contains(player));
    }

    @Test
    public void testChangePositionPlayer() {

        gameBoard.insertPlayer(player,ColorRoom.BLUE);
        player.changePosition(0,2, ColorRoom.BLUE);
        ArrayList <Player> playersInPreviousPosition =  gameBoard.playersInOneSquare(0,2, null);
        assertTrue(playersInPreviousPosition.contains(player));
        gameBoard.changePositionPlayer(player,0,1);
        player.changePosition(0,1,ColorRoom.BLUE);
        ArrayList <Player> playersInOneSquare = gameBoard.playersInOneSquare(0,1, null);
        assertTrue(playersInOneSquare.contains(player));
        playersInPreviousPosition =  gameBoard.playersInOneSquare(0,2, null);
        assertFalse(playersInPreviousPosition.contains(player));

    }

    @Test

    public void addPowerUpDiscarded(){

    }

    @Test
    public void cancelKillPlayerFromOverKill(){
        ArrayList<Player> kill = gameBoard.getKillPlayer();
        ArrayList<Player> overKill = gameBoard.getOverkillPlayer();
        gameBoard.addKillPlayer(player);
        gameBoard.addOverKillPlayer(player);
        gameBoard.cancelKillPlayerFromOverkill();
        assertEquals(0, kill.size());
    }
}