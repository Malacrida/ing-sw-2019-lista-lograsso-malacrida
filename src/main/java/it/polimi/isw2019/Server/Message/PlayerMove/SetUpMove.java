package it.polimi.isw2019.Server.Message.PlayerMove;

public class SetUpMove extends PlayerMove{
    private String nickname;
    private String phrase;
    private int idPlayer;
    private String color;
    private char gameMood;

    public SetUpMove(String idPlayerMove,String nickname, String phrase) {
        super(idPlayerMove);
        this.nickname = nickname;
        this.phrase = phrase;
    }

    public SetUpMove(String idPlayerMove,int idPlayer , String color, char gameMood){
        super(idPlayerMove);
        this.idPlayer= idPlayer;
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

    public int getIdPlayer(){
        return this.idPlayer;
    }
}
