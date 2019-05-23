package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.*;
import it.polimi.isw2019.model.exception.DamageTrackException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MachineGunTest {

    Player attacker, firstDefender, secondDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2;
    MachineGun card = new MachineGun();


    @Before
    public void setUp() throws Exception {
        attacker = new Player("Sara", "Speriamo che sto test vada", 1);
        firstDefender = new Player("Alba", "Speriamo che sto test vada", 2);
        secondDefender = new Player("Davide", "Speriamo che sto test vada", 3);
        pba = new PlayerBoard(ColorPlayer.BLUE);
        pb1 = new PlayerBoard(ColorPlayer.YELLOW);
        pb2 = new PlayerBoard(ColorPlayer.GREEN);
        gameBoard = new GameBoard();
        gameBoard.chooseArena(3);

        attacker.setPlayerBoardAndColor(pba, ColorPlayer.BLUE);
        firstDefender.setPlayerBoardAndColor(pb1, ColorPlayer.YELLOW);
        secondDefender.setPlayerBoardAndColor(pb2, ColorPlayer.GREEN);

        gameBoard.insertPlayer(attacker, ColorRoom.BLUE);
        gameBoard.insertPlayer(firstDefender, ColorRoom.BLUE);
        gameBoard.insertPlayer(secondDefender, ColorRoom.BLUE);


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void firstEffect() {
    }

    @Test
    public void secondEffect() throws DamageTrackException {

    }

    @Test
    public void thirdEffect() throws DamageTrackException {

    }
}