package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class FurnaceTest {

    Player attacker, firstDefender, secondDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2;
    ShotGun card = new ShotGun();
    ArrayList<Player> defenders = new ArrayList<>();
    int [] coordinates = new int[4];

    @Before
    public void setUp() throws Exception {
        attacker = new Player("Alba", "Speriamo che sto test vada", 1);
        firstDefender = new Player("Davide", "Tanto attaccano sempre me", 2);
        secondDefender = new Player("Paola", "Tanto attaccano sempre Alba", 3);
        pba = new PlayerBoard(ColorPlayer.BLUE);
        pb1 = new PlayerBoard(ColorPlayer.YELLOW);
        pb2 = new PlayerBoard(ColorPlayer.GREEN);
        gameBoard = new GameBoard();
        gameBoard.chooseArena(4);

        attacker.setPlayerBoardAndColor(pba, ColorPlayer.BLUE);
        firstDefender.setPlayerBoardAndColor(pb1, ColorPlayer.YELLOW);
        secondDefender.setPlayerBoardAndColor(pb2, ColorPlayer.GREEN);

        gameBoard.insertPlayer(attacker, ColorRoom.BLUE);
        gameBoard.insertPlayer(firstDefender, ColorRoom.BLUE);
        gameBoard.insertPlayer(secondDefender, ColorRoom.BLUE);

        coordinates[2] = 2;
        coordinates[3] = 3;


        defenders.add(firstDefender);
        defenders.add(secondDefender);

    }

    @After
    public void tearDown() throws Exception {
    }

    /*@Test
    public void testDamageAndMarkFurnace(){

    }*/

    @Test
    public void testFirstEffect() {

    }

    @Test
    public void secondEffect() {
    }

    @Test
    public void thirdEffect() {
    }
}