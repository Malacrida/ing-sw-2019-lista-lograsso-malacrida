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

public class ShockWaveTest {

    Player attacker, firstDefender, secondDefender, thirdDefender, fourDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2, pb3, pb4;
    ShockWave card = new ShockWave();
    ArrayList<Player> defenders = new ArrayList<>();
    int [] coordinates = new int[6];

    @Before
    public void setUp() throws Exception {
        attacker = new Player("Davide", "Speriamo che sto test vada");
        firstDefender = new Player("Alba", "Tanto attaccano sempre me");
        secondDefender = new Player("Stavri", "Palestra, palestra, palestra");
        thirdDefender = new Player("Pipino il breve", "Conquisterò tutto");
        fourDefender = new Player("Napoleone", "VIVA LA FRANCIA");
        pba = new PlayerBoard(ColorPlayer.BLUE);
        pb1 = new PlayerBoard(ColorPlayer.YELLOW);
        pb2 = new PlayerBoard(ColorPlayer.GREEN);
        pb3 = new PlayerBoard(ColorPlayer.VIOLET);
        pb4= new PlayerBoard(ColorPlayer.GREY);
        gameBoard = new GameBoard();
        gameBoard.chooseArena(4);

        attacker.setPlayerBoardAndColor(pba, ColorPlayer.BLUE);
        firstDefender.setPlayerBoardAndColor(pb1, ColorPlayer.YELLOW);
        secondDefender.setPlayerBoardAndColor(pb2, ColorPlayer.VIOLET);
        thirdDefender.setPlayerBoardAndColor(pb3, ColorPlayer.GREEN);
        fourDefender.setPlayerBoardAndColor(pb4, ColorPlayer.GREY);

        gameBoard.insertPlayer(attacker, ColorRoom.BLUE);
        gameBoard.insertPlayer(firstDefender, ColorRoom.BLUE);
        gameBoard.insertPlayer(secondDefender, ColorRoom.BLUE);
        gameBoard.insertPlayer(thirdDefender, ColorRoom.BLUE);
        gameBoard.insertPlayer(fourDefender, ColorRoom.BLUE);

        gameBoard.changePositionPlayer(firstDefender, 1, 2);
        gameBoard.changePositionPlayer(secondDefender, 0, 3);
        gameBoard.changePositionPlayer(thirdDefender, 0, 1);
        gameBoard.changePositionPlayer(fourDefender, 0, 1);

        coordinates = new int[]{1, 2, 0, 3, 0, 1};

        defenders.add(firstDefender);
        defenders.add(secondDefender);
        defenders.add(thirdDefender);
        defenders.add(fourDefender);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void firstEffect() throws ErrorEffectException, DamageTrackException {
        gameBoard.changePositionPlayer(firstDefender, 0, 2);
        gameBoard.changePositionPlayer(firstDefender, 1, 2);
        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        card.firstEffect(gameBoard, attacker, defenders, coordinates);

        assertTrue(visiblePlayers.contains(firstDefender));
        assertTrue(visiblePlayers.contains(secondDefender));
        assertTrue(visiblePlayers.contains(thirdDefender));

        assertEquals(1, pb1.numOfDamages());
        assertEquals(1, pb2.numOfDamages());
        assertEquals(1, pb3.numOfDamages());
    }

    @Test (expected = ErrorEffectException.class)
    public void secondTestfirstEffect() throws ErrorEffectException, DamageTrackException {
        gameBoard.changePositionPlayer(thirdDefender, 1, 1);
        card.firstEffect(gameBoard, attacker, defenders, coordinates);
    }

    @Test
    public void secondEffect() throws ErrorEffectException, DamageTrackException {
        ArrayList<Player> firstSquare = gameBoard.playersInOneSquare(coordinates[0], coordinates[1], null);
        ArrayList<Player> secondSquare = gameBoard.playersInOneSquare(coordinates[2], coordinates[3], null);
        ArrayList<Player> thirdSquare = gameBoard.playersInOneSquare(coordinates[4], coordinates[5], null);

        card.secondEffect(gameBoard, attacker, defenders, coordinates);
        assertTrue(firstSquare.contains(firstDefender));
        assertTrue(secondSquare.contains(secondDefender));
        assertTrue(thirdSquare.contains(thirdDefender));
        assertTrue(thirdSquare.contains(fourDefender));

        assertEquals(1, pb1.numOfDamages());
        assertEquals(1, pb2.numOfDamages());
        assertEquals(1, pb3.numOfDamages());
        assertEquals(1, pb4.numOfDamages());
    }

    @Test (expected = NoEffectException.class)
    public void thirdEffect() throws NoEffectException {
        card.thirdEffect(gameBoard, attacker, defenders, coordinates);
    }
}