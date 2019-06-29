package it.polimi.isw2019.message.playermove;


import java.io.Serializable;

public  abstract class PlayerMove implements VisitablePlayerMove, Serializable {
    private String player;


    public PlayerMove(String player){
        this.player = player;
    }


    public String getPlayer() {
        return player;
    }
}

