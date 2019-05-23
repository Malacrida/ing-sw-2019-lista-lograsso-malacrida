package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.*;
import org.junit.Before;
import org.junit.Test;

public class FlamethrowerTest {

    Player attacker, firstDefender, secondDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2;
    Flamethrower card = new Flamethrower();

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
        gameBoard.insertPlayer(secondDefender, ColorRoom.BLUE);
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