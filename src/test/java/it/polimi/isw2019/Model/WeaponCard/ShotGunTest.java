package it.polimi.isw2019.Model.WeaponCard;

import it.polimi.isw2019.Model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShotGunTest {

    Player attacker, firstDefender, secondDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2;
    ShotGun card = new ShotGun();

    @Before
    public void setUp() throws Exception {
        attacker = new Player("Alba", "Tanto attaccano sempre me", 1);
        firstDefender = new Player("Mirjam", "Che è sto gioco?", 2);
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