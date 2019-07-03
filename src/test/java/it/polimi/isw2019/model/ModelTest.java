package it.polimi.isw2019.model;

import it.polimi.isw2019.model.exception.*;
import it.polimi.isw2019.model.powerupcard.PowerUpCard;
import it.polimi.isw2019.model.weaponcard.AbstractWeaponCard;
import it.polimi.isw2019.utilities.Database;
import it.polimi.isw2019.view.CLIView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class ModelTest {

    Model model;
    PlayerBoard playerBoard1;
    PlayerBoard playerBoard2;
    PlayerBoard playerBoard3;
    PlayerBoard playerBoard4;
    PlayerBoard playerBoard5;
    Player player1, player2, player3, player4, player5;
    KillShotTrack killShotTrack;
    PowerUpCard powerUpCard;
    ArrayList<PowerUpCard> powerUpCards = new ArrayList<>();
    GameBoard gameBoard;

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

       // gameBoard = new GameBoard();

       // gameBoard.chooseArena(1);

        /*for (int i=0; i <26; i++){
            powerUpCards.add(powerUpCard);
        }*/


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addPlayer() {

    }

    @Test
    public void testChooseFirstPlayer(){
        model.chooseFirstPlayer(3);
        for (Player player : model.getPlayers()) {
            System.out.println(player.getName());
            System.out.println(player.getIndexPlayer());
        }
        assertEquals(3, model.getPlayers().indexOf(model.getCurrentPlayer()));
    }

    @Test
    public void testFirstMessage(){

        model.chooseFirstPlayer(3);
        model.firstMessage();

    }

    @Test
    public void testChangePlayer(){


        model.chooseFirstPlayer(3);

        int oldCurrentPlayer= model.getPlayers().indexOf(model.getCurrentPlayer());

        model.changePlayer();

        if(oldCurrentPlayer == (model.getPlayers().size()-1))
            assertEquals(0,model.getPlayers().indexOf(model.getCurrentPlayer()));
        else
              assertEquals(oldCurrentPlayer + 1,model.getPlayers().indexOf(model.getCurrentPlayer()));

        model.changePlayer();
        model.changePlayer();

        oldCurrentPlayer= model.getPlayers().indexOf(model.getCurrentPlayer());

        model.changePlayer();


        if(oldCurrentPlayer == (model.getPlayers().size()-1))
            assertEquals(0,model.getPlayers().indexOf(model.getCurrentPlayer()));
        else
            assertEquals(oldCurrentPlayer + 1,model.getPlayers().indexOf(model.getCurrentPlayer()));


        oldCurrentPlayer= model.getPlayers().indexOf(model.getCurrentPlayer());
        model.changePlayer();
        if(oldCurrentPlayer == (model.getPlayers().size()-1))
            assertEquals(0,model.getPlayers().indexOf(model.getCurrentPlayer()));
        else
            assertEquals(oldCurrentPlayer + 1,model.getPlayers().indexOf(model.getCurrentPlayer()));

    }

    @Test
    public void testSetFrenzyMood(){

        /*
        model.chooseFirstPlayer(3);

        model.changePlayer();
        model.changePlayer();

        System.out.println(model.getPlayers().indexOf(model.getCurrentPlayer()));

        model.setFrenzyMood();

        assertEquals(2 ,model.getCurrentPlayer().getNumActionToBePerformed());
        assertEquals(1, model.getPlayers().get(3).getNumActionToBePerformed());

        model.chooseFirstPlayer(0);
        model.setCurrentPlayer(model.getPlayers().get(0));
        model.setFrenzyMood();

        assertEquals(1 ,model.getCurrentPlayer().getNumActionToBePerformed());
        assertEquals(1, model.getPlayers().get(3).getNumActionToBePerformed());

        model.chooseFirstPlayer(0);
        model.setCurrentPlayer(model.getPlayers().get(0));
        model.changePlayer();

        model.setFrenzyMood();

        assertEquals(2 ,model.getCurrentPlayer().getNumActionToBePerformed());
        assertEquals(2, model.getPlayers().get(3).getNumActionToBePerformed());
        assertEquals(1, model.getPlayers().get(0).getNumActionToBePerformed());
        */

    }

    //riesco a prendere quella playerboard. NON FUNZIONA
    @Test
    public void testContainsColor() {

        /*
        try{

            assertEquals(false,model.containsColor(ColorPlayer.BLUE));
            assertNotEquals(true,model.containsColor(ColorPlayer.GREEN));
            fail();
        }catch(ColorNotAvailableException e){

        }
*/
    }

    @Test
    public void testPositionPlayerBoardAvailable() {
        /*
        try{
            assertNotEquals(model.positionPlayerBoardAvailable(ColorPlayer.BLUE),2);
            fail();
        }catch(ColorNotAvailableException e){

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


        } catch (DamageTrackException e) {
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

        }

    }


    @Test
    public void testAddScoreAfterDeath (){
        try {
            model.setKillShotTrack(2);
            player4.sufferDamageOrMark(player2.getColor(),1,2);
            player4.sufferDamageOrMark(player3.getColor(),1,2);
            player4.sufferDamageOrMark(player1.getColor(),2,0);
            player4.sufferDamageOrMark(player5.getColor(),3,1);
            player4.sufferDamageOrMark(player3.getColor(),2,1);
            fail();


        } catch (DamageTrackException e) {


            model.addScoreAfterDeath(player4);
            assertEquals(8,player3.getScore());
            assertEquals(6,player5.getScore());
            assertEquals(4,player1.getScore());
            assertEquals(3,player2.getScore());

            assertEquals(11,player4.playerDamage());


            assertEquals(player3.getColor(), player4.lastPlayerDoDamage());
            model.addDamageOnKillShotTrack( player4.lastPlayerDoDamage(), player4.playerDamage());

            player4.playerDeath();
            assertEquals(1,playerBoard4.getPlayerSkulls());
            assertEquals(0, playerBoard4.numOfDamages());
            assertEquals(1, playerBoard4.numOfMarkOfOneColor(player3.getColor()));
            assertEquals(2, playerBoard4.numOfMarkOfOneColor(player2.getColor()));
            assertEquals(1, playerBoard4.numOfMarkOfOneColor(player5.getColor()));

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
        catch (DamageTrackException e){
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

    }


    @Test
    public void killShotRanking (){

        model.setKillShotTrack(2);
        model.addDamageOnKillShotTrack(ColorPlayer.YELLOW,12);
        model.addDamageOnKillShotTrack(ColorPlayer.GREEN,11);
        model.addDamageOnKillShotTrack(ColorPlayer.BLUE,12);
        model.addDamageOnKillShotTrack(ColorPlayer.YELLOW,11);
        model.addDamageOnKillShotTrack(ColorPlayer.GREY,11);
        model.addDamageOnKillShotTrack(ColorPlayer.GREY,11);
        model.addDamageOnKillShotTrack(ColorPlayer.GREEN,11);
        model.addDamageOnKillShotTrack(ColorPlayer.BLUE,12);


        int [][] killShotTable = model.killShotRanking();

        assertEquals(1, killShotTable[0][0]);
        assertEquals(3, killShotTable[1][0]);
        assertEquals(2, killShotTable[2][0]);
        assertEquals(4, killShotTable[3][0]);
        assertEquals(5, killShotTable[4][0]);

        assertEquals(4, killShotTable[0][1]);
        assertEquals(3, killShotTable[1][1]);
        assertEquals(2, killShotTable[2][1]);
        assertEquals(2, killShotTable[3][1]);
        assertEquals(0, killShotTable[4][1]);


        model.addScoreToKillShotTrack();

        assertEquals(8,player1.getScore());
        assertEquals(6,player3.getScore());
        assertEquals(4,player2.getScore());
        assertEquals(2,player4.getScore());
        assertEquals(0,player5.getScore());

    }


    @Test
    public void testAddScoreToKillShotTrack (){


    }

    //public void useWeaponCard( int indexCard,int[] orderEffect,int[][] defenders, int[][] coordinates, int[][] payment){
    @Test
    public void testSetGame(){
        model.setGameBoard(new GameBoard());
        model.setGame(1,5);
        System.out.println(model.getGameBoard().getGameArena().getArenaRepresentation());
        System.out.println(model.getGameBoard().getGameArena().getAmmoTileOnSquare(0,1).toString());


    }
    @Test
    public void testUseWeaponCard(){
        Database db = new Database();
        gameBoard = new GameBoard();
        try {
            gameBoard.chooseArena(1);
        } catch (InstanceArenaException e) {
            e.printStackTrace();
        } catch (OutOfBoundsException e) {
            e.printStackTrace();
        }
        gameBoard.setPowerUpCards(db.loadPowerUpCards());
        gameBoard.setAmmoTiles(db.loadAmmoTiles());
        gameBoard.setWeaponCardsOnBoard();
        try {
            gameBoard.setUpAmmoTileOnArena(1);
        } catch (OutOfBoundsException e) {
            e.printStackTrace();
        }
        model.setGame(1,5);
        System.out.println(model.getGameBoard().getGameArena().getSquare(1,1).getSquareRepresentation());
        model.getGameBoard().insertPlayer(player2,ColorRoom.RED);
        AbstractWeaponCard weaponCard = model.getGameBoard().getGameArena().getWeaponCardsOnSquares(1,0)[1];
        try {
            player2.takeWeaponCards(weaponCard,null);
        } catch (TooManyWeaponCard tooManyWeaponCard) {
            tooManyWeaponCard.printStackTrace();
        }
        assertEquals(6,weaponCard.getID());
        System.out.println(model.getPlayers().size());

        model.setCurrentPlayer(player2);

        System.out.println(model.getCurrentPlayer().getY());
        gameBoard.getGameArena().getAmmoTileOnSquare(1,2).setAmmoCardDescription();
        System.out.println(gameBoard.getGameArena().getAmmoTileOnSquare(1,2).toString());
        System.out.println(gameBoard.getGameArena().getSquare(2,2).toString());
        gameBoard.getGameArena().movePlayer(player1,2,2);
        gameBoard.getGameArena().movePlayer(player2,2,2);

        int[] effect = {1,0,0};
        int[][] coo = new int[2][2];
        int[][] people = new int[2][2];
        int[][] pay = new int[2][2];

        System.out.println(player2.getWeaponCards().get(0).getID() +  " " + model.getCurrentPlayer().getName() + " effect " + effect[0]);
        assertEquals(false,model.getDeadPlayer().contains(player1));
       // model.useWeaponCard(0,effect,coo,people,pay);

        System.out.println(model.getDeadPlayer().size());

        assertEquals(false,model.getDeadPlayer().contains(player1));


    }

    @Test
    public void testCheckValidityPayment(){
       Database db = new Database();

       ArrayList<PowerUpCard> powerUp = db.loadPowerUpCards();
        try {
            player1.takePowerUpCard(powerUp.get(0),null);
        } catch (TooManyPowerUpCard tooManyPowerUpCard) {
            fail();
        }
        try {
            player1.takePowerUpCard(powerUp.get(1),null);
        } catch (TooManyPowerUpCard tooManyPowerUpCard) {
            fail();
        }

        model.setCurrentPlayer(player1);

        int[] payment = {1,0,1,1,-1,-1};
        System.out.println(player1.getPowerUpCards().get(0).getColor());
        ColorCube[] paymentCube ={ColorCube.BLUE,ColorCube.YELLOW, ColorCube.RED};
        assertEquals(true,model.checkValidityPayment(payment,paymentCube));

    }

    @Test
    public void testHandlePayment(){
      /*  Database db = new Database();

        ArrayList<PowerUpCard> powerUp = db.loadPowerUpCards();
        PowerUpCard tmpPowerUpCard = powerUp.get(0);
        PowerUpCard tmpPowerUpCard1 = powerUp.get(1);
        try {

            player1.takePowerUpCard(powerUp.get(0),null);
        } catch (TooManyPowerUpCard tooManyPowerUpCard) {
            fail();
        }
        try {
            player1.takePowerUpCard(powerUp.get(1),null);
        } catch (TooManyPowerUpCard tooManyPowerUpCard) {
            fail();
        }

        model.setCurrentPlayer(player1);
        assertEquals(2,model.getCurrentPlayer().getPowerUpCards().size());

        int[] payment = {1,0,1,0,-1,-1};
        System.out.println(player1.getPowerUpCards().get(0).getColor());
        ColorCube[] paymentCube ={ColorCube.BLUE,ColorCube.YELLOW, ColorCube.RED};
        assertEquals(2,model.getCurrentPlayer().getPowerUpCards().size());
        model.handlePayment(payment);
       //essert commentato perche mi restituisce un null pointer exception -> manca qualcosa da istanziare!(legato alla addPowerUpDiscarded)
        assertEquals(false,model.getCurrentPlayer().getPowerUpCards().contains(tmpPowerUpCard));
        try {
            player1.takePowerUpCard(powerUp.get(0),null);
        } catch (TooManyPowerUpCard tooManyPowerUpCard) {
            tooManyPowerUpCard.printStackTrace();
        }
        int[] payment1 = {0,0,0,0,1,-1};
        model.handlePayment(payment1);
        assertEquals(false,model.getCurrentPlayer().getPowerUpCards().contains(tmpPowerUpCard1));
        assertEquals(0,model.getCurrentPlayer().getPowerUpCards().size());*/
    }




}
