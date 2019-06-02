package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;

public class RunMove extends PlayerMove{
    private int[][] movement;

    public RunMove(int[][] movement){
        this.movement = movement;
    }

    @Override
    public void accept(VisitorController visitorController) {
            visitorController.visitControllerRun(this);
    }

    public void setMovement(int[][] movement){
        this.movement = movement;
    }

    public int[][] getMovement(){
        return this.movement;
    }


}