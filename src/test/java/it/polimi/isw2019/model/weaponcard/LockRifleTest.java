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

public class LockRifleTest {

    Player attacker, firstDefender, secondDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2;
    LockRifle card = new LockRifle();
    int [] coordinates;
    ArrayList<Player> defenders = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        attacker = new Player("Davide", "Speriamo che sto test vada", 1);
        firstDefender = new Player("Alba", "Tanto attaccano sempre me", 2);
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
        coordinates = new int[]{-1, -1, 3, 2};

        defenders.add(firstDefender);
        defenders.add(secondDefender);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFirstEffect() throws ErrorEffectException, DamageTrackException {

        firstDefender.changePosition(2, 3, ColorRoom.YELLOW);
        card.firstEffect(gameBoard, attacker, defenders, coordinates);

    }

    @Test (expected = ErrorEffectException.class)
    public void secondTestFirstEffect() throws ErrorEffectException, DamageTrackException {
            gameBoard.changePositionPlayer(firstDefender, 0,1);
            gameBoard.changePositionPlayer(firstDefender, 1,1);
            card.firstEffect(gameBoard, attacker, defenders, coordinates);
    }

    @Test
    public void testSecondEffect() throws ErrorEffectException, DamageTrackException {
           card.firstEffect(gameBoard, attacker, defenders, coordinates);
           card.secondEffect(gameBoard,attacker,defenders,coordinates);
           System.out.println(defenders.get(1).getName());
           ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

           assertTrue(visiblePlayers.contains(defenders.get(1)));

           assertEquals(1, pb2.numOfMarkOfOneColor(attacker.getColor()));

    }

    @Test (expected = ErrorEffectException.class)
    public void secondTestSecondEffect() throws ErrorEffectException, DamageTrackException {
        card.firstIsValid = true;
        gameBoard.changePositionPlayer(attacker, 0, 1);
        gameBoard.changePositionPlayer(secondDefender, 0, 3);
        gameBoard.changePositionPlayer(secondDefender, 1, 3);
        card.secondEffect(gameBoard,attacker,defenders,coordinates);
    }

    /*@Test (expected = DamageTrackException.class)
    public void thirdTestSecondEffect() throws ErrorEffectException, DamageTrackException {
        card.firstIsValid = true;
        secondDefender.sufferDamageOrMark(attacker.getColor(), 0, 3);
        card.secondEffect(gameBoard,attacker,defenders,coordinates);
    }*/


    @Test (expected = NoEffectException.class)
    public void thirdEffect() throws NoEffectException {
        card.thirdEffect(gameBoard, attacker, defenders, coordinates);
    }
}