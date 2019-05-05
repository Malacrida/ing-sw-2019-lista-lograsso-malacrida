package it.polimi.isw2019.Server.Model;

import it.polimi.isw2019.Server.Model.AbstractPlayerBoard;
import it.polimi.isw2019.Server.Model.ColorPlayer;
import it.polimi.isw2019.Server.Model.Exception.OutOfBoundsException;
import it.polimi.isw2019.Server.Model.PlayerBoard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractPlayerBoardTest {
    AbstractPlayerBoard playerBoard1;

    @Before
    public void setUp() throws Exception {
        playerBoard1 = new PlayerBoard(ColorPlayer.BLUE);
    }

    @After
    public void tearDown() throws Exception {
    }


    //non puoì avere piu di tre cubi
    @Test
    public void testAddRedCubes(){


        try {
            playerBoard1.addRedCubes();
            //assertEquals(2,playerBoard1.getRedCubes());

        } catch (OutOfBoundsException e) {
            fail();
        }
        try {
            playerBoard1.addRedCubes();
            //assertEquals(3,playerBoard1.getRedCubes());
        } catch (OutOfBoundsException e) {
            fail();
        }
        try {
            playerBoard1.addRedCubes();
            fail();
        } catch (OutOfBoundsException e) {

        }

    }

    @Test
    public void addYellowCubes() {
    }

    @Test
    public void addBlueCubes() {
    }

    @Test
    public void testRemoveRedCubes() {

        try {

            playerBoard1.removeRedCubes(1);
            //assertEquals(0,playerBoard1.getRedCubes());

        } catch (OutOfBoundsException e) {
            fail();
        }

        try {
            playerBoard1.addRedCubes();
            playerBoard1.addRedCubes();
            playerBoard1.addRedCubes();
            playerBoard1.removeRedCubes(3);
            //assertEquals(0,playerBoard1.getRedCubes());

        } catch (OutOfBoundsException e) {
            fail();
        }

        try {
            playerBoard1.addRedCubes();
            playerBoard1.addRedCubes();
            playerBoard1.removeRedCubes(3);
            fail();

        } catch (OutOfBoundsException e) {

        }

    }

    @Test
    public void removeYellowCubes() {

    }

    @Test
    public void removeBlueCubes() {

    }

    @Test
    public void getPlayerSkulls() {

    }

    @Test
    public void addPlayerSkulls() {

    }

    @Test
    public void numOfDamanges() {
    }

    @Test
    public void numOfMarkOfOneColor() {
    }

    @Test
    public void numOfDamagesOfOneColor() {

    }

    @Test
    public void listOfColorDamage() {
    }

    @Test
    public void removeMarkOfOneColor() {
    }

    @Test
    public void testTakeDamage() {
        playerBoard1.addMark(ColorPlayer.VIOLET,2);
        assertEquals(2,playerBoard1.numOfMarkOfOneColor(ColorPlayer.VIOLET));
        playerBoard1.takeDamage(ColorPlayer.GREEN,1);
        assertEquals(1,playerBoard1.numOfDamagesOfOneColor(ColorPlayer.GREEN));
        playerBoard1.takeDamage(ColorPlayer.BLUE, 2);
        assertEquals(2,playerBoard1.numOfDamagesOfOneColor(ColorPlayer.BLUE));
        playerBoard1.takeDamage(ColorPlayer.GREEN,3);
        assertEquals(4,playerBoard1.numOfDamagesOfOneColor(ColorPlayer.GREEN));
    }

    @Test
    public void testAddMark() {
        //aspetto 0 marchi( non so se e' corretto)
        assertEquals(0,playerBoard1.numOfMarkOfOneColor(ColorPlayer.BLUE));

        //try catch per il mark. Assumi che il valore passato sia corretto?
        //try catch per veder SE si possono inserire mark
        playerBoard1.addMark(ColorPlayer.BLUE,3);
        assertEquals(3,playerBoard1.numOfMarkOfOneColor(ColorPlayer.BLUE));

    }

    @Test
    public void deathPlayer() {
    }

    @Test
    public void firstBlood() {
    }
}