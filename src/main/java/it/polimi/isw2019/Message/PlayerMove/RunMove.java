package it.polimi.isw2019.Message.PlayerMove;

import it.polimi.isw2019.Controller.VisitorController;

public class RunMove extends PlayerMove{
    private int[][] movement;


    @Override
    public void visit(VisitorController singleMoveController) {

    }

    public void setMovement(int[][] movement){
        this.movement = movement;
    }


}
