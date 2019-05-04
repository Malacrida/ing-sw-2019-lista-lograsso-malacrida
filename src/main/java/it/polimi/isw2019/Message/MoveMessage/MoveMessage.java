package it.polimi.isw2019.Message.MoveMessage;

import java.util.ArrayList;

public abstract class MoveMessage {

    private String idMoveMessage;
    private int idPlayer;


    public MoveMessage(String idMoveMessage){
        this.idMoveMessage = idMoveMessage;
    }

    public MoveMessage(String idMoveMessage, int idPlayer){
        this.idMoveMessage = idMoveMessage;
        this.idPlayer = idPlayer;
    }


    public String getIdMoveMessage(){
        return idMoveMessage;
    }


    public int getIdPlayer(){
        return idPlayer;
    }


}
