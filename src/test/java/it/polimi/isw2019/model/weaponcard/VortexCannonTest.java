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

public class VortexCannonTest {

    Player attacker, firstDefender, secondDefender, thirdDefender, fourDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2, pb3, pb4;
    VortexCannon card = new VortexCannon();
    ArrayList<Player> defenders = new ArrayList<>();
    ArrayList<Player> defenders2 = new ArrayList<>();
    int [] coordinates = new int[6];

    @Before
    public void setUp() throws Exception {
        attacker = new Player("Davide", "Speriamo che sto test vada", 1);
        firstDefender = new Player("Alba", "Tanto attaccano sempre me", 2);
        secondDefender = new Player("Sara", "Aiuto", 3);
        thirdDefender = new Player("Merkel", "Teteska!", 4);
        fourDefender = new Player("Trump", "Fate il muro!", 5);
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

        defenders2.add(null);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void firstEffect() throws ErrorEffectException, DamageTrackException {
        card.firstEffect(gameBoard, attacker, defenders, coordinates);
        assertEquals(2, pb1.numOfDamages());
    }

    @Test (expected = ErrorEffectException.class)
    public void secondTestfirstEffect() throws ErrorEffectException, DamageTrackException {
        card.firstEffect(gameBoard, attacker, defenders2, coordinates);
    }

    @Test (expected = ErrorEffectException.class)
    public void thirdTestfirstEffect() throws ErrorEffectException, DamageTrackException {
        coordinates = new int[] {0, 3, 2, 2, 1, 1};
        gameBoard.changePositionPlayer(firstDefender, 0, 0);
        System.out.println(card.oneDistance(firstDefender.getX(), firstDefender.getY(), coordinates[0], coordinates[1]));
        System.out.println(card.sameSquare(firstDefender.getX(), firstDefender.getY(), coordinates[0], coordinates[1]));
        card.firstEffect(gameBoard, attacker, defenders2, coordinates);
    }

    @Test
    public void secondEffect() throws ErrorEffectException, DamageTrackException {
        card.firstEffect(gameBoard, attacker, defenders, coordinates);
        gameBoard.changePositionPlayer(secondDefender, 0, 2);
        gameBoard.changePositionPlayer(secondDefender, 0, 1);
        gameBoard.changePositionPlayer(secondDefender, 0, 0);
        card.secondEffect(gameBoard, attacker, defenders, coordinates);
        assertEquals(2, pb1.numOfDamages());
        assertEquals(1, pb2.numOfDamages());
        assertEquals(1, pb3.numOfDamages());


    }

    @Test (expected = ErrorEffectException.class)
    public void secondTestSecondEffect() throws ErrorEffectException, DamageTrackException {
        card.firstIsValid = false;
        card.secondEffect(gameBoard, attacker, defenders, coordinates);

    }

    @Test
    public void thirdTestSecondEffect() throws ErrorEffectException, DamageTrackException {
        card.firstIsValid = true;
        gameBoard.changePositionPlayer(secondDefender, 0, 2);
        gameBoard.changePositionPlayer(secondDefender, 0, 1);
        gameBoard.changePositionPlayer(secondDefender, 0, 0);
        gameBoard.changePositionPlayer(firstDefender, 0, 2);
        card.secondEffect(gameBoard, attacker, defenders, coordinates);

    }

    @Test (expected = NoEffectException.class)
    public void thirdEffect() throws NoEffectException {
        card.thirdEffect(gameBoard, attacker, defenders, coordinates);
    }
}