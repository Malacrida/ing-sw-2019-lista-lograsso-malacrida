package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.*;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.NoEffectException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FurnaceTest {

    Player attacker, firstDefender, secondDefender, thirdDefender, fourDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2, pb3, pb4;
    Furnace card = new Furnace();
    ArrayList<Player> defenders = new ArrayList<>();
    ArrayList<Player> defenders2 = new ArrayList<>();
    int [] coordinates = new int[6];
    int [] coordinates2 = new int[6];

    @Before
    public void setUp() throws Exception {
        attacker = new Player("Davide", "Speriamo che sto test vada", 1);
        firstDefender = new Player("Alba", "Tanto attaccano sempre me", 2);
        secondDefender = new Player("Garibaldi", "Aiuto", 3);
        thirdDefender = new Player("Pipino il breve", "Conquisterò tutto", 4);
        fourDefender = new Player("Napoleone", "VIVA LA FRANCIA", 5);
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
        gameBoard.changePositionPlayer(thirdDefender, 1, 2);
        gameBoard.changePositionPlayer(fourDefender, 1, 2);

        coordinates = new int[]{1, 2, 1, 2, 1, 2};
        coordinates2[0] = 1;
        coordinates2[1] = 1 ;

        defenders.add(firstDefender);
        defenders.add(secondDefender);
        defenders.add(thirdDefender);
        defenders.add(fourDefender);

        defenders2.add(null);
    }

    @Test
    public void firstEffect() throws ErrorEffectException, DamageTrackException {
        card.firstEffect(gameBoard, attacker, defenders, coordinates);
        assertEquals(1, pb1.numOfDamages());
        assertEquals(0, pb2.numOfDamages());
        assertEquals(1, pb3.numOfDamages());
        assertEquals(1, pb4.numOfDamages());

    }



    @Test
    public void secondEffect() throws ErrorEffectException, DamageTrackException {
        System.out.println(gameBoard.isSquareAvailableOnArena(attacker, coordinates[0], coordinates[1]));
        System.out.println(card.oneDistance(attacker.getX(), attacker.getY(), coordinates[0], coordinates[1]));
        System.out.println(gameBoard.playersInOneSquare(coordinates[0], coordinates[1], null));

        card.secondEffect(gameBoard, attacker, defenders, coordinates);

        assertEquals(1, pb1.numOfDamages());
        assertEquals(1, pb3.numOfDamages());
        assertEquals(1, pb1.numOfMarkOfOneColor(attacker.getColor()));
        assertEquals(1, pb3.numOfMarkOfOneColor(attacker.getColor()));
    }

    @Test (expected = ErrorEffectException.class)
    public void secondTestSecondEffect() throws ErrorEffectException, DamageTrackException {
        gameBoard.changePositionPlayer(firstDefender, 0, 2);
        gameBoard.changePositionPlayer(secondDefender, 0, 3);
        gameBoard.changePositionPlayer(thirdDefender, 0, 2);
        gameBoard.changePositionPlayer(fourDefender, 0, 2);
        card.secondEffect(gameBoard, attacker, defenders, coordinates);

    }

    @Test (expected = NoEffectException.class)
    public void thirdEffect() throws NoEffectException {
        card.thirdEffect(gameBoard, attacker, defenders, coordinates);
    }
}