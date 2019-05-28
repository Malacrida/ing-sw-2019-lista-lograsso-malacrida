package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;

public class ChooseActionMove extends PlayerMove{

    private String idPlayer;
    private int numAction;

    public ChooseActionMove(String idPlayer, int numAction){
        this.idPlayer = idPlayer;
        this.numAction = numAction;
    }

    public String getIdPlayer(){
        return this.idPlayer;
    }

    public int getNumAction(){
        return numAction;
    }

    @Override
    public void visit(VisitorController singleMoveController) {

    }
}
