package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.model.exception.EndAction;
import it.polimi.isw2019.model.exception.EndSingleAction;

public class RunMove extends PlayerMove{
    private int[][] movement;

    public RunMove(String nickname,int[][] movement){
        super(nickname);
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
