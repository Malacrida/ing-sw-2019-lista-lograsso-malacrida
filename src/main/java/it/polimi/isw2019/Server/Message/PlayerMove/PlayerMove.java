package it.polimi.isw2019.Server.Message.PlayerMove;


public  abstract class PlayerMove {

    private String idPlayerMove;


    public PlayerMove(String idPlayerMove){
        this.idPlayerMove = idPlayerMove;
    }

    /*public PlayerMove(String idPlayerMove, int idPlayer,String color){
        this.idPlayerMove = idPlayerMove;
        this.idPlayer = idPlayer;
        this.color = color;
    }
*/
    public String getIdPlayerMove(){
        return idPlayerMove;
    }



   /* public String getColor(){
        return color;
    }

    public int getIdPlayer(){
        return idPlayer;
    }*/
    //first message sent
}

