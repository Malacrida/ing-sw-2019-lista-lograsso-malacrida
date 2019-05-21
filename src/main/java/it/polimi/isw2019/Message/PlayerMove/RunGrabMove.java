package it.polimi.isw2019.Message.PlayerMove;

import it.polimi.isw2019.Controller.VisitorController;

public class RunGrabMove extends PlayerMove {

    private int[][] movement;
    private char[][] cardSelection;

    @Override
    public void visit(VisitorController singleMoveController) {
            singleMoveController.visitControllerRunGrab(this);
    }

    public void setMovement(int[][] movement){
        this.movement = movement;
    }

    public void setCardSelection(char[][] cardSelection){
        this.cardSelection = cardSelection;
    }
}
