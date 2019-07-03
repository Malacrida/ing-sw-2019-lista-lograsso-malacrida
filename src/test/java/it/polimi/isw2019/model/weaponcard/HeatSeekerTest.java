package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.*;
import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.ErrorEffectException;
import it.polimi.isw2019.model.exception.NoEffectException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HeatSeekerTest {
    Player attacker, firstDefender, secondDefender, thirdDefender, fourDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2, pb3, pb4;
    HeatSeeker card = new HeatSeeker();
    ArrayList<Player> defenders = new ArrayList<>();
    int [] coordinates = new int[4];

    @Before
    public void setUp() throws Exception {
        attacker = new Player("Alba", "Speriamo che sto test vada", 1);
        firstDefender = new Player("Lion", "Tanto attaccano sempre me", 2);
        secondDefender = new Player("Sara", "Tanto attaccano sempre Alba", 3);
        thirdDefender = new Player("Asdrubale",  "ma chi sono?", 4);
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



        firstDefender.changePosition(2, 0, ColorRoom.BLUE);

        coordinates[0] = 1;
        coordinates[1] = 0;
        coordinates[2] = 0;
        coordinates[3] = 0;

        defenders.add(firstDefender);


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void firstEffect() throws ErrorEffectException, DamageTrackException {

        /*try{
            card.firstEffect(gameBoard, attacker, defenders, coordinates);


            ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);


            assertFalse(visiblePlayers.contains(firstDefender));


            assertEquals(3, pb1.numOfDamages());



        } catch (ErrorEffectException e) {
            e.printStackTrace();
        }*/
    }

    @Test (expected = NoEffectException.class)
    public void secondEffect() throws NoEffectException {
        card.secondEffect(gameBoard, attacker, defenders, coordinates);
    }

    @Test (expected = NoEffectException.class)
    public void thirdEffect() throws NoEffectException {
        card.thirdEffect(gameBoard, attacker, defenders, coordinates);
    }
}