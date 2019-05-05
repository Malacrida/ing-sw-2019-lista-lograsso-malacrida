package it.polimi.isw2019.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RoomTest {

    Room room;

    Square squareAmmo1;
    Square squareAmmo2;
    Square squareAmmo3;
    Square squareSpawn;

    Player player1;
    Player player2;
    Player player3;

    @Before
    public void setUp() throws Exception {
        room= new Room(ColorRoom.YELLOW);

        squareAmmo1= new SquareAmmo();
        squareAmmo2= new SquareAmmo();
        squareAmmo3= new SquareAmmo();
        squareSpawn= new SquareSpawn();

        squareAmmo1.setSquareAdjacent(null,squareAmmo2,squareAmmo3,null);
        squareAmmo2.setSquareAdjacent(null,null,squareSpawn,squareAmmo1);
        squareAmmo3.setSquareAdjacent(squareAmmo1,squareSpawn,null,null);
        squareSpawn.setSquareAdjacent(squareAmmo2,null,null,squareAmmo3);

        player1= new Player("name1", null,1);
        player2= new Player("name2", null,2);
        player3= new Player("name3", null,3);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddSquare() {
        room.addSquare(squareAmmo1);
        room.addSquare(squareAmmo2);
        room.addSquare(squareAmmo3);
        room.addSquare(squareSpawn);

        assert (room.sizeSquare()==4);
        assertTrue(room.containsSquare(squareAmmo1));
        assertTrue(room.containsSquare(squareAmmo2));
        assertTrue(room.containsSquare(squareAmmo3));
        assertTrue(room.containsSquare(squareSpawn));
    }

    @Test
    public void testAddPlayer() {
        room.addPlayer(player1);
        room.addPlayer(player2);
        ArrayList <Player> playersPresent = room.getPlayers();
        assert (playersPresent.size()==2);
        assertTrue(playersPresent.contains(player1));
        assertTrue(playersPresent.contains(player2));
        assertFalse(playersPresent.contains(player3));
    }

    @Test
    public void testRemovePlayer() {
        room.addPlayer(player1);
        room.addPlayer(player2);
        room.removePlayer(player2);
        ArrayList <Player> playersPresent = room.getPlayers();
        assert (playersPresent.size()==1);
        assertTrue(playersPresent.contains(player1));
        assertFalse(playersPresent.contains(player2));
        assertFalse(playersPresent.contains(player3));
    }

}