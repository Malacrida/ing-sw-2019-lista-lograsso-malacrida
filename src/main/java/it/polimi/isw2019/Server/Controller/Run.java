package it.polimi.isw2019.Server.Controller;

import it.polimi.isw2019.Server.Message.PlayerMove.ActionMove;
import it.polimi.isw2019.Server.Message.PlayerMove.PlayerMove;
import it.polimi.isw2019.Server.Message.PlayerMove.RunGrabMove;
import it.polimi.isw2019.Server.Message.PlayerMove.SetUpMove;
import it.polimi.isw2019.Server.Model.Model;

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
}
