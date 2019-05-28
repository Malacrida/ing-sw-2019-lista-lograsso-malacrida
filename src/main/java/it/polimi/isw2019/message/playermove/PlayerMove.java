package it.polimi.isw2019.message.playermove;


import it.polimi.isw2019.controller.VisitorController;

public  abstract class PlayerMove {

    public void visitController(VisitorController... visitorController){
        for(VisitorController vc : visitorController){
            visit(vc);
        }
    }

    public abstract void visit(VisitorController singleMoveController);
}

