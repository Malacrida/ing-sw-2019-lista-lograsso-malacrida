package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.*;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.NoEffectException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ElectroscytheTest {

    Player attacker, firstDefender, secondDefender, thirdDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2, pb3;
    Electroscythe card = new Electroscythe();
    int [] coordinates = new int[4];
    ArrayList<Player> defenders = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        attacker = new Player("Giampierpaolo", "Speriamo che sto test vada", 1);
        firstDefender = new Player("Davide", "Tanto attaccano sempre me", 2);
        secondDefender = new Player("Sara", "Tanto attaccano sempre Alba", 3);
        thirdDefender = new Player("Virginia", "Devo fare le pulzie?", 4);
        pba = new PlayerBoard(ColorPlayer.BLUE);
        pb1 = new PlayerBoard(ColorPlayer.YELLOW);
        pb2 = new PlayerBoard(ColorPlayer.GREEN);
        pb3 = new PlayerBoard(ColorPlayer.VIOLET);
        gameBoard = new GameBoard();
        gameBoard.chooseArena(4);

        attacker.setPlayerBoardAndColor(pba, ColorPlayer.BLUE);
        firstDefender.setPlayerBoardAndColor(pb1, ColorPlayer.YELLOW);
        secondDefender.setPlayerBoardAndColor(pb2, ColorPlayer.GREEN);
        thirdDefender.setPlayerBoardAndColor(pb3, ColorPlayer.VIOLET);

        gameBoard.insertPlayer(attacker, ColorRoom.BLUE);
        gameBoard.insertPlayer(firstDefender, ColorRoom.BLUE);
        gameBoard.insertPlayer(secondDefender, ColorRoom.BLUE);
        gameBoard.insertPlayer(thirdDefender, ColorRoom.BLUE);

        coordinates[2] = 2;
        coordinates[3] = 3;


        defenders.add(firstDefender);
        defenders.add(secondDefender);
        defenders.add(thirdDefender);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testFirstEffect() {
        try {
            card.firstEffect(gameBoard, attacker, defenders, coordinates);
            ArrayList<Player> players = gameBoard.playersInOneSquare(attacker.getX(),attacker.getY(), attacker);

            assertTrue(players.contains(firstDefender));
            assertTrue(players.contains(secondDefender));
            assertTrue(players.contains(thirdDefender));


        } catch (ErrorEffectException | DamageTrackException e) {
            e.printStackTrace();
        }

        try {
            assertEquals(1, pb1.numOfDamages());
            assertEquals(1, pb2.numOfDamages());
            assertEquals(1, pb3.numOfDamages());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSecondEffect() {
        try {
            card.secondEffect(gameBoard, attacker, defenders, coordinates);
            ArrayList<Player> players = gameBoard.playersInOneSquare(attacker.getX(),attacker.getY(), attacker);

            assertTrue(players.contains(firstDefender));
            assertTrue(players.contains(secondDefender));



        } catch (ErrorEffectException | DamageTrackException e) {
            e.printStackTrace();
        }

        try {
            assertEquals(2, pb1.numOfDamages());
            assertEquals(2, pb2.numOfDamages());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test (expected = NoEffectException.class)
    public void thirdEffect() throws NoEffectException {
        card.thirdEffect(gameBoard, attacker, defenders, coordinates);
    }
}