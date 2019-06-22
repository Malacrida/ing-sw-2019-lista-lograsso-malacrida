package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.*;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class FlamethrowerTest {

    Player attacker, firstDefender, secondDefender, thirdDefender, fourDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2, pb3, pb4;
    ArrayList<Player> defenders = new ArrayList<>();
    Flamethrower card = new Flamethrower();
    int [] coordinates = new int[6];

    @Before
    public void setUp() throws Exception {
        attacker = new Player("Alba", "Speriamo che sto test vada", 1);
        firstDefender = new Player("Lion", "Tanto attaccano sempre me", 2);
        secondDefender = new Player("Sara", "Tanto attaccano sempre Alba", 3);
        thirdDefender = new Player("Alessia",  "ma chi sono?", 4);
        fourDefender = new Player("Giuseppino",  "ma chi sono?", 4);
        pba = new PlayerBoard(ColorPlayer.BLUE);
        pb1 = new PlayerBoard(ColorPlayer.YELLOW);
        pb2 = new PlayerBoard(ColorPlayer.GREEN);
        gameBoard = new GameBoard();
        gameBoard.chooseArena(4);

        attacker.setPlayerBoardAndColor(pba, ColorPlayer.BLUE);
        firstDefender.setPlayerBoardAndColor(pb1, ColorPlayer.YELLOW);
        secondDefender.setPlayerBoardAndColor(pb2, ColorPlayer.GREEN);
        thirdDefender.setPlayerBoardAndColor(pb3, ColorPlayer.VIOLET);
        fourDefender.setPlayerBoardAndColor(pb4, ColorPlayer.GREY);

        gameBoard.insertPlayer(attacker, ColorRoom.BLUE);
        gameBoard.insertPlayer(firstDefender, ColorRoom.BLUE);
        gameBoard.insertPlayer(secondDefender, ColorRoom.RED);
        gameBoard.insertPlayer(thirdDefender, ColorRoom.RED);
        gameBoard.insertPlayer(fourDefender, ColorRoom.RED);


        firstDefender.changePosition(0, 1, ColorRoom.BLUE);
        secondDefender.changePosition(0,1, ColorRoom.BLUE);
        thirdDefender.changePosition(0,0, ColorRoom.RED);
        fourDefender.changePosition(0, 3, ColorRoom.GREEN);

        coordinates[0] = 1;
        coordinates[1] = 0;
        coordinates[2] = 0;
        coordinates[3] = 0;
        coordinates[4] = 3;
        coordinates[5] = 0;

        defenders.add(firstDefender);
        defenders.add(secondDefender);
        defenders.add(thirdDefender);
        defenders.add(fourDefender);

    }

    @Test
    public void testFirstEffect() {
        try {
            card.firstEffect(gameBoard, attacker, defenders, coordinates);
            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

            assertTrue(visiblePlayers.contains(firstDefender));
            assertFalse(visiblePlayers.contains(secondDefender));

            assertEquals(1, pb1.numOfDamages());
            assertEquals(1, pb2.numOfDamages());


        } catch (ErrorEffectException | DamageTrackException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void secondEffect() {
        try {
            card.firstEffect(gameBoard, attacker, defenders, coordinates);
            ArrayList<Player> playersInOneSqaure = gameBoard.playersInOneSquare(coordinates[0], coordinates[1], secondDefender);

            System.out.println("Primo player: " + playersInOneSqaure.get(0).getName() + "Secondo player: " + playersInOneSqaure.get(1).getName() + "Terzo player: " + playersInOneSqaure.get(0).getName());

            System.out.println("la x: " + firstDefender.getX());
            System.out.println("La y: " + firstDefender.getY());

            assertTrue(playersInOneSqaure.contains(firstDefender));
            assertTrue(playersInOneSqaure.contains(secondDefender));


            assertEquals(2, pb1.numOfDamages());
            assertEquals(2, pb2.numOfDamages());
            assertEquals(1, pb1.numOfDamages());
            assertEquals(0, pb2.numOfDamages());

        } catch (ErrorEffectException | DamageTrackException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void thirdEffect() {
    }
}