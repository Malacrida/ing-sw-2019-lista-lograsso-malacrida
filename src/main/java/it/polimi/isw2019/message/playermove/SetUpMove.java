package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.view.MainView;

public class SetUpMove extends PlayerMove{
    private String nickname;
    private String phrase;
    private String color;
    private char gameMood;
    private MainView player;

    public MainView getPlayer() {
        return player;
    }

    public SetUpMove(MainView player, String nickname, String phrase, String color, char gameMood){
        this.player= player;
        this.nickname = nickname;
        this.phrase = phrase;
        this.color = color;
        this.gameMood= gameMood;
    }


    public String getNickname(){
        return nickname;
    }

    public String getPhrase(){
        return phrase;
    }

    public char getGameMood(){
        return this.gameMood;
    }

    public String getColor(){
        return this.color;
    }

    @Override
    public void accept(VisitorController visitorController) {
        visitorController.visitControllerSetUpPlayer(this);
    }
}
