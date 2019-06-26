package it.polimi.isw2019.model;

import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.powerupcard.PowerUpCard;
import it.polimi.isw2019.model.weaponcard.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player1, player2 , player3, player4;
    private PlayerBoard playerBoard1, playerBoard2 , playerBoard3, playerBoard4;
    private ColorPlayer colorPlayer1, colorPlayer2,colorPlayer3, colorPlayer4;
    private AbstractWeaponCard weaponCard1,weaponCard2, weaponCard3, weaponCard4;
    private PowerUpCard powerUpCard1, powerUpCard2;

    @Before
    public void setUp() throws Exception {
        player1 = new Player("Alba", "Attenti che ci sara' un secondo Big Bang!", 1);
        player2 = new Player("Sara", "Con la mia potenza vi devastero'!", 2);
        player3 = new Player("Lion", "Con la CIPOLLA vi attacchero'", 3);
        player4 = new Player("Davide", "Vi mettero tutti KO", 4);
        colorPlayer1 = ColorPlayer.BLUE;
        playerBoard1 = new PlayerBoard(colorPlayer1);
        colorPlayer2 = ColorPlayer.GREEN;
        playerBoard2 = new PlayerBoard(colorPlayer2);
        colorPlayer3 = ColorPlayer.GREY;
        playerBoard3 = new PlayerBoard(colorPlayer3);
        colorPlayer4 = ColorPlayer.VIOLET;
        playerBoard4 = new PlayerBoard(colorPlayer4);
        weaponCard1 = new Electroscythe();
        weaponCard2 = new PlasmaGun();
        weaponCard3 = new GrenadeLauncher();
        weaponCard4 = new HeatSeeker();
    }

    @After
    public void tearDown() throws Exception {
    }

    //riferimento al player
    @Test
    public void setPlayerBoardAndColor() {
        assertEquals(playerBoard1.getColor(), colorPlayer1);
        assertNotEquals(playerBoard1.getColor(), colorPlayer2);
    }

    //verificare che se ci sono tre carte NON se ne puo aggiungere una quarta.
    //verificare che se ci sono zero carte NON si puo prendere una carta.
    @Test
    public void takeWeaponCards() {
    }

    @Test
    public void takePowerUpCard() {

    }

    @Test
    public void usePowerUpCard() {

    }

    @Test
    public void reloadWeaponCard() {

    }

    @Test
    public void addScore() {

    }

    @Test
    public void sufferDamageWithOverKill() {
        player1.setPlayerBoardAndColor(playerBoard1,ColorPlayer.GREY);

        try {
            player1.sufferDamageOrMark(ColorPlayer.GREEN, 3, 2);

            assertEquals(2, player1.markDoByAnotherPlayer(ColorPlayer.GREEN));
            assertEquals(3, player1.damageDoByAnotherPlayer(ColorPlayer.GREEN));

            player1.sufferDamageOrMark(ColorPlayer.YELLOW, 2, 1);
            assertEquals(1, player1.markDoByAnotherPlayer(ColorPlayer.YELLOW));
            assertEquals(2, player1.damageDoByAnotherPlayer(ColorPlayer.YELLOW));
            assertEquals(2, player1.markDoByAnotherPlayer(ColorPlayer.GREEN));
            assertEquals(3, player1.damageDoByAnotherPlayer(ColorPlayer.GREEN));

            player1.sufferDamageOrMark(ColorPlayer.BLUE,0,2);
            assertEquals(0,player1.damageDoByAnotherPlayer(ColorPlayer.BLUE));
            assertEquals(2,player1.markDoByAnotherPlayer(ColorPlayer.BLUE));

            player1.sufferDamageOrMark(ColorPlayer.GREEN, 2, 0);
            assertEquals(1, player1.markDoByAnotherPlayer(ColorPlayer.YELLOW));
            assertEquals(2, player1.damageDoByAnotherPlayer(ColorPlayer.YELLOW));
            assertEquals(0, player1.markDoByAnotherPlayer(ColorPlayer.GREEN));
            assertEquals(7, player1.damageDoByAnotherPlayer(ColorPlayer.GREEN));

            player1.sufferDamageOrMark(ColorPlayer.BLUE,2,3);
            fail();
        }
        catch (DamageTrackException e){
            assertEquals(1, player1.markDoByAnotherPlayer(ColorPlayer.YELLOW));
            assertEquals(2, player1.damageDoByAnotherPlayer(ColorPlayer.YELLOW));
            assertEquals(0, player1.markDoByAnotherPlayer(ColorPlayer.GREEN));
            assertEquals(7, player1.damageDoByAnotherPlayer(ColorPlayer.GREEN));
            assertEquals(3, player1.markDoByAnotherPlayer(ColorPlayer.BLUE));
            assertEquals(3,player1.markDoByAnotherPlayer(ColorPlayer.BLUE));
            assertEquals(12, player1.playerDamage());
            assertEquals(ColorPlayer.BLUE, player1.lastPlayerDoDamage());
        }

    }

    @Test
    public void sufferDamageWithKillShot() {
        player1.setPlayerBoardAndColor(playerBoard1,ColorPlayer.BLUE);

        try {
            player1.sufferDamageOrMark(ColorPlayer.GREEN, 3, 2);

            assertEquals(2, player1.markDoByAnotherPlayer(ColorPlayer.GREEN));
            assertEquals(3, player1.damageDoByAnotherPlayer(ColorPlayer.GREEN));

            player1.sufferDamageOrMark(ColorPlayer.YELLOW, 2, 1);
            assertEquals(1, player1.markDoByAnotherPlayer(ColorPlayer.YELLOW));
            assertEquals(2, player1.damageDoByAnotherPlayer(ColorPlayer.YELLOW));
            assertEquals(2, player1.markDoByAnotherPlayer(ColorPlayer.GREEN));
            assertEquals(3, player1.damageDoByAnotherPlayer(ColorPlayer.GREEN));


            player1.sufferDamageOrMark(ColorPlayer.GREEN, 2, 1);
            assertEquals(1, player1.markDoByAnotherPlayer(ColorPlayer.YELLOW));
            assertEquals(2, player1.damageDoByAnotherPlayer(ColorPlayer.YELLOW));
            assertEquals(1, player1.markDoByAnotherPlayer(ColorPlayer.GREEN));
            assertEquals(7, player1.damageDoByAnotherPlayer(ColorPlayer.GREEN));

            player1.sufferDamageOrMark(ColorPlayer.BLUE,2,2);
            fail();
        }

        catch (DamageTrackException e){
            assertEquals(1, player1.markDoByAnotherPlayer(ColorPlayer.YELLOW));
            assertEquals(2, player1.damageDoByAnotherPlayer(ColorPlayer.YELLOW));
            assertEquals(1, player1.markDoByAnotherPlayer(ColorPlayer.GREEN));
            assertEquals(7, player1.damageDoByAnotherPlayer(ColorPlayer.GREEN));
            assertEquals(2, player1.damageDoByAnotherPlayer(ColorPlayer.BLUE));
            assertEquals(2, player1.markDoByAnotherPlayer(ColorPlayer.BLUE));
            assertEquals(11, player1.playerDamage());
        }
    }



    @Test
    public void firstPlayerDoDamage() {

    }

    @Test
    public void getNumberOfSkulls() {
                
    }

    @Test
    public void testChangePosition (){
        Model model = new Model();

        // relationship between  color player and color of arena

        model.addPlayer(player1);

        model.setCurrentPlayer(player1);

        player1.changePosition(1,1,ColorRoom.RED);

        assertEquals(player1.getX(), model.getCurrentPlayer().getX());

        assertNotEquals(2,model.getCurrentPlayer().getX());

        player1.changePosition(2,2, ColorRoom.YELLOW);

        assertNotEquals(1,model.getCurrentPlayer().getX());

    }

    @Test
    public void testChangeSquare(){
        Model model = new Model();

        // relationship between  color player and color of arena

        model.addPlayer(player1);

        model.setCurrentPlayer(player1);

        player1.changeSquare(1,1);

        assertEquals(player1.getX(), model.getCurrentPlayer().getX());

        player1.changeSquare(2,2);

        assertNotEquals(1,model.getCurrentPlayer().getX());


    }

    @Test
    public void testChangeRoom (){
        Model model = new Model();
        // relationship between  color player and color of arena

        model.addPlayer(player1);

        model.setCurrentPlayer(player1);

        player1.changeRoom(ColorRoom.RED);

        assertEquals(player1.getColorRoom(), model.getCurrentPlayer().getColorRoom());

        assertNotEquals(ColorRoom.BLUE, model.getCurrentPlayer().getColorRoom());
    }

    @Test
    public void testCanAddPowerUp(){
        Model model = new Model();


        model.addPlayer(player1);

        model.setCurrentPlayer(player1);

        ArrayList<PowerUpCard> powerUpCard = new ArrayList<>();
        //int id, String name, String color, String infoEffect
        for(int i = 0; i < 26 ; i++)
            powerUpCard.add(new PowerUpCard(i,"Newton", "RED",null));

        model.getGameBoard().setPowerUpCards(powerUpCard);

        player1.getPowerUpCards().add(powerUpCard.get(0));
        player1.getPowerUpCards().add(powerUpCard.get(1));

        assertEquals(true, player1.canAddPowerUp());

        player1.getPowerUpCards().add(powerUpCard.get(2));

        assertEquals(true, player1.canAddPowerUp());

        ArrayList<PowerUpCard> powerUpCard1 = new ArrayList<>();

        for(int i = 0; i < 26 ; i++)
            powerUpCard1.add(new PowerUpCard(i,"BUH", "RED",null));

        model.getGameBoard().setPowerUpCards(powerUpCard1);

        model.addPlayer(player2);
        model.setCurrentPlayer(player2);

        player2.getPowerUpCards().add(powerUpCard1.get(0));
        player2.getPowerUpCards().add(powerUpCard1.get(1));

        assertEquals(false, player2.canAddPowerUp());
    }

    @Test
    public void setMessagesToBeSent(){
        player1.setMessagesToBeSent(0);

        assertEquals(1,player1.getMessageToBeSent().size());

        player1.setMessagesToBeSent(1);
        assertEquals(2,player1.getMessageToBeSent().size());

        player1.setMessagesToBeSent(2);
        assertEquals(1,player1.getMessageToBeSent().size());

        player1.setMessagesToBeSent(3);
        assertEquals(2,player1.getMessageToBeSent().size());

        player1.setMessagesToBeSent(4);
        assertEquals(3,player1.getMessageToBeSent().size());

        player1.setMessagesToBeSent(5);
        assertEquals(1,player1.getMessageToBeSent().size());

        player1.setMessagesToBeSent(6);
        assertEquals(1,player1.getMessageToBeSent().size());

    }
}