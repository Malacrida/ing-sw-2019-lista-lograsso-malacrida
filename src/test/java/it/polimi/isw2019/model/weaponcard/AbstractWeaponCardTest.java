package it.polimi.isw2019.model.weaponcard;

import it.polimi.isw2019.model.ColorPlayer;
import it.polimi.isw2019.model.Player;
import it.polimi.isw2019.model.PlayerBoard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AbstractWeaponCardTest {

    Player attacker, firstDefender, secondDefender, thirdDefender;
    int x1, y1, x2, y2;
    ArrayList<Player> players;

    PlayerBoard pba, pb1, pb2;
    CyberBlade card = new CyberBlade();

    @Before
    public void setUp() throws Exception {

        attacker = new Player("Davide", "Speriamo che sto test vada", 1);
        firstDefender = new Player("Alba", "Tanto attaccano sempre me", 2);
        secondDefender = new Player("Sra", "Tanto attaccano sempre Alba", 3);
        thirdDefender = new Player("Giampierpaolo", "Ma a cosa sto giocando?", 4);
        pba = new PlayerBoard(ColorPlayer.BLUE);
        pb1 = new PlayerBoard(ColorPlayer.YELLOW);
        pb2 = new PlayerBoard(ColorPlayer.GREEN);

        attacker.setPlayerBoardAndColor(pba, ColorPlayer.BLUE);
        firstDefender.setPlayerBoardAndColor(pb1, ColorPlayer.YELLOW);
        secondDefender.setPlayerBoardAndColor(pb2, ColorPlayer.GREEN);

        players = new ArrayList<>();

        players.add(firstDefender);
        players.add(secondDefender);
        players.add(thirdDefender);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getID() {
    }

    @Test
    public void getName() {
    }

    @Test
    public void getRechargecube() {
    }

    @Test
    public void getColor() {
    }

    @Test
    public void getInfoEffect() {
    }

    @Test
    public void checkState() {
    }

    @Test
    public void changeState() {
    }

    @Test
    public void oneDistanceY() {

    }

    @Test
    public void oneDistanceX() {
    }

    @Test
    public void sameSquare() {
    }

    @Test
    public void oneDistance() {
    }

    @Test
    public void sameDirection() {
    }

    @Test
    public void firstEffect() {
    }

    @Test
    public void secondEffect() {
    }

    @Test
    public void thirdEffect() {
    }

    @Test
    public void oneDistanceY1() {
    }

    @Test
    public void oneDistanceX1() {
    }

    @Test
    public void moreThanOneOrTwoDistance() {
    }

    @Test
    public void sameSquare1() {
    }

    @Test
    public void direction() {
    }

    @Test
    public void oneDamageIfFirstIsValid() {
    }

    @Test
    public void oneDamageAllPlayersInOneSquare(Player attacker, ArrayList<Player> players) {
        /*this.oneDamageAllPlayersInOneSquare(this.attacker, this.players);

        assertTrue(this.players.contains(firstDefender));

        try {
            assertEquals(1, pb1.numOfDamages());
        } catch (exception e) {
            e.printStackTrace();
        }*/
    }

    @Test
    public void twoDamageInSameSquare() {
    }

    @Test
    public void threeDamageInSameSquare() {
    }

    @Test
    public void twoDamageAndSetFirstIsValid() {
    }

    @Test
    public void playersAreVisible() {
    }
}