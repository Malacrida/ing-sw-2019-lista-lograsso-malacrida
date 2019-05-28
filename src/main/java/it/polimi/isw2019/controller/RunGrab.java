package it.polimi.isw2019.controller;

import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.model.Model;

public class RunGrab extends ActionController{

    private Model model;
    public RunGrab(Model model){
        super(model);
    }
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



        if(runGrabMove.getCardSelection()!= 'A' || runGrabMove.getCardSelection()!= 'W' || runGrabMove.getCardSelection() != '0') {
            model.sendErrorMessage(model.getCurrentPlayer(),"Wrong Selection" + runGrabMove.getCardSelection());
            return;
        }

        else{
            int movement[][] = new int[1][1];

            if(runGrabMove.getMovement().length == 0){
                movement[0][0] = model.getCurrentPlayer().getX();
                movement[0][1] = model.getCurrentPlayer().getY();
            }
            else{
                movement = runGrabMove.getMovement();
            }

            if(!checkInputCorrect(runGrabMove.getMovement()))
                return;
            else {
                model.run(movement, false);
            }

            if(runGrabMove.getCardSelection()== 'W'){
                //check if is spawnPoint
                model.grabWeaponCard(model.getGameBoard().getGameArena().getWeaponCardsOnSquares(movement[0][0], movement[0][1]).get(runGrabMove.getPositionWeaponCard()),movement,runGrabMove.getPayment());
            }
            else if(runGrabMove.getCardSelection() == 'A'){
                model.grabAmmoCard(movement);
                //check che in quella posizione la ammocard NON sia stata usata
                //aggiornarli le munizioni in base alla ammo
                //mettere la ammo nel deck
            }
        }
    }

    @Override
    public void visitControllerChooseAction(ChooseActionMove chooseActionMove) {

    }
}
