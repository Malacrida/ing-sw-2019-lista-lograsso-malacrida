package it.polimi.isw2019.message.PlayerMove;

import it.polimi.isw2019.controller.VisitorController;

public class RunMove extends PlayerMove{
    private int[][] movement;


    @Override
    public void visit(VisitorController singleMoveController) {

    }

    public void setMovement(int[][] movement){
        this.movement = movement;
    }


}
