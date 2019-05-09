package it.polimi.isw2019.Server.Model.WeaponCard;

import it.polimi.isw2019.Server.Model.ColorPlayer;
import it.polimi.isw2019.Server.Model.Player;
import it.polimi.isw2019.Server.Model.PlayerBoard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WhisperTest {

    private Player attacker, firstDefender, secondDefender, thirdDefender;
    private int x1, y1, x2, y2;
    private PlayerBoard pba, pb1, pb2, pb3;


    @Before
    public void setUp() throws Exception {
        attacker = new Player("Davide", "Speriamo che sto test vada", 1);
        firstDefender = new Player("Alba", "Tanto attaccano sempre me", 2);
        secondDefender = new Player("Sra", "Tanto attaccano sempre Alba", 3);
        thirdDefender = new Player("Giampierpaolo", "Ma a cosa sto giocando?", 4);

        //x1 = 1;
        //y1 = 1;
        //x2 = 1;
        //y2 = 2;

        attacker.setPlayerBoardAndColor(pba, ColorPlayer.BLUE);
        firstDefender.setPlayerBoardAndColor(pb1, ColorPlayer.YELLOW);
        secondDefender.setPlayerBoardAndColor(pb2, ColorPlayer.GREEN);
        thirdDefender.setPlayerBoardAndColor(pb3, ColorPlayer.GREY);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void firstEffect() {

        try {
            assertEquals(3, pb1.numOfDamages());
            assertEquals(1, pb1.numOfMarkOfOneColor(attacker.getColor()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void secondEffect() {
    }

    @Test
    public void thirdEffect() {
    }
}