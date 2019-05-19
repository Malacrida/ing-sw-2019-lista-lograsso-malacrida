package it.polimi.isw2019.Server.Message.MoveMessage;

import java.util.ArrayList;

public abstract class MoveMessage {

    //String idMoveMessage is no more used
    private String idMoveMessage;
    //little model that contains all the structure of the model and updates the view.
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
