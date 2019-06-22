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

public class MachineGunTest {

    Player attacker, firstDefender, secondDefender, thirdDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2, pb3;
    MachineGun card = new MachineGun();
    ArrayList<Player> defenders = new ArrayList<>();
    int [] coordinates = new int[6];



    @Before
    public void setUp() throws Exception {
        attacker = new Player("Sara", "Speriamo che sto test vada", 1);
        firstDefender = new Player("Alba", "Speriamo che sto test vada", 2);
        secondDefender = new Player("Davide", "Speriamo che sto test vada", 3);
        thirdDefender = new Player("Sassari", "Basta test", 4);
        pba = new PlayerBoard(ColorPlayer.BLUE);
        pb1 = new PlayerBoard(ColorPlayer.YELLOW);
        pb2 = new PlayerBoard(ColorPlayer.GREEN);
        pb3 = new PlayerBoard(ColorPlayer.VIOLET);
        gameBoard = new GameBoard();
        gameBoard.chooseArena(3);

        attacker.setPlayerBoardAndColor(pba, ColorPlayer.BLUE);
        firstDefender.setPlayerBoardAndColor(pb1, ColorPlayer.YELLOW);
        secondDefender.setPlayerBoardAndColor(pb2, ColorPlayer.GREEN);
        thirdDefender.setPlayerBoardAndColor(pb3, ColorPlayer.VIOLET);

        gameBoard.insertPlayer(attacker, ColorRoom.BLUE);
        gameBoard.insertPlayer(firstDefender, ColorRoom.BLUE);
        gameBoard.insertPlayer(secondDefender, ColorRoom.BLUE);
        gameBoard.insertPlayer(thirdDefender, ColorRoom.BLUE);

        defenders.add(firstDefender);
        defenders.add(secondDefender);
        defenders.add(thirdDefender);

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

        assertEquals(1, pb1.numOfDamages());
        assertEquals(1, pb2.numOfDamages());


    }

    @Test
    public void secondEffect() throws DamageTrackException, ErrorEffectException {
        card.firstEffect(gameBoard, attacker, defenders, coordinates);
        card.secondEffect(gameBoard, attacker, defenders, coordinates);
        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        assertTrue(visiblePlayers.contains(firstDefender));
        assertTrue(visiblePlayers.contains(secondDefender));

        assertEquals(2, pb1.numOfDamages());


    }

    @Test
    public void thirdEffect() throws DamageTrackException, ErrorEffectException, NoEffectException {
        card.firstEffect(gameBoard, attacker, defenders, coordinates);
        card.thirdEffect(gameBoard, attacker, defenders, coordinates);
        ArrayList<Player> visiblePlayers = gameBoard.playersWhoCanSee(attacker);

        assertTrue(visiblePlayers.contains(firstDefender));
        assertTrue(visiblePlayers.contains(secondDefender));
        assertTrue(visiblePlayers.contains(thirdDefender));

        assertEquals(2, pb2.numOfDamages());
        assertEquals(1, pb3.numOfDamages());

    }
}