package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.AmmoTile.AmmoTile;
import it.polimi.isw2019.Model.Exception.AmmoTileUseException;
import it.polimi.isw2019.Model.Exception.InstanceArenaException;
import it.polimi.isw2019.Model.Exception.OutOfRangeException;
import it.polimi.isw2019.Model.WeaponCard.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ArenaTest {

    Arena arena;
    Arena instArena2;
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

        player = new Player("name", "Comment", 1);

        player= new Player("name","Wow",1);

        arena = new Arena();
        /*try {
            arena = Arena.instanceArena();
        }
        catch (InstanceArenaException e){
            fail();
        }*/
        try {
            arena.chooseArena(4);
        }
        catch (OutOfRangeException e){
            fail();
        }

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInstanceArena(){
/*
        try {
            instArena2 = Arena.instanceArena();
            fail();
        }
        catch (InstanceArenaException e){

        }*/

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
        assert (ammoTiles.size()==3);

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
    public void testSpawnPlayer() {

        arena.spawnPlayer(ColorRoom.BLUE,player);
        ArrayList <Player> playersInOneSquare = arena.playersInOneSquareOnArena(0,2, null);
        assertTrue(playersInOneSquare.contains(player));

    }

    @Test
    public void testMovePlayer() {

        arena.spawnPlayer(ColorRoom.YELLOW,player);
        player.changePosition(2,3,ColorRoom.YELLOW);
        arena.movePlayer(player,1,3);
        ArrayList <Player> playersInOneSquare = arena.playersInOneSquareOnArena(1,3, null);
        assertTrue(playersInOneSquare.contains(player));
        ArrayList <Player> playersInPreviousPosition = arena.playersInOneSquareOnArena(2,3, null);
        assertFalse(playersInPreviousPosition.contains(player));

    }

}