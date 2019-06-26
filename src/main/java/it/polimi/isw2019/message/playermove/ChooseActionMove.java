package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;

public class ChooseActionMove extends PlayerMove{

    private int numAction;

    public ChooseActionMove(String idPlayer, int numAction){
        super(idPlayer);
        this.numAction = numAction;
    }

    public int getNumAction(){
        return numAction;
    }

    @Override
    public void accept(VisitorController visitorController) {
        visitorController.visitControllerActionChoose(this);
    }
}
