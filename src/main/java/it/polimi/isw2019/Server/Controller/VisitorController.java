package it.polimi.isw2019.Server.Controller;

import it.polimi.isw2019.Server.Message.PlayerMove.ActionMove;
import it.polimi.isw2019.Server.Message.PlayerMove.ChooseActionMove;
import it.polimi.isw2019.Server.Message.PlayerMove.RunGrabMove;
import it.polimi.isw2019.Server.Message.PlayerMove.SetUpMove;


public interface VisitorController {

    public abstract void visitControllerSetUpPlayer(SetUpMove setUpMove);

    //cancellare
    public abstract void visitControllerAction(ActionMove actionMove);

    public abstract void visitControllerRun(ActionMove actionMove);

    public abstract void visitControllerRunGrab(RunGrabMove runGrabMove);

    public abstract void visitControllerChooseAction(ChooseActionMove chooseActionMove);



}
