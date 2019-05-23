package it.polimi.isw2019.Controller;

import it.polimi.isw2019.Message.PlayerMove.*;
import it.polimi.isw2019.Model.Model;

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
