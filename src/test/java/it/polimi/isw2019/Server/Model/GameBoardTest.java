package it.polimi.isw2019.Server.Model;

import it.polimi.isw2019.Model.AmmoTile.AmmoTile;
import it.polimi.isw2019.Model.Exception.AmmoTileUseException;
import it.polimi.isw2019.Model.Exception.OutOfBoundsException;
import it.polimi.isw2019.Model.PowerUpCard.PowerUpCard;
import it.polimi.isw2019.Model.ColorRoom;
import it.polimi.isw2019.Model.GameBoard;
import it.polimi.isw2019.Model.Player;
import it.polimi.isw2019.Model.WeaponCard.*;
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

        gameBoard= new GameBoard();
        gameBoard.chooseArena(4);

        weaponCard1= new Electroscythe();
        weaponCard2= new GrenadeLauncher();
        weaponCard3= new Furnace();
        weaponCard4= new GrenadeLauncher();
        weaponCard5= new HeatSeeker();
        weaponCard6= new GrenadeLauncher();
        weaponCard7= new Furnace();
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

        ammoTile=new AmmoTile(1, null,null,null);

        for (int i=0; i<12; i++){
            ammoTiles.add(ammoTile);
        }

        for (int i=0; i <26; i++){
            powerUpCards.add(powerUpCard);
        }

        player = new Player("name", "Comment", 1);
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
        assert (gameBoard.sizePowerUpCards()==26);
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
        assert (gameBoard.sizeWeaponCards()==0);
        try {
            ArrayList<AbstractWeaponCard> weaponCardsRed =gameBoard.weaponCardsOnSquares(1,0);
            assertTrue(weaponCardsRed.contains(weaponCard1));
            assertTrue(weaponCardsRed.contains(weaponCard4));
            assertTrue(weaponCardsRed.contains(weaponCard7));
            ArrayList<AbstractWeaponCard> weaponCardsBlue =gameBoard.weaponCardsOnSquares(0,2);
            assertTrue(weaponCardsBlue.contains(weaponCard2));
            assertTrue(weaponCardsBlue.contains(weaponCard5));
            assertTrue(weaponCardsBlue.contains(weaponCard8));
            ArrayList<AbstractWeaponCard> weaponCardsYellow =gameBoard.weaponCardsOnSquares(2,3);
            assertTrue(weaponCardsYellow.contains(weaponCard3));
            assertTrue(weaponCardsYellow.contains(weaponCard6));
            assertTrue(weaponCardsYellow.contains(weaponCard9));
        }
        catch (OutOfBoundsException e){
            fail();
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
            gameBoard.takeWeaponCard(weaponCard3,2,3);
        }
        catch (OutOfBoundsException e){
            fail();
        }
        try {
            ArrayList <AbstractWeaponCard> weaponCardsPresent = gameBoard.weaponCardsOnSquares(2,3);
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
            ArrayList <AbstractWeaponCard> weaponCardsPresent = gameBoard.weaponCardsOnSquares(1,0);
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
        assert (gameBoard.sizePowerUpCards()==25);
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

        gameBoard.placeAmmoTile(ammoTile, 0,0);
        assert (gameBoard.getAmmoTileOnSquare(0,0)==ammoTile);
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
}