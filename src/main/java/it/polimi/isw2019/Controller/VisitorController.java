package it.polimi.isw2019.Controller;

import it.polimi.isw2019.Message.PlayerMove.ActionMove;
import it.polimi.isw2019.Message.PlayerMove.ChooseActionMove;
import it.polimi.isw2019.Message.PlayerMove.RunGrabMove;
import it.polimi.isw2019.Message.PlayerMove.SetUpMove;


public interface VisitorController {

    public abstract void visitControllerSetUpPlayer(SetUpMove setUpMove);

    //cancellare
    public abstract void visitControllerAction(ActionMove actionMove);

    public abstract void visitControllerRun(ActionMove actionMove);

    public abstract void visitControllerRunGrab(RunGrabMove runGrabMove);

    public abstract void visitControllerChooseAction(ChooseActionMove chooseActionMove);



}
