package it.polimi.isw2019.controller;

import it.polimi.isw2019.message.PlayerMove.*;

public class MoveControllerSetup implements VisitorController {


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
        switch(chooseActionMove.getNumAction()) {
            case 0:
                //create runMessage
                //check with the status of the player
                break;
            case 1:
                //create runGrabMessage
                //check with the statu of the player
                break;
            case 2:
                //create useWeaponCardMessage
                break;
            case 3:
                //create runUseWeaponCardMessage
                //check with status of player
                break;
            case 4:
                //create runReloadUseWeaponCardMessage
                //check with status of player
                break;
            case 5:
                //create reloadMessage
                break;

            case 6:
                //create useWeaponCardMessage
                break;
            //default -> messaggio errore
            }


        }
    }

