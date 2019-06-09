package it.polimi.isw2019.message.playermove;


public  abstract class PlayerMove implements VisitablePlayerMove {
    private String player;


    public PlayerMove(String player){
        this.player = player;
    }


    public String getPlayer() {
        return player;
    }
}

