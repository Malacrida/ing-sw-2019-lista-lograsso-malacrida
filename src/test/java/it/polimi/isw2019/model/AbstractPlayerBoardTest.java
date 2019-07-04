package it.polimi.isw2019.model;

import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.OutOfBoundsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractPlayerBoardTest {
    PlayerBoard playerBoard1;
    PlayerBoard playerBoard2;

    @Before
    public void setUp() throws Exception {
        playerBoard1 = new PlayerBoard(ColorPlayer.YELLOW);
        playerBoard2= new PlayerBoard(ColorPlayer.GREEN);
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
        playerBoard1.addMark(ColorPlayer.VIOLET,2);
        assertEquals(2,playerBoard1.numOfMarkOfOneColor(ColorPlayer.VIOLET));

    }

    @Test
    public void numOfDamagesOfOneColor() {
        try {
            playerBoard1.takeDamage(ColorPlayer.GREEN,1);
            assertEquals(1,playerBoard1.numOfDamagesOfOneColor(ColorPlayer.GREEN));
        }

        catch (DamageTrackException e){
            fail();
        }


    }

    @Test
    public void listOfColorDamage() {
    }

    @Test
    public void removeMarkOfOneColor() {
        playerBoard1.addMark(ColorPlayer.YELLOW,2);
        assertEquals(2,playerBoard1.numOfMarkOfOneColor(ColorPlayer.YELLOW));

        playerBoard1.removeMarkOfOneColor(ColorPlayer.YELLOW);
        assertEquals(0,playerBoard1.numOfMarkOfOneColor(ColorPlayer.YELLOW));

    }

    @Test
    public void testTakeDamageOverKillWithMark() {
        /*playerBoard1.addMark(ColorPlayer.VIOLET,2);
        assertEquals(2,playerBoard1.numOfMarkOfOneColor(ColorPlayer.VIOLET));

        try {
            playerBoard1.takeDamage(ColorPlayer.GREEN, 3);
            assertEquals(3, playerBoard1.numOfDamagesOfOneColor(ColorPlayer.GREEN));


            playerBoard1.addMark(ColorPlayer.GREEN, 2);
            assertEquals(2, playerBoard1.numOfMarkOfOneColor(ColorPlayer.GREEN));

            playerBoard1.takeDamage(ColorPlayer.BLUE, 2);
            assertEquals(2, playerBoard1.numOfDamagesOfOneColor(ColorPlayer.BLUE));

            assertEquals(2, playerBoard1.numOfMarkOfOneColor(ColorPlayer.GREEN));
            playerBoard1.takeDamage(ColorPlayer.GREEN, 1);

            assertEquals(0, playerBoard1.numOfMarkOfOneColor(ColorPlayer.GREEN));
            assertEquals(6, playerBoard1.numOfDamagesOfOneColor(ColorPlayer.GREEN));
            assertEquals(2, playerBoard1.numOfMarkOfOneColor(ColorPlayer.VIOLET));
        }

        catch (DamageTrackException e){
            fail();
        }

        try {
            playerBoard1.takeDamage(ColorPlayer.VIOLET,3);

            fail();
        }

        catch (DamageTrackException e){

            assertEquals(4,playerBoard1.numOfDamagesOfOneColor(ColorPlayer.VIOLET));
            assertEquals(0,playerBoard1.numOfMarkOfOneColor(ColorPlayer.VIOLET));
            assertEquals(12,playerBoard1.numOfDamages());
            assertEquals(ColorPlayer.GREEN, playerBoard1.firstBlood());
        }
*/
    }

   @Test
   public void testTakeDamageKillShot (){
        try {
            playerBoard2.addMark(ColorPlayer.YELLOW,3);
            playerBoard2.takeDamage(ColorPlayer.YELLOW,2);
            playerBoard2.takeDamage(ColorPlayer.BLUE,3);
            playerBoard2.addMark(ColorPlayer.BLUE,1);
            playerBoard2.takeDamage(ColorPlayer.GREY,3);
        }

        catch (DamageTrackException e){
            assertEquals(1,playerBoard2.numOfMarkOfOneColor(ColorPlayer.BLUE));
            assertEquals(11,playerBoard2.numOfDamages());
            assertEquals(ColorPlayer.YELLOW, playerBoard2.firstBlood());

        }
   }

    @Test
    public void testTakeDamageOverKillWithDamage (){
    /*    try {
            playerBoard2.addMark(ColorPlayer.YELLOW,3);
            playerBoard2.takeDamage(ColorPlayer.YELLOW,2);
            playerBoard2.takeDamage(ColorPlayer.BLUE,2);
            playerBoard2.takeDamage(ColorPlayer.GREY,3);
            playerBoard2.addMark(ColorPlayer.BLUE,3);
            playerBoard2.takeDamage(ColorPlayer.BLUE,3);

        }

        catch (DamageTrackException e){
            assertEquals(0,playerBoard2.numOfMarkOfOneColor(ColorPlayer.BLUE));
//            assertEquals(12,playerBoard2.numOfDamages());
            assertEquals(ColorPlayer.YELLOW, playerBoard2.firstBlood());
            assertEquals(ColorPlayer.BLUE, playerBoard2.killShot());
        }*/
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

}