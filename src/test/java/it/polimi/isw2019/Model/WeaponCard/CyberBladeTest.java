package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.*;
import it.polimi.isw2019.Model.Exception.DamageTrackException;
import it.polimi.isw2019.Model.Exception.ErrorEffectException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CyberBladeTest {

    Player attacker, firstDefender, secondDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2;
    CyberBlade card = new CyberBlade();

    @Before
    public void setUp() throws Exception {
        attacker = new Player("Alba", "Speriamo che sto test vada", 1);
        firstDefender = new Player("Davide", "Tanto attaccano sempre me", 2);
        secondDefender = new Player("Sara", "Tanto attaccano sempre Alba", 3);
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
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFirstEffect() {
        try {
            card.firstEffect(gameBoard, attacker, firstDefender, null, null,-1, -1, -1, -1);
            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

            assertTrue(visiblePlayers.contains(firstDefender));

        } catch (ErrorEffectException | DamageTrackException e) {
            e.printStackTrace();
        }

        try {
            assertEquals(2, pb1.numOfDamages());
            assertEquals(0, pb2.numOfDamages());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSecondEffect() {
    }

    @Test
    public void testThirdEffect() {
        try {
            card.thirdEffect(gameBoard, attacker, firstDefender, secondDefender, null, -1, -1, -1, -1);
            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

            assertTrue(visiblePlayers.contains(secondDefender));
        } catch (ErrorEffectException | DamageTrackException e) {
            e.printStackTrace();
        }

        try {
            assertEquals(2, pb2.numOfDamages());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}