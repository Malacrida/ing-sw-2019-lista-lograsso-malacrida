package it.polimi.isw2019.Server.Message;

import it.polimi.isw2019.Server.Message.PlayerMove.*;
import it.polimi.isw2019.Server.Message.PlayerMove.Action;

public class RunMessage extends Action {
    private int[][] matrix;
    public RunMessage(String idPlayerMove,String idAction, int[][] matrix){
        super(idPlayerMove,idAction);
        this.matrix = matrix;
    }

}
