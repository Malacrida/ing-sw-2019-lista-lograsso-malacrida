package it.polimi.isw2019.model;


import it.polimi.isw2019.model.weaponcard.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

//import CyberBlade;

public class SquareSpawnTest {

    SquareSpawn squareSpawn1;
    Square squareSpawn2;
    Square squareSpawn3;
    Square squareSpawn4;
    Square squareSpawn5;
    AbstractWeaponCard weaponCard1;
    AbstractWeaponCard weaponCard2;
    AbstractWeaponCard weaponCard3;
    AbstractWeaponCard [] weaponCards= new AbstractWeaponCard[3];
    ArrayList<Square> squaresNear= new ArrayList<>();
    Player player1;
    Player player2;

    @Before
    public void setUp() throws Exception {
        squareSpawn1= new SquareSpawn();
        squareSpawn2= new SquareSpawn();
        squareSpawn3= new SquareSpawn();
        //squareSpawn4= new SquareSpawn(squareSpawn3,null,null,squareSpawn5);
        squareSpawn5= new SquareSpawn();
        weaponCard1= new CyberBlade();
        weaponCard2= new Electroscythe();
        weaponCard3= new Flamethrower();
        weaponCards[0]=weaponCard1;
        weaponCards[1]=weaponCard2;
        weaponCards[2]=weaponCard3;
        player1= new Player("name1", null);
        player2= new Player("name2", null);
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void containsWeaponTest() {
        squareSpawn1.setWeaponCards(weaponCards);
        assert (squareSpawn1.containsWeapon(weaponCard2)==true);
    }

    /*@Test
    public void takeWeaponTest() {
        squareSpawn1.setWeaponCards(weaponCards);
        squareSpawn1.takeWeapon(weaponCard1);
        assert ((squareSpawn1.containsWeapon(weaponCard1)));

    }*/

    @Test
    public void setWeaponCardsTest() {
        squareSpawn1.setWeaponCards(weaponCards);
        AbstractWeaponCard[] weaponCardsTest = squareSpawn1.getWeaponCards();
        assert (squareSpawn1.numOfWeaponCards()==3);
        for (int i=0; i<3; i++){
            assert (weaponCardsTest[i].equals(weaponCards[i]));
        }
    }

    @Test
    public void putNewWeaponCardTest() {
        squareSpawn1.putNewWeaponCard(weaponCard1);
        AbstractWeaponCard[] weaponCardsTest= squareSpawn1.getWeaponCards();
        assert (weaponCardsTest[0].equals(weaponCard1));
    }

    @Test
    public void squaresAvailableTest() {
        squareSpawn2.setSquareAdjacent(null,squareSpawn3,squareSpawn5,null);
        squareSpawn3.setSquareAdjacent(null,null,squareSpawn4,squareSpawn2);
        squareSpawn5.setSquareAdjacent(squareSpawn2,squareSpawn4,null,null);

        squaresNear.add(squareSpawn3);
        squaresNear.add(squareSpawn5);
        ArrayList<Square> squareSpawnsAvailable = squareSpawn2.squaresAvailable();
        assert (squareSpawnsAvailable.size()==2);
        for (int i =0; i<squaresNear.size(); i++){
            assert (squareSpawnsAvailable.contains(squaresNear.get(i)));
        }
    }

    @Test
    public void findPlayerTest() {
        squareSpawn1.addPlayer(player1);
        assertTrue(squareSpawn1.findPlayer(player1));
        assertFalse (squareSpawn1.findPlayer(player2));
    }

    @Test
    public void addPlayerTest() {
        try {
            squareSpawn1.addPlayer(player1);
            assert (squareSpawn1.getPlayers().contains(player1));
        }
        catch (NullPointerException e){
            fail();
        }
    }

    @Test
    public void addPlayerThrowException (){
        try {
            Player player = null;
            squareSpawn1.addPlayer(player);
            fail();
        }
        catch (NullPointerException e){

        }
    }

    @Test
    public void removePlayersTest() {

        try {
            squareSpawn1.addPlayer(player1);
            squareSpawn1.addPlayer(player2);
            squareSpawn1.removePlayers(player1);
            assert (squareSpawn1.getPlayers().size()==1);
            assert (squareSpawn1.numPlayers()==1);
        }
        catch (NullPointerException e){
            fail();
        }
    }

    @Test
    public void removePlayerThrowException (){
        try {
            squareSpawn1.addPlayer(player1);
            squareSpawn1.removePlayers(player2);
            fail();
        }
        catch (NullPointerException e){

        }
    }
}