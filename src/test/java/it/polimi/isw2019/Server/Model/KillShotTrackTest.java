package it.polimi.isw2019.Server.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KillShotTrackTest {

    KillShotTrack killShotTrack;

    @Before
    public void setUp() throws Exception {
        killShotTrack= new KillShotTrack(8);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void killPlayer() {

        killShotTrack.killPlayer(ColorPlayer.YELLOW,12);
        assertEquals(7,killShotTrack.getNumSkull());
        killShotTrack.killPlayer(ColorPlayer.GREEN,11);
        assertEquals(6,killShotTrack.getNumSkull());
        killShotTrack.killPlayer(ColorPlayer.BLUE,11);
        assertEquals(5,killShotTrack.getNumSkull());
        killShotTrack.killPlayer(ColorPlayer.YELLOW,11);
        assertEquals(4,killShotTrack.getNumSkull());
        killShotTrack.killPlayer(ColorPlayer.VIOLET,11);
        assertEquals(3,killShotTrack.getNumSkull());
        killShotTrack.killPlayer(ColorPlayer.GREY,11);
        assertEquals(2,killShotTrack.getNumSkull());
        killShotTrack.killPlayer(ColorPlayer.GREEN,11);
        assertEquals(1,killShotTrack.getNumSkull());
        assertFalse(killShotTrack.isFinalFrenzy());
        killShotTrack.killPlayer(ColorPlayer.BLUE,12);
        assertEquals(0,killShotTrack.getNumSkull());
        assertTrue(killShotTrack.isFinalFrenzy());


        ColorPlayer [][] killTrack= killShotTrack.getDamageToken();
        assertEquals(ColorPlayer.YELLOW, killTrack[0][0]);
        assertEquals(ColorPlayer.YELLOW, killTrack[0][1]);
        assertEquals(ColorPlayer.BLUE, killTrack[2][0]);
        assertEquals(null, killTrack[2][1]);
    }

    @Test
    public void numOfKillShotByOnePlayer() {

        killShotTrack.killPlayer(ColorPlayer.YELLOW,12);
        killShotTrack.killPlayer(ColorPlayer.GREEN,11);
        killShotTrack.killPlayer(ColorPlayer.BLUE,12);
        killShotTrack.killPlayer(ColorPlayer.YELLOW,11);
        killShotTrack.killPlayer(ColorPlayer.GREY,11);
        killShotTrack.killPlayer(ColorPlayer.GREY,11);
        killShotTrack.killPlayer(ColorPlayer.GREEN,11);
        killShotTrack.killPlayer(ColorPlayer.BLUE,12);

        assertEquals(3, killShotTrack.numOfKillShotByOnePlayer(ColorPlayer.YELLOW));
        assertEquals(2, killShotTrack.numOfKillShotByOnePlayer(ColorPlayer.GREEN));
        assertEquals(2, killShotTrack.numOfKillShotByOnePlayer(ColorPlayer.GREY));
        assertEquals(4, killShotTrack.numOfKillShotByOnePlayer(ColorPlayer.BLUE));
        assertEquals(0, killShotTrack.numOfKillShotByOnePlayer(ColorPlayer.VIOLET));


    }

}