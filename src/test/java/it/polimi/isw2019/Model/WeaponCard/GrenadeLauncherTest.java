package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.*;
import it.polimi.isw2019.Model.Exception.DamageTrackException;
import it.polimi.isw2019.Model.Exception.ErrorEffectException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GrenadeLauncherTest {

    Player attacker, firstDefender, secondDefender, thirdDefender, fourDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2, pb3, pb4;
    GrenadeLauncher card = new GrenadeLauncher();

    @Before
    public void setUp() throws Exception {

        attacker = new Player("Alba", "Tanto attaccano sempre me", 1);
        firstDefender = new Player("Stavri", "Accipigna", 2);
        secondDefender = new Player("Sara", "Tanto attaccano sempre Alba", 3);
        thirdDefender = new Player("Alessio", "Ma non facevo Automatica?", 4);
        fourDefender = new Player("Asdrubale", "Che nome del piffero", 5);
        pba = new PlayerBoard(ColorPlayer.BLUE);
        pb1 = new PlayerBoard(ColorPlayer.YELLOW);
        pb2 = new PlayerBoard(ColorPlayer.GREEN);
        pb3 = new PlayerBoard(ColorPlayer.GREY);
        pb4 = new PlayerBoard(ColorPlayer.VIOLET);
        gameBoard = new GameBoard();
        gameBoard.chooseArena(1);

        attacker.setPlayerBoardAndColor(pba, ColorPlayer.BLUE);
        firstDefender.setPlayerBoardAndColor(pb1, ColorPlayer.YELLOW);
        secondDefender.setPlayerBoardAndColor(pb2, ColorPlayer.GREEN);
        thirdDefender.setPlayerBoardAndColor(pb3, ColorPlayer.GREY);
        fourDefender.setPlayerBoardAndColor(pb4, ColorPlayer.GREY);

        gameBoard.insertPlayer(attacker, ColorRoom.BLUE);
        gameBoard.insertPlayer(firstDefender, ColorRoom.YELLOW);
        gameBoard.insertPlayer(secondDefender, ColorRoom.YELLOW);
        gameBoard.insertPlayer(thirdDefender, ColorRoom.RED);
        gameBoard.insertPlayer(fourDefender, ColorRoom.YELLOW);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFirstEffect() {
        try {
            card.firstEffect(gameBoard, attacker, firstDefender, secondDefender, thirdDefender,-1, -1, -1, -1);
            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

            assertTrue(visiblePlayers.contains(firstDefender));
            assertTrue(visiblePlayers.contains(secondDefender));
            assertFalse(visiblePlayers.contains(thirdDefender));


        } catch (ErrorEffectException | DamageTrackException e) {
            e.printStackTrace();
        }

        try {
            assertEquals(1, pb1.numOfDamages());
            assertEquals(0, pb2.numOfDamages());
            assertEquals(0, pb3.numOfDamages());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSecondEffect() {
        try {
            System.out.print(firstDefender.getX() + " " + firstDefender.getY());
            card.secondEffect(gameBoard, attacker, null, null, null,2, 3, -1, -1, -1, -1);
            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);
            ArrayList<Player> players = gameBoard.playersInOneSquare(2, 3, null);


            assertEquals(2, firstDefender.getX());

            assertTrue(visiblePlayers.contains(firstDefender));
            assertTrue(visiblePlayers.contains(secondDefender));
            assertFalse(visiblePlayers.contains(thirdDefender));
            assertTrue(visiblePlayers.contains(fourDefender));

            assertTrue(players.contains(firstDefender));
            assertTrue(players.contains(secondDefender));
            assertFalse(players.contains(thirdDefender));
            assertTrue(players.contains(fourDefender));

            assertEquals(1, pb1.numOfDamages());
            assertEquals(1, pb2.numOfDamages());
            assertEquals(0, pb3.numOfDamages());
            assertEquals(1, pb4.numOfDamages());


        } catch (ErrorEffectException | DamageTrackException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testThirdEffect() {
    }
}