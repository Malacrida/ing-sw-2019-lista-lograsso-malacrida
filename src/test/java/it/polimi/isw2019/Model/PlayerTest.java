package it.polimi.isw2019.Model;

import it.polimi.isw2019.Model.PowerUpCard.PowerUpCard;
import it.polimi.isw2019.Model.WeaponCard.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



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
    public void sufferDamage() {

    }

    /*@Test
    public void damageDoByAnotherPlayer() {
        //player1.sufferDamage(player2.getColor(),2,1);
        //player1.sufferDamage(player3.getColor(),2,0);
        //test sulla playerBoard se dal giocatore NON riesco a risalire ai damage

    }*/

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