package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ZX_2Test {

    Player attacker, firstDefender, secondDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2;
    ZX_2 card = new ZX_2();

    @Before
    public void setUp() throws Exception {
        attacker = new Player("Alba", "Speriamo che sto test vada", 1);
        firstDefender = new Player("Davide", "Aiuto, quante carte sono?", 2);
        secondDefender = new Player("Sara", "Tanto attaccano sempre Alba", 3);
        pba = new PlayerBoard(ColorPlayer.BLUE);
        pb1 = new PlayerBoard(ColorPlayer.YELLOW);
        pb2 = new PlayerBoard(ColorPlayer.GREEN);
        gameBoard = new GameBoard();
        gameBoard.chooseArena(4);

        attacker.setPlayerBoardAndColor(pba, ColorPlayer.BLUE);
        firstDefender.setPlayerBoardAndColor(pb1, ColorPlayer.YELLOW);
        secondDefender.setPlayerBoardAndColor(pb2, ColorPlayer.GREEN);

        gameBoard.insertPlayer(attacker, ColorRoom.RED);
        gameBoard.insertPlayer(firstDefender, ColorRoom.RED);
        gameBoard.insertPlayer(secondDefender, ColorRoom.RED);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void firstEffect() {
    }

    @Test
    public void secondEffect() {
    }

    @Test
    public void thirdEffect() {
    }
}