package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.*;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.NoEffectException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FlamethrowerTest {

    Player attacker, firstDefender, secondDefender, thirdDefender, fourDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2, pb3, pb4;
    Flamethrower card = new Flamethrower();
    ArrayList<Player> defenders = new ArrayList<>();
    int[] coordinates = new int[4];

    @Before
    public void setUp() throws Exception {
        attacker = new Player("Davide", "Riprendiamoci Fiume", 1);
        firstDefender = new Player("Alba", "Tanto attaccano sempre me", 2);
        secondDefender = new Player("Sara", "Aiuto", 3);
        thirdDefender = new Player("Pipino il breve", "Conquisterò tutto", 4);
        fourDefender = new Player("Napoleone", "VIVA LA FRANCIA", 5);
        pba = new PlayerBoard(ColorPlayer.BLUE);
        pb1 = new PlayerBoard(ColorPlayer.YELLOW);
        pb2 = new PlayerBoard(ColorPlayer.GREEN);
        pb3 = new PlayerBoard(ColorPlayer.VIOLET);
        pb4 = new PlayerBoard(ColorPlayer.GREY);
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
        gameBoard.changePositionPlayer(secondDefender, 1, 2);
        gameBoard.changePositionPlayer(secondDefender, 2, 2);
        gameBoard.changePositionPlayer(thirdDefender, 1, 2);
        gameBoard.changePositionPlayer(fourDefender, 1, 2);
        gameBoard.changePositionPlayer(fourDefender, 2, 2);

        coordinates = new int[]{1, 2, 2, 2};

        defenders.add(firstDefender);
        defenders.add(secondDefender);
        defenders.add(thirdDefender);
        defenders.add(fourDefender);
    }

    @Test
    public void firstEffect() throws ErrorEffectException, DamageTrackException {
        card.firstEffect(gameBoard, attacker, defenders, coordinates);
        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        assertTrue(visiblePlayers.contains(firstDefender));
        assertTrue(visiblePlayers.contains(secondDefender));
        assertTrue(visiblePlayers.contains(thirdDefender));
        assertTrue(visiblePlayers.contains(fourDefender));

        assertEquals(1, pb1.numOfDamages());
        assertEquals(1, pb2.numOfDamages());

    }

    @Test
    public void secondEffect() throws ErrorEffectException, DamageTrackException {
        card.secondEffect(gameBoard, attacker, defenders, coordinates);
        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        assertTrue(visiblePlayers.contains(firstDefender));
        assertTrue(visiblePlayers.contains(secondDefender));
        assertTrue(visiblePlayers.contains(thirdDefender));
        assertTrue(visiblePlayers.contains(fourDefender));


        assertEquals(2, pb1.numOfDamages());
        assertEquals(1, pb2.numOfDamages());
        assertEquals(2, pb3.numOfDamages());
        assertEquals(1, pb4.numOfDamages());



    }

    @Test (expected = NoEffectException.class)
    public void thirdEffect() throws NoEffectException {
        card.thirdEffect(gameBoard, attacker, defenders, coordinates);
    }
}
