package it.polimi.isw2019.Controller;

import it.polimi.isw2019.Message.PlayerMove.ActionMove;
import it.polimi.isw2019.Message.PlayerMove.ChooseActionMove;
import it.polimi.isw2019.Message.PlayerMove.RunGrabMove;
import it.polimi.isw2019.Message.PlayerMove.SetUpMove;

public class Run extends ActionController{

    @Override
    public void visitControllerSetUpPlayer(SetUpMove setUpMove) {

    }

    @Override
    public void visitControllerAction(ActionMove actionMove) {

    }

    //change into runMove
    @Override
    public void visitControllerRun(ActionMove actionMove) {

    }

    @Override
    public void visitControllerRunGrab(RunGrabMove runGrabMove) {

    }

    @Override
    public void visitControllerChooseAction(ChooseActionMove chooseActionMove) {

    }
}
