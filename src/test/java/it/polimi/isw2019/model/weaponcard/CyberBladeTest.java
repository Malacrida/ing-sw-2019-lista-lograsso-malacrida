package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.*;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
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

    int [] coordinates = new int[4];
    ArrayList<Player> defenders = new ArrayList<>();

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

        coordinates[2] = 2;
        coordinates[3] = 3;

        defenders.add(firstDefender);
        defenders.add(secondDefender);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFirstEffect() {
        try {
            System.out.println("FIRSTDEFENDER - La X:" + firstDefender.getX() + " " + "La Y:" + firstDefender.getY());
            System.out.println("ATTACKER - La X:" + attacker.getX() + " " + "La Y:" + attacker.getY());
            System.out.println("DEFENDER.GET(0) - La X:" + defenders.get(0).getX() + " " + "La Y:" + defenders.get(0).getY());
            card.firstEffect(gameBoard, attacker, defenders, coordinates);
            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

            assertTrue(visiblePlayers.contains(defenders.get(0)));
            assertEquals(2, pb1.numOfDamages());
            assertEquals(0, pb2.numOfDamages());
        } catch (ErrorEffectException | DamageTrackException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSecondEffect() {
    }

    @Test
    public void testThirdEffect() {
        try {
            card.thirdEffect(gameBoard, attacker, defenders, coordinates);
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