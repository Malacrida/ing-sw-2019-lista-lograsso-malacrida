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

public class HellionTest {

    Player attacker, firstDefender, secondDefender, thirdDefender, fourDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2, pb3, pb4;
    Hellion card = new Hellion();
    ArrayList<Player> defenders = new ArrayList<>();
    int [] coordinates = new int[6];

    @Before
    public void setUp() throws Exception {

        attacker = new Player("Alba", "Speriamo che sto test vada", 1);
        firstDefender = new Player("Lion", "Tanto attaccano sempre me", 2);
        secondDefender = new Player("Giovanni", "Tanto attaccano sempre Alba", 3);
        thirdDefender = new Player("Alessia",  "ma chi sono?", 4);
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
        gameBoard.insertPlayer(firstDefender, ColorRoom.RED);
        gameBoard.insertPlayer(secondDefender, ColorRoom.RED);
        gameBoard.insertPlayer(thirdDefender, ColorRoom.RED);
        //gameBoard.insertPlayer(fourDefender, ColorRoom.RED);

        coordinates[0] = 1;
        coordinates[1] = 0;
        coordinates[2] = 0;
        coordinates[3] = 0;
        coordinates[4] = 3;
        coordinates[5] = 0;

        firstDefender.changePosition(0, 0, ColorRoom.RED);
        secondDefender.changePosition(0,0, ColorRoom.RED);
        thirdDefender.changePosition(0,0, ColorRoom.RED);

        defenders.add(firstDefender);
        defenders.add(secondDefender);
        defenders.add(thirdDefender);
        //defenders.add(fourDefender);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void firstEffect() {
        try{
            card.firstEffect(gameBoard, attacker, defenders, coordinates);

            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);
            ArrayList<Player> inOneSquare = gameBoard.playersInOneSquare(firstDefender.getX(), firstDefender.getY(), null);

            for (Player visiblePlayer : visiblePlayers) {

                System.out.println("Nome: " + visiblePlayer.getName());

            }

            for (Player playersInOneSquare : inOneSquare) {

                System.out.println("Nome: " + playersInOneSquare.getName());

            }

            assertTrue(visiblePlayers.contains(firstDefender));
            assertTrue(visiblePlayers.contains(secondDefender));
            assertTrue(visiblePlayers.contains(thirdDefender));

            assertEquals(1, pb1.numOfDamages());

            assertEquals(1, pb2.numOfMarkOfOneColor(ColorPlayer.BLUE));
            assertEquals(1, pb3.numOfMarkOfOneColor(ColorPlayer.BLUE));




        } catch (ErrorEffectException | DamageTrackException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void secondEffect() {
    }

    @Test
    public void thirdEffect() {
    }
}