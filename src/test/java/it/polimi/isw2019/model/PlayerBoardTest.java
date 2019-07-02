package it.polimi.isw2019.model;

import it.polimi.isw2019.model.exception.DamageTrackException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        model.addPlayer(player1);
        model.addPlayer(player2);
        model.addPlayer(player3);
        playerBoard1 = new PlayerBoard(ColorPlayer.BLUE);
        playerBoard2= new PlayerBoard(ColorPlayer.GREEN);
        playerBoard3 = new PlayerBoard(ColorPlayer.VIOLET);
        model.setPlayerWithPlayerBoard(player1,ColorPlayer.BLUE);
        model.setPlayerWithPlayerBoard(player2,ColorPlayer.VIOLET);
        model.setPlayerWithPlayerBoard(player3,ColorPlayer.GREEN);
        model.setGame(1,5);


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


}