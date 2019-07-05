package it.polimi.isw2019.model;

import it.polimi.isw2019.model.ammotile.AmmoTile;
import it.polimi.isw2019.model.exception.AmmoTileUseException;
import it.polimi.isw2019.model.exception.InstanceArenaException;
import it.polimi.isw2019.model.exception.OutOfBoundsException;
import it.polimi.isw2019.model.weaponcard.*;
import it.polimi.isw2019.utilities.Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ArenaTest {

    Arena arena;
    AbstractWeaponCard[] weaponCards1= new AbstractWeaponCard[3];
    AbstractWeaponCard[] weaponCards2= new AbstractWeaponCard[3];
    AbstractWeaponCard[] weaponCards3= new AbstractWeaponCard[3];
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
        weaponCard3= new ShotGun();
        weaponCard4= new GrenadeLauncher();
        weaponCard5= new HeatSeeker();
        weaponCard6= new GrenadeLauncher();
        weaponCard7= new ShotGun();
        weaponCard8= new Electroscythe();
        weaponCard9= new GrenadeLauncher();

        weaponCards1[0]= weaponCard1;
        weaponCards1[1]= weaponCard2;
        weaponCards1[2]= weaponCard3;
        weaponCards2[0]= weaponCard4;
        weaponCards2[1]= weaponCard5;
        weaponCards2[2]= weaponCard6;
        weaponCards3[0]= weaponCard7;
        weaponCards3[1]= weaponCard8;
        weaponCards3[2]= weaponCard9;




        Database db = new Database();

        ArrayList<AmmoTile> deckAmmoTile= db.loadAmmoTiles();
        for (int i=0; i<12; i++){
            ammoTiles.add(deckAmmoTile.get(i));
        }


        player = new Player("name", "Comment", 0);

        player1= new Player("name1","Wow",1);
        player2= new Player("name2","Wow",2);
        player3= new Player("name3","Wow",3);
        player4= new Player("name4","Wow",4);


        arena = new Arena();

        try {
            arena.chooseArena(4);
        }
        catch (OutOfBoundsException e){
            fail();
        }

    }

    @After
    public void tearDown() throws Exception {
    }


    @Test (expected = OutOfBoundsException.class)
    public void testChooseArena() throws OutOfBoundsException {
        arena.chooseArena(6);
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
        assertEquals (3,ammoTiles.size());

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
    public void testPlayersInOneSquareOnArena (){
        arena.spawnPlayer(ColorRoom.YELLOW,player1);
        arena.spawnPlayer(ColorRoom.YELLOW,player2);
        arena.spawnPlayer(ColorRoom.YELLOW,player3);
        arena.spawnPlayer(ColorRoom.RED,player4);

        ArrayList<Player> allPayers = arena.playersInOneSquareOnArena(2,3,null);

        assertTrue(allPayers.contains(player1));
        assertTrue(allPayers.contains(player2));
        assertTrue(allPayers.contains(player3));
        assertFalse(allPayers.contains(player4));

        ArrayList<Player> players = arena.playersInOneSquareOnArena(2,3, player1);

        assertFalse(players.contains(player1));
        assertTrue(players.contains(player2));
        assertTrue(players.contains(player3));
        assertFalse(players.contains(player4));


    }

    @Test
    public void testPlayerInTheRoomNear(){
        arena.spawnPlayer(ColorRoom.BLUE,player1);


        arena.spawnPlayer(ColorRoom.BLUE,player2);

        arena.spawnPlayer(ColorRoom.BLUE,player3);

        ArrayList<Player> players = arena.playersInOneRoom(ColorRoom.BLUE,null);
        assertTrue(players.contains(player1));
        assertTrue(players.contains(player2));
        assertTrue(players.contains(player3));
        assertEquals(ColorRoom.BLUE,player1.getColorRoom());
        assertEquals(ColorRoom.BLUE,player2.getColorRoom());
        assertEquals(ColorRoom.BLUE,player3.getColorRoom());

        arena.spawnPlayer(ColorRoom.YELLOW,player4);
        assertEquals(ColorRoom.YELLOW,player4.getColorRoom());

        if(arena.isSquaresAvailable(player1,1,2)){
            assertTrue(arena.isPlayerChangeRoom(player1,1,2));
            player1.changePosition(1,2,ColorRoom.YELLOW);
        }
        else fail();

        arena.teleporterMove(player3,1,3);
        player3.changePosition(1,3,ColorRoom.YELLOW);


        if(arena.isSquaresAvailable(player4,2,2)){
            assertFalse(arena.isPlayerChangeRoom(player1,2,2));
            player4.changePosition(2,2,ColorRoom.YELLOW);
        }
        else fail();

        ArrayList<Player> playersNear2 = arena.playerInTheRoomNear(player2);

        //assertEquals(4,players.size());
        assertTrue(playersNear2.contains(player1));
        assertTrue(playersNear2.contains(player2));
        assertTrue(playersNear2.contains(player3));
        assertTrue(playersNear2.contains(player4));

    }

    @Test
    public void testInTheSameSquares (){
        arena.spawnPlayer(ColorRoom.BLUE,player1);

        arena.spawnPlayer(ColorRoom.BLUE,player2);

        arena.spawnPlayer(ColorRoom.YELLOW,player4);

        ArrayList<Player> playersWhoSeePlayer1 = arena.playerWhoSeeOnArena(player1);

        assertEquals(2,playersWhoSeePlayer1.size());
        assertFalse(playersWhoSeePlayer1.contains(player1));
        assertTrue(playersWhoSeePlayer1.contains(player2));
        assertTrue(playersWhoSeePlayer1.contains(player4));

    }


    @Test
    public void testPlayerWhoSeeOnArena (){

        arena.spawnPlayer(ColorRoom.BLUE,player1);

        arena.spawnPlayer(ColorRoom.BLUE,player2);

        arena.spawnPlayer(ColorRoom.BLUE,player3);


        arena.spawnPlayer(ColorRoom.YELLOW,player4);


        if(arena.isSquaresAvailable(player1,1,2)){
            player1.changePosition(1,2,ColorRoom.YELLOW);
        }
        else fail();

        arena.teleporterMove(player3,1,3);

        if(arena.isSquaresAvailable(player4,2,2)){
            player4.changePosition(2,2,ColorRoom.YELLOW);
        }
        else fail();

        ArrayList<Player> playersWhoSeePlayer1 = arena.playerWhoSeeOnArena(player1);

       // assertEquals(3,playersWhoSeePlayer1.size());
        assertFalse(playersWhoSeePlayer1.contains(player1));
//        assertTrue(playersWhoSeePlayer1.contains(player2));
        assertTrue(playersWhoSeePlayer1.contains(player3));
        assertTrue(playersWhoSeePlayer1.contains(player4));


        ArrayList<Player> playersWhoSeePlayer2 = arena.playerWhoSeeOnArena(player2);

//        assertEquals(3,playersWhoSeePlayer2.size());
        assertTrue(playersWhoSeePlayer2.contains(player1));
        assertFalse(playersWhoSeePlayer2.contains(player2));
        assertTrue(playersWhoSeePlayer2.contains(player3));
        assertTrue(playersWhoSeePlayer2.contains(player4));
/*
        ArrayList<Player> playersWhoSeePlayer3 = arena.playerWhoSeeOnArena(player3);

        assertEquals(2,playersWhoSeePlayer3.size());
        assertTrue(playersWhoSeePlayer3.contains(player1));
        assertFalse(playersWhoSeePlayer3.contains(player2));
        assertFalse(playersWhoSeePlayer3.contains(player3));
        assertTrue(playersWhoSeePlayer3.contains(player4));*/


    }


    @Test
    public void testPlayerInTheRoomNear2(){
        arena.spawnPlayer(ColorRoom.BLUE,player1);
        player1.changePosition(0,2,ColorRoom.BLUE);

        arena.spawnPlayer(ColorRoom.BLUE,player2);
        player2.changePosition(0,2,ColorRoom.BLUE);

        arena.spawnPlayer(ColorRoom.BLUE,player3);
        player3.changePosition(0,2,ColorRoom.BLUE);

        arena.spawnPlayer(ColorRoom.YELLOW,player4);
        player4.changePosition(2,3,ColorRoom.RED);

        arena.teleporterMove(player1,1,1);
        player1.changePosition(1,1,ColorRoom.VIOLET);

        if(arena.isSquaresAvailable(player2,0,1)){
            player2.changePosition(0,1,ColorRoom.BLUE);
        }

        arena.teleporterMove(player3,1,3);
        player3.changePosition(1,3,ColorRoom.YELLOW);

        arena.teleporterMove(player4,2,1);
        player4.changePosition(2,1,ColorRoom.GREY);


        ArrayList<Player> players2= arena.playerInTheRoomNear(player2);
        assertEquals(2,players2.size());
        assertTrue(players2.contains(player1));
         assertTrue(players2.contains(player2));
        assertFalse(players2.contains(player3));
        assertFalse(players2.contains(player4));


        ArrayList<Player> players3= arena.playerInTheRoomNear(player3);
     //   assertEquals(2,players3.size());
        assertFalse(players3.contains(player1));
        assertFalse(players3.contains(player2));
        assertTrue(players3.contains(player3));
        assertFalse(players3.contains(player4));

    }

    @Test
    public void testPlayerWhoSeeOnArena2 (){
        arena.spawnPlayer(ColorRoom.BLUE,player1);


        arena.spawnPlayer(ColorRoom.BLUE,player2);

        arena.spawnPlayer(ColorRoom.YELLOW,player3);


        arena.spawnPlayer(ColorRoom.RED,player4);


        arena.teleporterMove(player1,1,1);


        if(arena.isSquaresAvailable(player2,0,1)){
            player2.changePosition(0,1,ColorRoom.BLUE);
        }

        if(arena.isSquaresAvailable(player3,1,3)) arena.movePlayer(player3,1,3);

        arena.teleporterMove(player4,2,1);


        ArrayList<Player> playersWhoSeePlayer2 = arena.playerWhoSeeOnArena(player2);

        assertEquals(2,playersWhoSeePlayer2.size());
        assertTrue(playersWhoSeePlayer2.contains(player1));
        assertFalse(playersWhoSeePlayer2.contains(player2));
        assertFalse(playersWhoSeePlayer2.contains(player3));
        assertTrue(playersWhoSeePlayer2.contains(player4));

        ArrayList<Player> playersWhoSeePlayer3 = arena.playerWhoSeeOnArena(player3);

        assertEquals(0,playersWhoSeePlayer3.size());
        assertFalse(playersWhoSeePlayer3.contains(player1));
        assertFalse(playersWhoSeePlayer3.contains(player2));
        assertFalse(playersWhoSeePlayer3.contains(player3));
        assertFalse(playersWhoSeePlayer3.contains(player4));
/*
        ArrayList<Player> playersWhoSeePlayer4 = arena.playerWhoSeeOnArena(player4);

        //assertEquals(3,playersWhoSeePlayer4.size());
        assertTrue(playersWhoSeePlayer4.contains(player1));
        assertTrue(playersWhoSeePlayer4.contains(player2));
        assertTrue(playersWhoSeePlayer4.contains(player3));
        assertFalse(playersWhoSeePlayer4.contains(player4));*/
    }



    @Test
    public void testPlayerInRoom (){
        arena.spawnPlayer(ColorRoom.BLUE,player1);

        arena.spawnPlayer(ColorRoom.YELLOW,player2);

        arena.spawnPlayer(ColorRoom.BLUE,player3);


      /*  ArrayList <Player> playersInOneRoom1 = arena.playersInOneRoom(player1.getColorRoom(),player1);
        assertEquals(1,playersInOneRoom1.size());
        assertFalse(playersInOneRoom1.contains(player1));
        assertFalse(playersInOneRoom1.contains(player2));
        assertTrue(playersInOneRoom1.contains(player3));*/

        ArrayList<Player> playersInOneRoom2= arena.playersInOneRoom(player2.getColorRoom(),player2);
        assertEquals(0,playersInOneRoom2.size());
        assertFalse(playersInOneRoom2.contains(player1));
        assertFalse(playersInOneRoom2.contains(player2));
        assertFalse(playersInOneRoom2.contains(player3));


        ArrayList <Player> playersInOneRoom3 = arena.playersInOneRoom(player3.getColorRoom(), player3);
        assertTrue(player1.getColorRoom()==player3.getColorRoom());
        assertEquals(1,playersInOneRoom3.size());
        assertTrue(playersInOneRoom3.contains(player1));
        assertFalse(playersInOneRoom3.contains(player2));
        assertFalse(playersInOneRoom3.contains(player3));
    }


    @Test
    public void testSpawnPlayer() {

        arena.spawnPlayer(ColorRoom.BLUE,player);
        ArrayList <Player> playersInOneSquare = arena.playersInOneSquareOnArena(0,2, null);
        assertTrue(playersInOneSquare.contains(player));

    }


    @Test
    public void testTeleportMove (){
        arena.spawnPlayer(ColorRoom.BLUE,player1);
        player1.changePosition(0,2,ColorRoom.BLUE);

        arena.spawnPlayer(ColorRoom.BLUE,player2);
        player2.changePosition(0,2,ColorRoom.BLUE);

        arena.spawnPlayer(ColorRoom.BLUE,player3);
        player3.changePosition(0,2,ColorRoom.BLUE);

        arena.spawnPlayer(ColorRoom.YELLOW,player4);
        player4.changePosition(2,3,ColorRoom.RED);

        arena.teleporterMove(player1,1,1);
        player1.changePosition(1,1,ColorRoom.VIOLET);

        if(arena.isSquaresAvailable(player2,0,1)){
            player2.changePosition(0,1,ColorRoom.BLUE);
        }

        arena.teleporterMove(player3,1,3);
        player3.changePosition(1,3,ColorRoom.YELLOW);

        arena.teleporterMove(player4,2,1);
        player4.changePosition(2,1,ColorRoom.GREY);


//        assertTrue(arena.playersInOneRoom(ColorRoom.VIOLET,null).contains(player1));
        assertTrue(arena.playersInOneRoom(ColorRoom.BLUE,null).contains(player2));
        assertTrue(arena.playersInOneRoom(ColorRoom.YELLOW,null).contains(player3));
        assertTrue(arena.playersInOneRoom(ColorRoom.GREY,null).contains(player4));
        assertFalse(arena.playersInOneRoom(ColorRoom.YELLOW,null).contains(player4));


    }


    @Test
    public void testMovePlayer() {
        /* Riguardare

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
        assertFalse(playersInPreviousPosition.contains(player));*/
    }


    @Test
    public void testPlayerChangeRoom (){



        arena.spawnPlayer(ColorRoom.RED,player1);

        arena.playerChangeRoom(player1,2,0);

        ArrayList<Player> playersRoomRed = arena.playersInOneRoom(ColorRoom.RED,null);
        assertEquals(0, playersRoomRed.size());
        assertFalse(playersRoomRed.contains(player1));


        ArrayList<Player> playersRoomGrey = arena.playersInOneRoom(ColorRoom.GREY,null);

        assertEquals(1, playersRoomGrey.size());
        assertTrue(playersRoomGrey.contains(player1));
        assertEquals(ColorRoom.GREY, player1.getColorRoom());
    }
}