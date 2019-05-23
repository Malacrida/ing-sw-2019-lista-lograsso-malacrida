package it.polimi.isw2019.controller;

import it.polimi.isw2019.message.PlayerMove.*;
import it.polimi.isw2019.model.Model;

public class Run extends ActionController{

    public Run(Model model){
        super(model);
    }
    @Override
    public void visitControllerSetUpPlayer(SetUpMove setUpMove) {

    }

    @Override
    public void visitControllerAction(ActionMove actionMove) {

    }

    //change into runMove
    @Override
    public void visitControllerRun(RunMove runMove) {
        //check adiacenze!
    }

    @Override
    public void visitControllerRunGrab(RunGrabMove runGrabMove) {

    }

    @Override
    public void visitControllerChooseAction(ChooseActionMove chooseActionMove) {

    }
}
