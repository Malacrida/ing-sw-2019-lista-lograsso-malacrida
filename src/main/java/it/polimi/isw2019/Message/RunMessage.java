package it.polimi.isw2019.Message;

import it.polimi.isw2019.Message.PlayerMove.*;

public class RunMessage extends Action {
    private int[][] matrix;
    public RunMessage(String idPlayerMove,String idAction, int[][] matrix){
        super(idPlayerMove,idAction);
        this.matrix = matrix;
    }

}
