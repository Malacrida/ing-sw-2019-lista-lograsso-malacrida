package it.polimi.isw2019.model;

import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.NoCubesException;
import it.polimi.isw2019.model.exception.OutOfBoundsException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class PlayerBoardTest {

    Model model;

    PlayerBoard playerBoard1;
    PlayerBoard playerBoard2;
    PlayerBoard playerBoard3;

    Player player1;
    Player player2;
    Player player3;

    GameBoard gameBoard;

    @Before
    public void setUp() throws Exception {
        Model model = new Model();
        model.setGameBoard(gameBoard);
        player1 = new Player("Sara","ehi");
        player2 = new Player("Alba","ohi");
        player3 = new Player("Giove","uhu");
        model.addPlayer("Sara","ehi");
        model.addPlayer("Alba", "ohi");
        model.addPlayer("Giove", "uhu");
        playerBoard1 = new PlayerBoard(ColorPlayer.BLUE);
        playerBoard2= new PlayerBoard(ColorPlayer.GREEN);
        playerBoard3 = new PlayerBoard(ColorPlayer.VIOLET);
        model.setPlayerWithPlayerBoard(player1,ColorPlayer.BLUE);
        model.setPlayerWithPlayerBoard(player2,ColorPlayer.VIOLET);
        model.setPlayerWithPlayerBoard(player3,ColorPlayer.GREEN);
        model.setGame(1,5,0 );


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getColor() {
    }

    @Test
    public void testTakeDamage(){
        try {
            playerBoard1.takeDamage(ColorPlayer.GREEN, 5);
            assertEquals(5,playerBoard1.getDamageTokens().size());
        }catch(DamageTrackException e){
            fail();
        }

        try {
            playerBoard1.takeDamage(ColorPlayer.GREEN,5);
        } catch (DamageTrackException e) {
            fail();
        }
        try {
            playerBoard1.takeDamage(ColorPlayer.GREEN, 2);
            fail();
        }catch(DamageTrackException e){

        }
    }


    @Test
    public void testRemoveRedCubes() {

        try {

            playerBoard1.removeRedCubes(1);
            //assertEquals(0,playerBoard1.getRedCubes());

        } catch (OutOfBoundsException e) {
            Assert.fail();
        }

        try {
            playerBoard1.addRedCubes();
            playerBoard1.addRedCubes();
            playerBoard1.addRedCubes();
            playerBoard1.removeRedCubes(3);
            //assertEquals(0,playerBoard1.getRedCubes());

        } catch (OutOfBoundsException e) {
            Assert.fail();
        }

        try {
            playerBoard1.addRedCubes();
            playerBoard1.addRedCubes();
            playerBoard1.removeRedCubes(3);
            Assert.fail();

        } catch (OutOfBoundsException e) {

        }

    }

    @Test
    public void testRedCubes(){


        try {
            playerBoard1.addRedCubes();
            assertEquals(2,playerBoard1.numOfRedCubes());

        } catch (OutOfBoundsException e) {
            Assert.fail();
        }
        try {
            playerBoard1.addRedCubes();
            assertEquals(3,playerBoard1.numOfRedCubes());
        } catch (OutOfBoundsException e) {
            Assert.fail();
        }
        try {
            playerBoard1.addRedCubes();
            Assert.fail();
        } catch (OutOfBoundsException e) {

        }

        try {
            playerBoard1.removeRedCubes(4);
            fail();
        }catch (OutOfBoundsException e){

        }

        try {
            assertEquals(3, playerBoard1.numOfRedCubes());
            playerBoard1.removeCube(ColorCube.RED);
            assertEquals(2,playerBoard1.numOfRedCubes());
        }catch (NoCubesException e){
            fail();
        }
    }

    @Test
    public void testBlueCubes(){


        try {
            playerBoard1.addBlueCubes();
            assertEquals(2,playerBoard1.numOfBlueCubes());

        } catch (OutOfBoundsException e) {
            Assert.fail();
        }
        try {
            playerBoard1.addBlueCubes();
            assertEquals(3,playerBoard1.numOfBlueCubes());
        } catch (OutOfBoundsException e) {
            Assert.fail();
        }
        try {
            playerBoard1.addBlueCubes();
            Assert.fail();
        } catch (OutOfBoundsException e) {

        }

        try {
            playerBoard1.removeBlueCubes(4);
            fail();
        }catch (OutOfBoundsException e){

        }

        try {
            assertEquals(3, playerBoard1.numOfBlueCubes());
            playerBoard1.removeCube(ColorCube.BLUE);
            assertEquals(2,playerBoard1.numOfBlueCubes());
        }catch (NoCubesException e){
            fail();
        }
    }

    @Test
    public void testYellowCubes(){


        try {
            playerBoard1.addYellowCubes();
            assertEquals(2,playerBoard1.numOfYellowCubes());

        } catch (OutOfBoundsException e) {
            Assert.fail();
        }
        try {
            playerBoard1.addYellowCubes();
            assertEquals(3,playerBoard1.numOfYellowCubes());
        } catch (OutOfBoundsException e) {
            Assert.fail();
        }
        try {
            playerBoard1.addYellowCubes();
            Assert.fail();
        } catch (OutOfBoundsException e) {

        }

        try {
            playerBoard1.removeYellowCubes(4);
            fail();
        }catch (OutOfBoundsException e){

        }

        try {
            assertEquals(3, playerBoard1.numOfYellowCubes());
            playerBoard1.removeCube(ColorCube.YELLOW);
            assertEquals(2,playerBoard1.numOfYellowCubes());
        }catch (NoCubesException e){
            fail();
        }
    }

    @Test
    public void numOfDamagesOfOneColor() {
        try {
            playerBoard1.takeDamage(ColorPlayer.GREEN,1);
            Assert.assertEquals(1,playerBoard1.numOfDamagesOfOneColor(ColorPlayer.GREEN));
        }

        catch (DamageTrackException e){
            Assert.fail();
        }


    }

    @Test
    public void numOfMarkOfOneColor() {
        playerBoard1.addMark(ColorPlayer.VIOLET,2);
        Assert.assertEquals(2,playerBoard1.numOfMarkOfOneColor(ColorPlayer.VIOLET));

    }

    @Test
    public void listOfColorDamage() {
    }

    @Test
    public void removeMarkOfOneColor() {
        playerBoard1.addMark(ColorPlayer.YELLOW,2);
        Assert.assertEquals(2,playerBoard1.numOfMarkOfOneColor(ColorPlayer.YELLOW));

        playerBoard1.removeMarkOfOneColor(ColorPlayer.YELLOW);
        Assert.assertEquals(0,playerBoard1.numOfMarkOfOneColor(ColorPlayer.YELLOW));

    }

    @Test
    public void testTakeDamageOverKillWithMark() {
        playerBoard1.addMark(ColorPlayer.VIOLET,2);
        Assert.assertEquals(2,playerBoard1.numOfMarkOfOneColor(ColorPlayer.VIOLET));

        try {
            playerBoard1.takeDamage(ColorPlayer.GREEN, 3);
            Assert.assertEquals(3, playerBoard1.numOfDamagesOfOneColor(ColorPlayer.GREEN));


            playerBoard1.addMark(ColorPlayer.GREEN, 2);
            Assert.assertEquals(2, playerBoard1.numOfMarkOfOneColor(ColorPlayer.GREEN));

            playerBoard1.takeDamage(ColorPlayer.BLUE, 2);
            Assert.assertEquals(2, playerBoard1.numOfDamagesOfOneColor(ColorPlayer.BLUE));

            Assert.assertEquals(2, playerBoard1.numOfMarkOfOneColor(ColorPlayer.GREEN));
            playerBoard1.takeDamage(ColorPlayer.GREEN, 1);

            Assert.assertEquals(0, playerBoard1.numOfMarkOfOneColor(ColorPlayer.GREEN));
            Assert.assertEquals(6, playerBoard1.numOfDamagesOfOneColor(ColorPlayer.GREEN));
            Assert.assertEquals(2, playerBoard1.numOfMarkOfOneColor(ColorPlayer.VIOLET));
        }

        catch (DamageTrackException e){
            Assert.fail();
        }

        try {
            playerBoard1.takeDamage(ColorPlayer.VIOLET,3);

            Assert.fail();
        }

        catch (DamageTrackException e){

            Assert.assertEquals(4,playerBoard1.numOfDamagesOfOneColor(ColorPlayer.VIOLET));
            Assert.assertEquals(0,playerBoard1.numOfMarkOfOneColor(ColorPlayer.VIOLET));
            Assert.assertEquals(12,playerBoard1.numOfDamages());
            Assert.assertEquals(ColorPlayer.GREEN, playerBoard1.firstBlood());
        }

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
            Assert.assertEquals(1,playerBoard2.numOfMarkOfOneColor(ColorPlayer.BLUE));
            Assert.assertEquals(11,playerBoard2.numOfDamages());
            Assert.assertEquals(ColorPlayer.YELLOW, playerBoard2.firstBlood());

        }
    }

    @Test
    public void testTakeDamageOverKillWithDamage (){
        try {
            playerBoard2.addMark(ColorPlayer.YELLOW,3);
            playerBoard2.takeDamage(ColorPlayer.YELLOW,2);
            playerBoard2.takeDamage(ColorPlayer.BLUE,2);
            playerBoard2.takeDamage(ColorPlayer.GREY,3);
            playerBoard2.addMark(ColorPlayer.BLUE,3);
            playerBoard2.takeDamage(ColorPlayer.BLUE,3);

        }

        catch (DamageTrackException e){
            Assert.assertEquals(0,playerBoard2.numOfMarkOfOneColor(ColorPlayer.BLUE));
            Assert.assertEquals(12,playerBoard2.numOfDamages());
            Assert.assertEquals(ColorPlayer.YELLOW, playerBoard2.firstBlood());
            Assert.assertEquals(ColorPlayer.BLUE, playerBoard2.killShot());
        }
    }


    @Test
    public void testAddMark() {
        //aspetto 0 marchi( non so se e' corretto)
        Assert.assertEquals(0,playerBoard1.numOfMarkOfOneColor(ColorPlayer.BLUE));

        //try catch per il mark. Assumi che il valore passato sia corretto?
        //try catch per veder SE si possono inserire mark
        playerBoard1.addMark(ColorPlayer.BLUE,3);
        Assert.assertEquals(3,playerBoard1.numOfMarkOfOneColor(ColorPlayer.BLUE));

    }

    @Test
    public void testFrenzy(){
        assertFalse(playerBoard1.isFrenzy());
        playerBoard1.setFrenzy(true);
        assertTrue(playerBoard1.isFrenzy());
    }



}