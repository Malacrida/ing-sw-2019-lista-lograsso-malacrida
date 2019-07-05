package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.*;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.NoEffectException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HellionTest {
    Player attacker, firstDefender, secondDefender, thirdDefender, fourDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2, pb3, pb4;
    Hellion card = new Hellion();
    ArrayList<Player> defenders = new ArrayList<>();
    ArrayList<Player> defenders2 = new ArrayList<>();
    int [] coordinates = new int[6];

    @Before
    public void setUp() throws Exception {
        attacker = new Player("Davide", "Speriamo che sto test vada");
        firstDefender = new Player("Alba", "Tanto attaccano sempre me");
        secondDefender = new Player("Superman", "Una S grazie");
        thirdDefender = new Player("Pinocchio", "Non ho mai mentito");
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
        gameBoard.changePositionPlayer(secondDefender, 0, 3);
        gameBoard.changePositionPlayer(thirdDefender, 0, 1);
        gameBoard.changePositionPlayer(fourDefender, 0, 1);

        coordinates = new int[]{0, 0, 1, 2, 2, 2};

        defenders.add(firstDefender);
        defenders.add(secondDefender);
        defenders.add(thirdDefender);
        defenders.add(fourDefender);
    }

    @Test
    public void firstEffect() throws ErrorEffectException, DamageTrackException {
        card.firstEffect(gameBoard, attacker, defenders, coordinates);
        assertEquals(1, pb1.numOfDamages());
        assertEquals(1, pb1.numOfMarkOfOneColor(attacker.getColor()));
        assertEquals(1, pb3.numOfMarkOfOneColor(attacker.getColor()));
        assertEquals(1, pb4.numOfMarkOfOneColor(attacker.getColor()));
    }

    @Test(expected = ErrorEffectException.class)
    public void secondTestFirstEffect() throws ErrorEffectException, DamageTrackException {
        gameBoard.changePositionPlayer(firstDefender, 0, 2);
        card.firstEffect(gameBoard, attacker, defenders, coordinates);
    }

    @Test
    public void secondEffect() throws ErrorEffectException, DamageTrackException {
        card.secondEffect(gameBoard, attacker, defenders, coordinates);
        assertEquals(1, pb1.numOfDamages());
        assertEquals(2, pb1.numOfMarkOfOneColor(attacker.getColor()));
        assertEquals(2, pb3.numOfMarkOfOneColor(attacker.getColor()));
        assertEquals(2, pb4.numOfMarkOfOneColor(attacker.getColor()));
    }

    @Test(expected = ErrorEffectException.class)
    public void secondTestSecondEffect() throws ErrorEffectException, DamageTrackException {
        card.secondEffect(gameBoard, attacker, defenders2, coordinates);
    }

    @Test (expected = NoEffectException.class)
    public void thirdEffect() throws NoEffectException {
        card.thirdEffect(gameBoard, attacker, defenders, coordinates);
    }
}