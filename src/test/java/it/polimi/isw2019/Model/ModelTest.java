package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.Exception.ColorNotAvailableException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelTest {
    PlayerBoard playerBoard1, playerBoard2;
    Player player1, player2;
    ColorPlayer colorPlayer1, colorPlayer2;
    Model model;
    @Before
    public void setUp() throws Exception {
        model = new Model();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addPlayer() {

    }

    /*
    //riesco a prendere quella playerboard. NON FUNZIONA
    @Test
    public void testContainsColor() {
        try{
            model.setPlayer("Alba", "ehi",1,ColorPlayer.BLUE);
            assertEquals(model.containsColor(ColorPlayer.BLUE), false);
            fail();
        }catch(ColorNotAvailableException e){

        }
        try{
            //non mi dovrebbe creare il player
            model.setPlayer("Sara", "uau",2, ColorPlayer.BLUE);
            fail();

        }catch(ColorNotAvailableException e){

        }
        try{
            //non mi dovrebbe creare il player
            model.setPlayer("Davide", "uae",3, ColorPlayer.GREEN);
            assertEquals(model.containsColor(ColorPlayer.GREY), true);

        }catch(ColorNotAvailableException e){
            fail();
        }
    }*/


    @Test
    public void positionPlayerBoardAviable() {

    }
}