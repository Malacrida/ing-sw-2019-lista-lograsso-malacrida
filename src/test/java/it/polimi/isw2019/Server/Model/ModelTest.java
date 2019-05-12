package it.polimi.isw2019.Server.Model;

import it.polimi.isw2019.Server.Model.ColorPlayer;
import it.polimi.isw2019.Server.Model.Exception.ColorNotAvailableException;
import it.polimi.isw2019.Server.Model.Exception.KillShotException;
import it.polimi.isw2019.Server.Model.Exception.OverKillException;
import it.polimi.isw2019.Server.Model.Model;
import it.polimi.isw2019.Server.Model.Player;
import it.polimi.isw2019.Server.Model.PlayerBoard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelTest {

    Model model;
    AbstractPlayerBoard playerBoard1;
    AbstractPlayerBoard playerBoard2;
    AbstractPlayerBoard playerBoard3;
    AbstractPlayerBoard playerBoard4;
    AbstractPlayerBoard playerBoard5;
    Player player1, player2, player3, player4, player5;
    KillShotTrack killShotTrack;

    @Before
    public void setUp() throws Exception {
        model = new Model();
        player1 = new Player("Sara", "uau" , 1);
        player2 = new Player("Alba", "uau" , 2);
        player3 = new Player("Davide", "uau" , 3);
        player4 = new Player("Gio", "uau", 4);
        player5 = new Player("Luca", "uau" , 5);
        /*ColorPlayer colorPlayer1 = ColorPlayer.BLUE;
        ColorPlayer colorPlayer2 = ColorPlayer.GREEN;
        ColorPlayer colorPlayer3 = ColorPlayer.YELLOW;
        ColorPlayer colorPlayer4 = ColorPlayer.GREY;
        ColorPlayer colorPlayer5 = ColorPlayer.VIOLET;*/

        killShotTrack=new KillShotTrack(8);

        playerBoard1 = new PlayerBoard(ColorPlayer.BLUE);
        player1.setPlayerBoardAndColor(playerBoard1,ColorPlayer.BLUE);
        playerBoard2 = new PlayerBoard(ColorPlayer.GREEN);
        player2.setPlayerBoardAndColor(playerBoard2,ColorPlayer.GREEN);
        playerBoard3 = new PlayerBoard(ColorPlayer.YELLOW);
        player3.setPlayerBoardAndColor(playerBoard3,ColorPlayer.YELLOW);
        playerBoard4 = new PlayerBoard(ColorPlayer.GREY);
        player4.setPlayerBoardAndColor(playerBoard4,ColorPlayer.GREY);
        playerBoard5 = new PlayerBoard(ColorPlayer.VIOLET);
        player5.setPlayerBoardAndColor(playerBoard5,ColorPlayer.VIOLET);

        model.addPlayer(player1);
        model.addPlayer(player2);
        model.addPlayer(player3);
        model.addPlayer(player4);
        model.addPlayer(player5);




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
        /*try{

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

        }*/

    }

    @Test
    public void tstPositionPlayerBoardAvailable() {
        /*try{
            model.setPlayerWithPlayerBoard(player2, colorPlayer2);
            assertNotEquals(model.positionPlayerBoardAvailable(ColorPlayer.BLUE),2);
        }catch(ColorNotAvailableException e){
            //non dovrebbe fallire!!!
            fail();
        }*/
    }

    @Test
    public void testSetDamageRanking (){
        try {
            player4.sufferDamageOrMark(player2.getColor(),1,2);
            player4.sufferDamageOrMark(player3.getColor(),1,2);
            player4.sufferDamageOrMark(player1.getColor(),2,0);
            player4.sufferDamageOrMark(player5.getColor(),3,1);
            player4.sufferDamageOrMark(player3.getColor(),2,1);
            fail();


        } catch (KillShotException e) {
            assertEquals(11,player4.playerDamage());

            assertEquals(5,player4.damageDoByAnotherPlayer(player3.getColor()));
            assertEquals(3,player4.damageDoByAnotherPlayer(player5.getColor()));
            assertEquals(2,player4.damageDoByAnotherPlayer(player1.getColor()));
            assertEquals(1,player4.damageDoByAnotherPlayer(player2.getColor()));

            model.setDamageRanking(player4);

            int [][] damageRankingPlayer4 = model.damageRanking;

            assertEquals(player1.getPlayerID(), damageRankingPlayer4[2][0]);
            assertEquals(player4.damageDoByAnotherPlayer(player1.getColor()), damageRankingPlayer4[2][1]);

            assertEquals(player2.getPlayerID(), damageRankingPlayer4[3][0]);
            assertEquals(player4.damageDoByAnotherPlayer(player2.getColor()), damageRankingPlayer4[3][1]);

            assertEquals(player3.getPlayerID(), damageRankingPlayer4[0][0]);
            assertEquals(player4.damageDoByAnotherPlayer(player3.getColor()), damageRankingPlayer4[0][1]);

            assertEquals(player5.getPlayerID(), damageRankingPlayer4[1][0]);
            assertEquals(player4.damageDoByAnotherPlayer(player5.getColor()), damageRankingPlayer4[1][1]);

            assertEquals(player2.getColor(),player4.firstPlayerDoDamage());

        } catch (OverKillException e) {
            fail();
        }

    }


    @Test
    public void testAddScoreAfterDeath (){
        try {
            player4.sufferDamageOrMark(player2.getColor(),1,2);
            player4.sufferDamageOrMark(player3.getColor(),1,2);
            player4.sufferDamageOrMark(player1.getColor(),2,0);
            player4.sufferDamageOrMark(player5.getColor(),3,1);
            player4.sufferDamageOrMark(player3.getColor(),2,1);
            fail();


        } catch (KillShotException e) {

            model.addScoreAfterDeath(player4);
            assertEquals(8,player3.getScore());
            assertEquals(6,player5.getScore());
            assertEquals(4,player1.getScore());
            assertEquals(3,player2.getScore());

            assertEquals(11,player4.playerDamage());
            //non funziona bene lastPlayerDamage
            //killShotTrack.killPlayer(player4.lastPlayerDoDamage(), player4.playerDamage());
            killShotTrack.killPlayer(player3.getColor(), player4.playerDamage());
            assertEquals(7, killShotTrack.getNumSkull());
            player4.playerDeath();
            assertEquals(1,playerBoard4.getPlayerSkulls());
            assertEquals(0, playerBoard4.numOfDamages());
            assertEquals(1, playerBoard4.numOfMarkOfOneColor(player3.getColor()));
            assertEquals(2, playerBoard4.numOfMarkOfOneColor(player2.getColor()));
            assertEquals(1, playerBoard4.numOfMarkOfOneColor(player5.getColor()));

        } catch (OverKillException e) {
            fail();
        }


    }


    @Test
    public void testAddScoreAfter3Death (){
        player5.playerDeath();
        player5.playerDeath();
        player5.playerDeath();
        try {
            player5.sufferDamageOrMark(player4.getColor(),3,2);
            player5.sufferDamageOrMark(player1.getColor(),0,2);
            player5.sufferDamageOrMark(player2.getColor(),2,1);
            player5.sufferDamageOrMark(player4.getColor(),2,1);
            player5.sufferDamageOrMark(player3.getColor(),1,0);
            player5.sufferDamageOrMark(player1.getColor(),3,0);
        }
        catch (OverKillException e){
            assertEquals(player4.getColor(), player5.firstPlayerDoDamage());
            model.addScoreAfterDeath(player5);
            assertEquals(3,player4.getScore());
            assertEquals(1,player1.getScore());
            assertEquals(1,player2.getScore());
            assertEquals(0,player3.getScore());

            player5.playerDeath();
            assertEquals(4, playerBoard5.getPlayerSkulls());
            assertEquals(0, playerBoard5.numOfDamages());
            assertEquals(1, playerBoard5.numOfMarkOfOneColor(player4.getColor()));
            assertEquals(1, playerBoard5.numOfMarkOfOneColor(player2.getColor()));
            assertEquals(0, playerBoard5.numOfMarkOfOneColor(player1.getColor()));
        }
        catch (KillShotException e){
            fail();
        }
    }





}
