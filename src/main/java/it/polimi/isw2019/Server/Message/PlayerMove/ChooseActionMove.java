package it.polimi.isw2019.Server.Message.PlayerMove;

import it.polimi.isw2019.Server.Controller.VisitorController;

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
