package it.polimi.isw2019.Message.PlayerMove;

import it.polimi.isw2019.Controller.VisitorController;

public class SetUpMove extends PlayerMove{
    private String nickname;
    private String phrase;
    private String color;
    private char gameMood;


    public SetUpMove(String nickname, String phrase, String color, char gameMood){
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
    public void visit(VisitorController singleMoveController) {
        //check if is correct
        //singleMoveController.visitControllerSetUpPlayer(this);
    }
}
