package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.Exception.ColorNotAvailableException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelTest {
    PlayerBoard playerBoard2;
    Player player1, player2;
    ColorPlayer colorPlayer1, colorPlayer2;
    Model model;
    @Before
    public void setUp() throws Exception {
        model = new Model();
        Player player1 = new Player("Sara", "uau" , 2);
        Player player2 = new Player("ALBA", "uau" , 3);
        ColorPlayer colorPlayer1 = ColorPlayer.BLUE;
        ColorPlayer colorPlayer2 = ColorPlayer.GREEN;
        model.gameSetting();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addPlayer() {

    }



    //riesco a prendere quella playerboard. NON FUNZIONA
    @Test
    public void testContainsColor() {
        try{

            assertEquals(model.containsColor(colorPlayer2), true);
            assertNotEquals(model.containsColor(colorPlayer1), true);
            model.setPlayerWithPlayerBoard(player1, colorPlayer1);

        }catch(ColorNotAvailableException e){
            fail();
        }
        try{

            model.setPlayerWithPlayerBoard(player2, colorPlayer1);
            fail();

        }catch(ColorNotAvailableException e){

        }

    }

    @Test
    public void positionPlayerBoardAvailable() {
        try{
            model.setPlayerWithPlayerBoard(player2, colorPlayer2);
            assertNotEquals(model.positionPlayerBoardAvailable(ColorPlayer.BLUE),2);
        }catch(ColorNotAvailableException e){
            //non dovrebbe fallire!!!
            fail();
        }
    }
}