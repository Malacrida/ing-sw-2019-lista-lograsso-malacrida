package it.polimi.isw2019.Message.PlayerMove;


public class PlayerMove {

    private String idPlayerMove;
    private int idPlayer;
    private String nickname;
    private String phrase;
    private String color;


    public PlayerMove(String idPlayerMove,String nickname, String phrase){
        this.nickname = nickname;
        this.phrase = phrase;
    }

    public PlayerMove(String idPlayerMove, int idPlayer,String color){
        this.idPlayerMove = idPlayerMove;
        this.idPlayer = idPlayer;
        this.color = color;
    }

    public String getIdPlayerMove(){
        return idPlayerMove;
    }

    public String getNickname(){
        return nickname;
    }

    public String getPhrase(){
        return phrase;
    }

    public String getColor(){
        return color;
    }

    public int getIdPlayer(){
        return idPlayer;
    }
    //first message sent
}

