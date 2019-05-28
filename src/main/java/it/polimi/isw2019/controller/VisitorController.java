package it.polimi.isw2019.controller;

import it.polimi.isw2019.message.playermove.*;


public interface VisitorController {

    public abstract void visitControllerSetUpPlayer(SetUpMove setUpMove);

    //cancellare
    public abstract void visitControllerAction(ActionMove actionMove);

    public abstract void visitControllerRun(RunMove runMove);

    public abstract void visitControllerRunGrab(RunGrabMove runGrabMove);

    public abstract void visitControllerChooseAction(ChooseActionMove chooseActionMove);


}
