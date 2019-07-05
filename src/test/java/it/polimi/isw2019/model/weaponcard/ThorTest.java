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

public class ThorTest {

    Player attacker, firstDefender, secondDefender, thirdDefender, fourDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2, pb3, pb4;
    Thor card = new Thor();
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

        gameBoard.changePositionPlayer(firstDefender, 0, 1);
        gameBoard.changePositionPlayer(secondDefender, 0, 0);
        gameBoard.changePositionPlayer(thirdDefender, 0, 1);
        gameBoard.changePositionPlayer(fourDefender, 0, 1);

        coordinates = new int[]{0, 0, 1, 2, 2, 2};

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
        card.firstEffect(gameBoard, attacker, defenders, coordinates);
        assertEquals(2, pb1.numOfDamages());
        assertTrue(card.firstIsValid);
    }

    @Test
    public void secondEffect() throws ErrorEffectException, DamageTrackException {
        card.firstEffect(gameBoard, attacker, defenders, coordinates);
        card.secondEffect(gameBoard, attacker, defenders, coordinates);
        assertEquals(1, pb2.numOfDamages());
    }

    @Test(expected = ErrorEffectException.class)
    public void secondTestSecondEffect() throws ErrorEffectException, DamageTrackException {
        card.firstIsValid = false;
        card.secondEffect(gameBoard, attacker, defenders, coordinates);
    }

    @Test
    public void thirdEffect() throws ErrorEffectException, DamageTrackException {
        card.firstEffect(gameBoard, attacker, defenders, coordinates);
        card.secondEffect(gameBoard, attacker, defenders, coordinates);
        card.thirdEffect(gameBoard, attacker, defenders, coordinates);
        assertEquals(2, pb3.numOfDamages());
    }

    @Test(expected = ErrorEffectException.class)
    public void secondTestThirdEffect() throws ErrorEffectException, DamageTrackException {
        card.secondIsValid = false;
        card.thirdEffect(gameBoard, attacker, defenders, coordinates);
    }
}