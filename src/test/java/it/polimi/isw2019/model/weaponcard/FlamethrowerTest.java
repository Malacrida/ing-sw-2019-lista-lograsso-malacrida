package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class FlamethrowerTest {

    Player attacker, firstDefender, secondDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2;
    ArrayList<Player> defenders = new ArrayList<>();
    Flamethrower card = new Flamethrower();
    int [] coordinates = new int[4];

    @Before
    public void setUp() throws Exception {
        attacker = new Player("Alba", "Speriamo che sto test vada", 1);
        firstDefender = new Player("Lion", "Tanto attaccano sempre me", 2);
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
        gameBoard.insertPlayer(secondDefender, ColorRoom.RED);

        coordinates[2] = 2;
        coordinates[3] = 3;


        defenders.add(firstDefender);
        defenders.add(secondDefender);

    }

    @Test
    public void testFirstEffect() {
        /*try {
            card.firstEffect(gameBoard, attacker, firstDefender, secondDefender, null,-1, -1, -1, -1);
            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

            assertTrue(visiblePlayers.contains(firstDefender));
            assertFalse(visiblePlayers.contains(secondDefender));



        } catch (ErrorEffectException | DamageTrackException e) {
            e.printStackTrace();
        } catch (NoEffectException e) {
            e.printStackTrace();
        }

        try {
            assertEquals(1, pb1.numOfDamages());
            assertEquals(0, pb2.numOfDamages());
        } catch (exception e) {
            e.printStackTrace();
        }

        }*/
    }

    @Test
    public void secondEffect() {
    }

    @Test
    public void thirdEffect() {
    }
}