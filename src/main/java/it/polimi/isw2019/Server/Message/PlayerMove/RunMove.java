package it.polimi.isw2019.Server.Message.PlayerMove;

public class RunMove extends Action{
    private int [][] movement;
    public RunMove(String idPlayerMove,String idAction,int[][] movement){
        super(idPlayerMove,idAction);
        this.movement= movement;
    }

    public int [][] getMovement(){
        return movement;
    }
}
