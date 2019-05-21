package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Model.ColorPlayer;
import it.polimi.isw2019.Model.Player;
import it.polimi.isw2019.Model.PlayerBoard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AbstractWeaponCardTest {

    private Player attacker, firstDefender, secondDefender, thirdDefender;
    private int x1, y1, x2, y2;

    private PlayerBoard pba, pb1, pb2;

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
}