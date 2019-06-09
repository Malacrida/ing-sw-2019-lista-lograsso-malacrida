package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.view.MainView;

public class SetUpMove extends PlayerMove{
    private String phrase;
    private char gameMood;
    private MainView viewPlayer;

    public MainView getViewPlayer() {
        return viewPlayer;
    }

    public SetUpMove(MainView viewPlayer, String nickname, String phrase, char gameMood){
        super(nickname);
        this.viewPlayer= viewPlayer;
        this.phrase = phrase;
        this.gameMood= gameMood;
    }



    public String getPhrase(){
        return phrase;
    }

    public char getGameMood(){
        return this.gameMood;
    }

    @Override
    public void accept(VisitorController visitorController) {
        visitorController.visitControllerSetUpPlayer(this);
    }
}
