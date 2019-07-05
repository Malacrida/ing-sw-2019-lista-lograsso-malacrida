package it.polimi.isw2019.model;

import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.exception.TooManyPowerUpCard;
import it.polimi.isw2019.model.powerupcard.PowerUpCard;
import it.polimi.isw2019.model.weaponcard.*;
import it.polimi.isw2019.utilities.Database;
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
    private ArrayList<PowerUpCard> powerUpCard;

    @Before
    public void setUp() throws Exception {
        player1 = new Player("Alba", "Attenti che ci sara' un secondo Big Bang!");
        player2 = new Player("Sara", "Con la mia potenza vi devastero'!");
        player3 = new Player("Lion", "Con la CIPOLLA vi attacchero'");
        player4 = new Player("Davide", "Vi mettero tutti KO");
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

        Database db = new Database();
        powerUpCard = db.loadPowerUpCards();

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
    public void setActive(){
        player1.setActive(true);
        assertTrue(player1.isActive());
    }

    @Test
    public void setPlayerId(){
        assertEquals(1, player1.getPlayerID());
    }

    @Test
    public void getPlayer(){
        assertEquals(player1, player1.getPlayer());
    }

    @Test
    public void setRespawn(){
        player1.setRespawned(true);
        assertTrue(player1.isRespawn());
    }

    public void getTerminator(){
        player1.setTerminatorPlayer(player2);
        assertEquals(player2, player2.getTerminatorPlayer());
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

        model.addPlayer("Alba", "Attenti che ci sara' un secondo Big Bang!");

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

        model.addPlayer("Alba", "Attenti che ci sara' un secondo Big Bang!");

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

        model.addPlayer("Alba", "Attenti che ci sara' un secondo Big Bang!");

        model.setCurrentPlayer(player1);

        player1.changeRoom(ColorRoom.RED);

        assertEquals(player1.getColorRoom(), model.getCurrentPlayer().getColorRoom());

        assertNotEquals(ColorRoom.BLUE, model.getCurrentPlayer().getColorRoom());
    }

    @Test
    public void testCanAddPowerUp(){
        Model model = new Model();

        model.setGame(1,5,0 );

        model.addPlayer("Alba", "Attenti che ci sara' un secondo Big Bang!");

        model.setCurrentPlayer(player1);

        model.getGameBoard().setPowerUpCards(powerUpCard);

        player1.getPowerUpCards().add(powerUpCard.get(0));
        player1.getPowerUpCards().add(powerUpCard.get(1));

//       assertEquals(true, player1.canAddPowerUp());

        player1.getPowerUpCards().add(powerUpCard.get(2));

//        assertEquals(true, player1.canAddPowerUp());

        ArrayList<PowerUpCard> powerUpCard1 = new ArrayList<>();

        for(int i = 0; i < 26 ; i++)
            powerUpCard1.add(new PowerUpCard(i,"BUH", "RED",null));

        model.getGameBoard().setPowerUpCards(powerUpCard1);

        model.addPlayer("Sara", "Con la mia potenza vi devastero'!");
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

    @Test
    public void testFromPowerUpIntoCubes(){
        Database db = new Database();

        ArrayList<PowerUpCard> powerUpCards = db.loadPowerUpCards();
        player1.setPlayerBoardAndColor(playerBoard1,player1.getColor());
        try {
            assertEquals(0,player1.getPowerUpCards().size());
            player1.takePowerUpCard(powerUpCards.get(0),null);
            powerUpCards.remove(0);
            assertEquals(1,player1.getPowerUpCards().size());
        } catch (TooManyPowerUpCard tooManyPowerUpCard) {
            fail();
        }
            assertEquals(1,player1.getPowerUpCards().size());
            System.out.println(player1.getPowerUpCards().get(0).getColorCard().getColorCubeRepresentation());
            //player1.getRealPlayerBoard().addCube(player1.getPowerUpCards().get(0).getColorCard());
            player1.fromPowerUpCardIntoCubes(0);
           // player1.getRealPlayerBoard().numOfBlueCubes()
            assertEquals(0,player1.getPowerUpCards().size());
            assertEquals(23,powerUpCards.size());
    }
}