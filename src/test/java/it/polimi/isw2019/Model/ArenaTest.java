package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.Exception.InstanceArenaException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArenaTest {

    Arena arena;
    Arena instArena1=null;
    Arena instArena2;


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInstanceArena(){
        try {
            instArena1 = Arena.instanceArena();
        }
        catch (InstanceArenaException e){
            fail();
        }
        try {
            instArena2 = Arena.instanceArena();
            fail();
        }
        catch (InstanceArenaException e){

        }

    }

    @Test
    public void testChooseArena() {
    }

    @Test
    public void testSetWeaponsCardOnSquareSpawn() {
    }

    @Test
    public void testPlaceAnotherWeaponCardsOnSquareSpawn() {
    }

    @Test
    public void testContainsWeaponOnSpawnSquare() {
    }

    @Test
    public void testTakeWeaponCardsOnSquareSpawn() {
    }

    @Test
    public void testSetAmmoTilesOnSquare() {
    }

    @Test
    public void testPlaceAnotherAmmoTileOnSquare() {
    }

    @Test
    public void tastTakeAmmoTileOnSquare() {
    }

    @Test
    public void testSpawnPlayer() {
    }

    @Test
    public void testMovePlayer() {
    }
}