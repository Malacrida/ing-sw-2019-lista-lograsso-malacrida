package it.polimi.isw2019.Message.MoveMessage;

import java.util.ArrayList;

public class MoveMessage {

    private String idMoveMessage;
    private ArrayList<String> colorAvailable;
    private int idPlayer;


    public MoveMessage(String idMoveMessage, int idPlayer ,ArrayList<String> colorAvailable){
        this.idMoveMessage = idMoveMessage;
        this.idPlayer = idPlayer;
        this.colorAvailable = colorAvailable;
    }


    public String getIdMoveMessage(){
        return idMoveMessage;
    }


    public int getIdPlayer(){
        return idPlayer;
    }

    public ArrayList<String > getColor(){
        return colorAvailable;
    }


}
