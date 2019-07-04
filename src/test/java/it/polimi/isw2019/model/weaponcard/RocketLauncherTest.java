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

public class RocketLauncherTest {

    Player attacker, firstDefender, secondDefender, thirdDefender, fourDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2, pb3, pb4;
    RocketLauncher card = new RocketLauncher();
    ArrayList<Player> defenders = new ArrayList<>();
    int [] coordinates = new int[6];

    @Before
    public void setUp() throws Exception {
        attacker = new Player("Davide", "Speriamo che sto test vada", 1);
        firstDefender = new Player("Alba", "Tanto attaccano sempre me", 2);
        secondDefender = new Player("Sara", "Aiuto", 3);
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

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void firstEffect() throws ErrorEffectException, DamageTrackException {
        card.firstEffect(gameBoard, attacker, defenders, coordinates);
        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        assertTrue(visiblePlayers.contains(firstDefender));
        assertTrue(visiblePlayers.contains(secondDefender));

        assertEquals(2, pb1.numOfDamages());
        assertEquals(0, pb2.numOfDamages());

        assertEquals(0, firstDefender.getX());
        assertEquals(0, firstDefender.getY());

    }

    @Test
    public void secondEffect() throws ErrorEffectException {
        System.out.println("X: " + attacker.getX() + " " + "Y: "+ attacker.getY());
        System.out.println(coordinates[2]+ " " + coordinates[3] + " " + gameBoard.isSquareAvailableOnArena(attacker, coordinates[2], coordinates[3]) );
        //gameBoard.changePositionPlayer(attacker, coordinates[2], coordinates[3]);
        System.out.println("X: " + attacker.getX() + " " + "Y: "+ attacker.getY());
        card.secondEffect(gameBoard, attacker, defenders, coordinates);

        assertEquals(2, attacker.getX());
        assertEquals(2, attacker.getY());
    }

    @Test
    public void thirdEffect() throws ErrorEffectException {
        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);
        System.out.println(defenders);
        System.out.println(visiblePlayers);
        System.out.println("X: " + thirdDefender.getX() + " " + "Y: "+ thirdDefender.getY());
        System.out.println("X: " + fourDefender.getX() + " " + "Y: "+ fourDefender.getY());
        card.thirdEffect(gameBoard, attacker, defenders, coordinates);


        assertTrue(visiblePlayers.contains(thirdDefender));
        assertTrue(visiblePlayers.contains(fourDefender));

        assertEquals(1, pb3.numOfDamages());
        assertEquals(1, pb4.numOfDamages());

    }
}