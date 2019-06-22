package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.*;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
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
    ShotGun card = new ShotGun();
    ArrayList<Player> defenders = new ArrayList<>();
    int [] coordinates = new int[4];

    @Before
    public void setUp() throws Exception {
        attacker = new Player("Alba", "Speriamo che sto test vada", 1);
        firstDefender = new Player("Lion", "Tanto attaccano sempre me", 2);
        secondDefender = new Player("Sara", "Tanto attaccano sempre Alba", 3);
        thirdDefender = new Player("Rebecca",  "ma chi sono questi?", 4);
        //fourDefender = new Player("Giuseppino",  "ma chi sono?", 4);
        pba = new PlayerBoard(ColorPlayer.BLUE);
        pb1 = new PlayerBoard(ColorPlayer.YELLOW);
        pb2 = new PlayerBoard(ColorPlayer.GREEN);
        gameBoard = new GameBoard();
        gameBoard.chooseArena(4);

        attacker.setPlayerBoardAndColor(pba, ColorPlayer.BLUE);
        firstDefender.setPlayerBoardAndColor(pb1, ColorPlayer.YELLOW);
        secondDefender.setPlayerBoardAndColor(pb2, ColorPlayer.GREEN);
        thirdDefender.setPlayerBoardAndColor(pb3, ColorPlayer.VIOLET);
        //fourDefender.setPlayerBoardAndColor(pb4, ColorPlayer.GREY);

        gameBoard.insertPlayer(attacker, ColorRoom.BLUE);
        gameBoard.insertPlayer(firstDefender, ColorRoom.BLUE);
        gameBoard.insertPlayer(secondDefender, ColorRoom.RED);
        gameBoard.insertPlayer(thirdDefender, ColorRoom.RED);
        //gameBoard.insertPlayer(fourDefender, ColorRoom.RED);


        firstDefender.changePosition(0, 1, ColorRoom.BLUE);
        secondDefender.changePosition(0,1, ColorRoom.BLUE);
        thirdDefender.changePosition(0,0, ColorRoom.RED);
        //fourDefender.changePosition(0, 3, ColorRoom.GREEN);

        coordinates[0] = 1;
        coordinates[1] = 0;
        coordinates[2] = 0;
        coordinates[3] = 0;
        coordinates[4] = 3;
        coordinates[5] = 0;

        defenders.add(firstDefender);
        defenders.add(secondDefender);
        defenders.add(thirdDefender);
        //defenders.add(fourDefender);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFirstEffect() {
        try {
            card.firstEffect(gameBoard, attacker, defenders, coordinates);
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
            System.out.print("La X:" + firstDefender.getX() + " " + "La Y:" + firstDefender.getY());
            card.secondEffect(gameBoard, attacker, null, coordinates);
            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);
            ArrayList<Player> players = gameBoard.playersInOneSquare(coordinates[2], coordinates[3], null);


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