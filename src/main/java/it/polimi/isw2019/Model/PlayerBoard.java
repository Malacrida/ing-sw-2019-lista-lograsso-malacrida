package it.polimi.isw2019.Model;

public class PlayerBoard extends AbstractPlayerBoard{

    //i giocatori non verrano piu identificati da un id ma da un colore, si potrebbe modificare
    public PlayerBoard(ColorPlayer color, int playerBoardID) {

        super(color, playerBoardID, false);
    }


}
