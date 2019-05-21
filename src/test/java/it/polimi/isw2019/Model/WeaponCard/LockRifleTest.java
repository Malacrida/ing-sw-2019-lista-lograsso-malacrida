package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.*;
import it.polimi.isw2019.Model.Exception.DamageTrackException;
import it.polimi.isw2019.Model.Exception.ErrorEffectException;
import it.polimi.isw2019.Model.WeaponCard.LockRifle;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LockRifleTest {

    Player attacker, firstDefender, secondDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2;
    LockRifle card = new LockRifle();

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

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testFirstEffect() {
        try {
            gameBoard.insertPlayer(attacker, ColorRoom.BLUE);
            gameBoard.insertPlayer(firstDefender, ColorRoom.BLUE);
            gameBoard.insertPlayer(secondDefender, ColorRoom.RED);

            card.firstEffect(gameBoard, attacker, firstDefender, secondDefender, null,-1, -1, -1, -1);
            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

            assertTrue(visiblePlayers.contains(firstDefender));

        } catch (ErrorEffectException e) {
            e.printStackTrace();
        } catch (DamageTrackException e) {
            e.printStackTrace();
        }
        try {
            assertEquals(2, pb1.numOfDamages());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testSecondEffect() {
       try {
           // assertEquals(1, pb2.numOfMarkOfOneColor(attacker.getColor()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testThirdEffect() {
    }
}