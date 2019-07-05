package it.polimi.isw2019.model;

import it.polimi.isw2019.model.weaponcard.AbstractWeaponCard;
import it.polimi.isw2019.model.weaponcard.CyberBlade;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SetUpGameTest {
    SetUpGame setUpGame = new SetUpGame();
    private static ArrayList<PlayerBoard> playerBoards= new ArrayList<>();
    private static ArrayList<AbstractWeaponCard> weaponCards = new ArrayList<>();

    @Before
    public void setUp() throws Exception {


    }

    @Test
    public void setPlayerBoard() {
        playerBoards = setUpGame.setPlayerBoard();
        assertEquals(5,playerBoards.size());
    }

    @Test
    public void setWeaponCards() {
        weaponCards = setUpGame.setWeaponCards();
        assertEquals(21,weaponCards.size());
    }
}