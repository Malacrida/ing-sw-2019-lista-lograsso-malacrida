package it.polimi.isw2019.Server.Model;

import it.polimi.isw2019.Server.Model.AmmoTile.AmmoTile;
import it.polimi.isw2019.Server.Model.Arena;
import it.polimi.isw2019.Server.Model.ColorRoom;
import it.polimi.isw2019.Server.Model.Exception.AmmoTileUseException;
import it.polimi.isw2019.Server.Model.Exception.OutOfRangeException;
import it.polimi.isw2019.Server.Model.Player;
import it.polimi.isw2019.Server.Model.WeaponCard.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ArenaTest {

    Arena arena;
    ArrayList<AbstractWeaponCard> weaponCards1= new ArrayList<>();;
    ArrayList<AbstractWeaponCard> weaponCards2= new ArrayList<>();;
    ArrayList<AbstractWeaponCard> weaponCards3= new ArrayList<>();;
    AbstractWeaponCard weaponCard1;
    AbstractWeaponCard weaponCard2;
    AbstractWeaponCard weaponCard3;
    AbstractWeaponCard weaponCard4;
    AbstractWeaponCard weaponCard5;
    AbstractWeaponCard weaponCard6;
    AbstractWeaponCard weaponCard7;
    AbstractWeaponCard weaponCard8;
    AbstractWeaponCard weaponCard9;

    AmmoTile ammoTile;
    ArrayList<AmmoTile> ammoTiles= new ArrayList<>();

    Player player;
    Player player1;
    Player player2;
    Player player3;
    Player player4;



    @Before
    public void setUp() throws Exception {
        weaponCard1= new Electroscythe();
        weaponCard2= new GrenadeLauncher();
        weaponCard3= new Furnace();
        weaponCard4= new GrenadeLauncher();
        weaponCard5= new HeatSeeker();
        weaponCard6= new GrenadeLauncher();
        weaponCard7= new Furnace();
        weaponCard8= new Electroscythe();
        weaponCard9= new GrenadeLauncher();

        weaponCards1.add(weaponCard1);
        weaponCards1.add(weaponCard2);
        weaponCards1.add(weaponCard3);
        weaponCards2.add(weaponCard4);
        weaponCards2.add(weaponCard5);
        weaponCards2.add(weaponCard6);
        weaponCards3.add(weaponCard7);
        weaponCards3.add(weaponCard8);
        weaponCards3.add(weaponCard9);

        ammoTile= new AmmoTile(1,null, null, null);
        for (int i=0; i<12; i++){
            ammoTiles.add(ammoTile);
        }

        player = new Player("name", "Comment", 0);

        player1= new Player("name1","Wow",1);
        player2= new Player("name2","Wow",2);
        player3= new Player("name3","Wow",3);
        player4= new Player("name4","Wow",4);


        arena = new Arena();

        try {
            arena.chooseArena(1);
        }
        catch (OutOfRangeException e){
            fail();
        }

    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void testChooseArena() {

        try {
            arena.chooseArena(6);
            fail();
        }
        catch (OutOfRangeException e){

        }
    }

    @Test
    public void testSetWeaponsCardOnSquareSpawn() {

        arena.setWeaponsCardOnSquareSpawn(weaponCards1,weaponCards2,weaponCards3);
        assertTrue(arena.containsWeaponOnSpawnSquare(1,0,weaponCard1));
        assertTrue(arena.containsWeaponOnSpawnSquare(1,0,weaponCard2));
        assertTrue(arena.containsWeaponOnSpawnSquare(1,0,weaponCard3));
        assertTrue(arena.containsWeaponOnSpawnSquare(0,2,weaponCard4));
        assertTrue(arena.containsWeaponOnSpawnSquare(0,2,weaponCard5));
        assertTrue(arena.containsWeaponOnSpawnSquare(0,2,weaponCard6));
        assertTrue(arena.containsWeaponOnSpawnSquare(2,3,weaponCard7));
        assertTrue(arena.containsWeaponOnSpawnSquare(2,3,weaponCard8));
        assertTrue(arena.containsWeaponOnSpawnSquare(2,3,weaponCard9));

    }


    @Test
    public void testTakeAndPlaceWeaponCardsOnSquareSpawn() {

        arena.setWeaponsCardOnSquareSpawn(weaponCards1,weaponCards2,weaponCards3);
        arena.takeWeaponCardsOnSquareSpawn(weaponCard8,2,3);
        assertFalse ((arena.containsWeaponOnSpawnSquare(2,3,weaponCard8)));
        assertTrue(arena.containsWeaponOnSpawnSquare(2,3,weaponCard7));
        assertTrue(arena.containsWeaponOnSpawnSquare(2,3,weaponCard9));
        arena.placeAnotherWeaponCardsOnSquareSpawn(weaponCard1,2,3);
        assertTrue(arena.containsWeaponOnSpawnSquare(2,3,weaponCard1));
        assertTrue(arena.containsWeaponOnSpawnSquare(2,3,weaponCard7));
        assertTrue(arena.containsWeaponOnSpawnSquare(2,3,weaponCard9));

    }

    @Test
    public void testSetAmmoTilesOnSquare() {

        arena.setAmmoTilesOnSquare(ammoTiles);
        assertEquals (4,ammoTiles.size());

    }

    @Test
    public void testPlaceAnotherAmmoTileOnSquare() {

        arena.placeAnotherAmmoTileOnSquare(ammoTile,0,1);
        assertEquals(arena.getAmmoTileOnSquare(0,1),ammoTile);

    }

    @Test
    public void testTakeAmmoTileOnSquare() {

        arena.placeAnotherAmmoTileOnSquare(ammoTile,0,1);
        try {
            arena.takeAmmoTileOnSquare(0,1);
        }
        catch (AmmoTileUseException e){
            fail();
        }

        try {
            arena.takeAmmoTileOnSquare(0,1);
            fail();
        }
        catch (AmmoTileUseException e){

        }

        try {
            arena.takeAmmoTileOnSquare(0,2);
            fail();
        }
        catch (AmmoTileUseException e){

        }

    }

    @Test
    public void testPlayerWhoSeeOnArena (){

        arena.spawnPlayer(ColorRoom.BLUE,player1);
        player1.changePosition(0,2,ColorRoom.BLUE);

        arena.spawnPlayer(ColorRoom.BLUE,player2);
        player2.changePosition(0,2,ColorRoom.BLUE);

        arena.spawnPlayer(ColorRoom.BLUE,player3);
        player3.changePosition(0,2,ColorRoom.BLUE);

        arena.spawnPlayer(ColorRoom.YELLOW,player4);
        player4.changePosition(2,3,ColorRoom.RED);

        if(arena.movePlayer(player1,1,2,false)){
            player1.changePosition(1,2,ColorRoom.YELLOW);
        }
        else fail();

        if(arena.movePlayer(player3,1,3,true)){
            player3.changePosition(1,3,ColorRoom.YELLOW);
        }
        else fail();

        if(arena.movePlayer(player4,2,2,false)){
            player4.changePosition(2,2,ColorRoom.YELLOW);
        }
        else fail();


       ArrayList<Player> playersWhoSeePlayer1 = arena.playerWhoSeeOnArena(player1.getX(), player1.getY(), player1);

        assertEquals(3,playersWhoSeePlayer1.size());
        assertFalse(playersWhoSeePlayer1.contains(player1));
        assertTrue(playersWhoSeePlayer1.contains(player2));
        assertTrue(playersWhoSeePlayer1.contains(player3));
        assertTrue(playersWhoSeePlayer1.contains(player4));

        ArrayList<Player> playersWhoSeePlayer2 = arena.playerWhoSeeOnArena(player2.getX(), player2.getY(), player2);

        assertEquals(2,playersWhoSeePlayer2.size());
        assertTrue(playersWhoSeePlayer2.contains(player1));
        assertFalse(playersWhoSeePlayer2.contains(player2));
        assertFalse(playersWhoSeePlayer2.contains(player3));
        assertTrue(playersWhoSeePlayer2.contains(player4));
/*
        ArrayList<Player> playersWhoSeePlayer3 = arena.playerWhoSeeOnArena(player3.getX(), player3.getY(), player3);

        assertEquals(2,playersWhoSeePlayer3.size());
        assertTrue(playersWhoSeePlayer3.contains(player1));
        assertFalse(playersWhoSeePlayer3.contains(player2));
        assertFalse(playersWhoSeePlayer3.contains(player3));
        assertTrue(playersWhoSeePlayer3.contains(player4));

        ArrayList<Player> playersWhoSeePlayer4 = arena.playerWhoSeeOnArena(player4.getX(),player4.getY(),player4);
        assertEquals(3,playersWhoSeePlayer4.size());
        assertTrue(playersWhoSeePlayer4.contains(player1));
        assertTrue(playersWhoSeePlayer4.contains(player2));
        assertTrue(playersWhoSeePlayer4.contains(player3));
        assertFalse(playersWhoSeePlayer4.contains(player4));*/


    }


    @Test
    public void testPlayerInRoom (){
        arena.spawnPlayer(ColorRoom.BLUE,player1);
        player1.changePosition(0,2,ColorRoom.BLUE);


        arena.spawnPlayer(ColorRoom.YELLOW,player2);
        player2.changePosition(0,2,ColorRoom.YELLOW);

        arena.spawnPlayer(ColorRoom.BLUE,player3);
        player3.changePosition(0,2,ColorRoom.BLUE);

        ArrayList <Player> playersInOneRoom1 = arena.playersInOneRoom(player1.getColorRoom(),player1);
        assertEquals(1,playersInOneRoom1.size());
        assertFalse(playersInOneRoom1.contains(player1));
        assertFalse(playersInOneRoom1.contains(player2));
        assertTrue(playersInOneRoom1.contains(player3));

        ArrayList<Player> playersInOneRoom2= arena.playersInOneRoom(player2.getColorRoom(),player2);
        assertEquals(0,playersInOneRoom2.size());
        assertFalse(playersInOneRoom2.contains(player1));
        assertFalse(playersInOneRoom2.contains(player2));
        assertFalse(playersInOneRoom2.contains(player3));

        /*
        ArrayList <Player> playersInOneRoom3 = arena.playersInOneRoom(player3.getColorRoom(), player3);
        assertTrue(player1.getColorRoom()==player3.getColorRoom());
        assertEquals(1,playersInOneRoom3.size());
        assertTrue(playersInOneRoom3.contains(player1));
        assertFalse(playersInOneRoom3.contains(player2));
        assertFalse(playersInOneRoom3.contains(player3));*/
    }


    @Test
    public void testSpawnPlayer() {

        arena.spawnPlayer(ColorRoom.BLUE,player);
        ArrayList <Player> playersInOneSquare = arena.playersInOneSquareOnArena(0,2, null);
        assertTrue(playersInOneSquare.contains(player));

    }

    @Test
    public void testMovePlayer() {

        arena.spawnPlayer(ColorRoom.YELLOW,player);
        player.changePosition(2,3,ColorRoom.YELLOW);
        arena.movePlayer(player,1,3,false);
        player.changePosition(1,3,ColorRoom.YELLOW);
        ArrayList <Player> playersInOneSquare = arena.playersInOneSquareOnArena(1,3, null);
        assertTrue(playersInOneSquare.contains(player));
        ArrayList <Player> playersInPreviousPosition = arena.playersInOneSquareOnArena(2,3, null);
        assertFalse(playersInPreviousPosition.contains(player));

        arena.movePlayer(player,0,0,true);
        player.changePosition(0,0,ColorRoom.RED);

        playersInOneSquare = arena.playersInOneSquareOnArena(0,0, null);
        assertTrue(playersInOneSquare.contains(player));
        playersInPreviousPosition = arena.playersInOneSquareOnArena(1,3, null);
        assertFalse(playersInPreviousPosition.contains(player));
    }

}