package it.polimi.isw2019.controller;

import it.polimi.isw2019.message.PlayerMove.*;
import it.polimi.isw2019.model.Model;

public class MoveControllerSetup implements VisitorController {

    private Model model;
    @Override
    public void visitControllerSetUpPlayer(SetUpMove setUpMove) {

    }

    @Override
    public void visitControllerAction(ActionMove actionMove) {

    }

    @Override
    public void visitControllerRun(RunMove runMove) {

    }

    @Override
    public void visitControllerRunGrab(RunGrabMove runGrabMove) {

    }

    @Override
    public void visitControllerChooseAction(ChooseActionMove chooseActionMove) {
            model.sendMessage(chooseActionMove.getNumAction());
        }
    }

