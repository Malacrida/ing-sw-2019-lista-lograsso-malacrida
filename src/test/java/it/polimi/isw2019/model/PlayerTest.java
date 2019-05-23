package it.polimi.isw2019.model;

import it.polimi.isw2019.model.exception.DamageTrackException;
import it.polimi.isw2019.model.powerupcard.PowerUpCard;
import it.polimi.isw2019.model.weaponcard.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.*;

public class PlayerTest {

    private Player player1, player2 , player3, player4;
    private AbstractPlayerBoard playerBoard1, playerBoard2 , playerBoard3, playerBoard4;
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
        weaponCard2 = new Furnace();
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
    public void changePosition() {
    }
}