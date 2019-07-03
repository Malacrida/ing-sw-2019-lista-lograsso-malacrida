package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;

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

    @Override
    public void accept(NetworkHandlerVisitorInterface networkHandler) {
        networkHandler.sendActionChoose(this);
    }

}
