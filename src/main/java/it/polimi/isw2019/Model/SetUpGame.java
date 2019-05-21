package it.polimi.isw2019.Model;

import java.util.ArrayList;

public class SetUpGame {

    private static ArrayList<PlayerBoard> playerBoards= new ArrayList<>();


    public static ArrayList<PlayerBoard> setPlayerBoard (){
        PlayerBoard playerBoardYellow = new PlayerBoard(ColorPlayer.YELLOW);
        PlayerBoard playerBoardGreen = new PlayerBoard(ColorPlayer.GREEN);
        PlayerBoard playerBoardBlue = new  PlayerBoard(ColorPlayer.BLUE);
        PlayerBoard playerBoardViolet = new PlayerBoard(ColorPlayer.VIOLET);
        PlayerBoard playerBoardGrey = new PlayerBoard(ColorPlayer.GREY);

        playerBoards.add(playerBoardYellow);
        playerBoards.add(playerBoardGreen);
        playerBoards.add(playerBoardBlue);
        playerBoards.add(playerBoardViolet);
        playerBoards.add(playerBoardGrey);

        return playerBoards;
    }
}
