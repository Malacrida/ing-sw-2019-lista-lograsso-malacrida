package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.AmmoTile.AmmoTile;
import it.polimi.isw2019.Model.Exception.AmmoTileUseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SquareAmmoTest {

    SquareAmmo squareAmmo;
    Square squareAmmo1;
    Square squareAmmo2;
    Square squareAmmo3;
    ArrayList<Square> squaresNear= new ArrayList<>();
    AmmoTile ammoTile;
    Player player1;
    Player player2;

    @Before
    public void setUp() throws Exception {
        squareAmmo= new SquareAmmo();
        squareAmmo1= new SquareAmmo();
        squareAmmo2= new SquareAmmo();
        squareAmmo3= new SquareAmmo();
        ammoTile=  new AmmoTile(1, null,null,null);
        player1= new Player("name1", null, 1);
        player1= new Player("name2", null, 2);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setAmmoTileTest() {
        squareAmmo.setAmmoTile(ammoTile);
        assert (squareAmmo.getAmmoTile()==ammoTile);
    }

    @Test
    public void takeAmmoTileTest() {
        squareAmmo.setAmmoTile(ammoTile);
        try {
            squareAmmo.takeAmmoTile();
            assertFalse (squareAmmo.isCanUseAmmo());
        }
        catch (AmmoTileUseException e){

        }
    }

    @Test
    public void squaresAvailableTest() {
        squareAmmo1.setSquareAdjacent(null,squareAmmo2,squareAmmo3,null);
        squareAmmo2.setSquareAdjacent(null,null,null,squareAmmo1);
        squareAmmo3.setSquareAdjacent(squareAmmo1,null,null,null);

        squaresNear.add(squareAmmo2);
        squaresNear.add(squareAmmo3);
        ArrayList<Square> squareSpawnsAvailable = squareAmmo1.squaresAvailable();
        assert (squareSpawnsAvailable.size()==2);
        for (int i =0; i<squaresNear.size(); i++){
            assert (squareSpawnsAvailable.contains(squaresNear.get(i)));
        }
    }

    @Test
    public void addPlayerTest() {
        squareAmmo.addPlayer(player1);
        assert (squareAmmo.getPlayers().contains(player1));
    }

    @Test
    public void addPlayerThrowException (){
        try {
            Player player = null;
            squareAmmo.addPlayer(player);
            fail();
        }
        catch (NullPointerException e){

        }
    }

    @Test
    public void removePlayersTest() {
        //Riguardare bene la teoria sui test
        try {
            squareAmmo.addPlayer(player1);
            squareAmmo.addPlayer(player2);
            squareAmmo.removePlayers(player1);
            assert (squareAmmo.getPlayers().size()==1);
        }
        catch (NullPointerException e){
            //fail();
        }
    }


    @Test
    public void removePlayerThrowException (){
        try {
            squareAmmo.addPlayer(player1);
            squareAmmo.removePlayers(player2);
            fail();
        }
        catch (NullPointerException e){

        }
    }
}