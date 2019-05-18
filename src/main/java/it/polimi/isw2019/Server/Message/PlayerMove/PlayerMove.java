package it.polimi.isw2019.Server.Message.PlayerMove;


import it.polimi.isw2019.Server.Controller.VisitorController;

public  abstract class PlayerMove {
    public void visitController(VisitorController... visitorController){
        for(VisitorController vc : visitorController){
            visit(vc);
        }
    }

    public abstract void visit(VisitorController singleMoveController);
}

