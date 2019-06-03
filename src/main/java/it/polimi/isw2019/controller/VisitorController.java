package it.polimi.isw2019.controller;

import it.polimi.isw2019.message.playermove.*;


public interface VisitorController {

    public abstract void visitControllerSetUpPlayer(SetUpMove setUpMove);

    public abstract void visitControllerActionChoosen(ChooseActionMove chooseActionMove);

    public abstract void visitControllerRun(RunMove runMove);

    public abstract void visitControllerRunGrab(RunGrabMove runGrabMove);

    public abstract void visitControllerChooseAction(ChooseActionMove chooseActionMove);

    public abstract void visitControllerRegisterPlayer(FirstMessage firstMessage);

    public abstract void visitColorChoosen(ColorChoosen colorChoosen);

    public abstract void visitReload(ReloadMove reloadMove);


}
