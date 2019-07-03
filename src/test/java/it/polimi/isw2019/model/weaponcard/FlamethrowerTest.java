package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FlamethrowerTest {

    Player attacker, firstDefender, secondDefender, thirdDefender, fourDefender;
    GameBoard gameBoard;
    PlayerBoard pba, pb1, pb2, pb3, pb4;
    Flamethrower card = new Flamethrower();
    ArrayList<Player> defenders = new ArrayList<>();
    int[] coordinates = new int[6];

    @Before
    public void setUp() throws Exception {
        attacker = new Player("Davide", "Riprendiamoci Fiume", 1);
        firstDefender = new Player("Alba", "Tanto attaccano sempre me", 2);
        secondDefender = new Player("Sara", "Aiuto", 3);
        thirdDefender = new Player("Pipino il breve", "Conquisterò tutto", 4);
        fourDefender = new Player("Napoleone", "VIVA LA FRANCIA", 5);
        pba = new PlayerBoard(ColorPlayer.BLUE);
        pb1 = new PlayerBoard(ColorPlayer.YELLOW);
        pb2 = new PlayerBoard(ColorPlayer.GREEN);
        pb3 = new PlayerBoard(ColorPlayer.VIOLET);
        pb4 = new PlayerBoard(ColorPlayer.GREY);
        gameBoard = new GameBoard();
        gameBoard.chooseArena(4);

        attacker.setPlayerBoardAndColor(pba, ColorPlayer.BLUE);
        firstDefender.setPlayerBoardAndColor(pb1, ColorPlayer.YELLOW);
        secondDefender.setPlayerBoardAndColor(pb2, ColorPlayer.VIOLET);
        thirdDefender.setPlayerBoardAndColor(pb3, ColorPlayer.GREEN);
        fourDefender.setPlayerBoardAndColor(pb4, ColorPlayer.GREY);

        gameBoard.insertPlayer(attacker, ColorRoom.BLUE);
        gameBoard.insertPlayer(firstDefender, ColorRoom.BLUE);
        gameBoard.insertPlayer(secondDefender, ColorRoom.BLUE);
        gameBoard.insertPlayer(thirdDefender, ColorRoom.BLUE);
        gameBoard.insertPlayer(fourDefender, ColorRoom.BLUE);

        gameBoard.changePositionPlayer(firstDefender, 0, 1);
        gameBoard.changePositionPlayer(secondDefender, 0, 3);
        gameBoard.changePositionPlayer(thirdDefender, 0, 1);
        gameBoard.changePositionPlayer(fourDefender, 0, 1);

        coordinates = new int[]{0, 0, 1, 2, 2, 2};

        defenders.add(firstDefender);
        defenders.add(secondDefender);
        defenders.add(thirdDefender);
        defenders.add(fourDefender);
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
