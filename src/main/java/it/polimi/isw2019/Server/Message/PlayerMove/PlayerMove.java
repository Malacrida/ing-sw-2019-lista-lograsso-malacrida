package it.polimi.isw2019.Server.Message.PlayerMove;


import it.polimi.isw2019.Server.Controller.MoveController;

public  abstract class PlayerMove {
    public void visitController(MoveController... moveController){
        for(MoveController mc : moveController){
            visit(mc);
        }
    }
    public abstract void visit(MoveController singleMoveController);
}

