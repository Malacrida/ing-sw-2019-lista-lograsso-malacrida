package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.model.exception.EndAction;
import it.polimi.isw2019.model.exception.EndSingleAction;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;

import java.io.Serializable;


/**
 * player move when player decides to do run action
 */

public class RunMove extends PlayerMove implements Serializable {
    private int[][] movement;

    public RunMove(String nickname,int[][] movement){
        super(nickname);
        this.movement = movement;
    }

    @Override
    public void accept(VisitorController visitorController) {
            visitorController.visitControllerRun(this);

    }

    @Override
    public void accept(NetworkHandlerVisitorInterface networkHandler) {
        networkHandler.sendRun(this);
    }

    /**
     * setter of movement that the player wants to do
     * @param movement coodinates
     */

    public void setMovement(int[][] movement){
        this.movement = movement;
    }

    /**
     * getter of movement
     * @return coordinates
     */

    public int[][] getMovement(){
        return this.movement;
    }


}
