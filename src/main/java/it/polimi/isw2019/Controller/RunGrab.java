package it.polimi.isw2019.Controller;

import it.polimi.isw2019.Message.PlayerMove.*;
import it.polimi.isw2019.Model.Model;
import it.polimi.isw2019.Model.WeaponCard.AbstractWeaponCard;

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
            //create error message
            return;
        }
        else{
            int movement[][] =new int[1][1];
            if(runGrabMove.getMovement().length == 0){
                movement[0][0] = model.getCurrentPlayer().getX();
                movement[0][1] = model.getCurrentPlayer().getY();
            }
            else{
                movement = runGrabMove.getMovement();
            }
            if(runGrabMove.getCardSelection()== 'W'){
                //check if is spawnPoint
                if(model.isSpawnPoint(movement[0][0], movement[0][1])) {
                    //controll that there is the card at that position
                    //assume that the index is OK
                    AbstractWeaponCard tmpWeaponCard= model.getGameBoard().getGameArena().getWeaponCardsOnSquares(movement[0][0], movement[0][1]).get(runGrabMove.getPositionWeaponCard());
                   if(model.getCurrentPlayer().getWeaponCards().size() == 3){
                       String error = "Cannot GRAB -> to many weapon cards";
                   } else if (model.getCurrentPlayer().getWeaponCards().size() <3){
                       // if(tmpWeaponCard.getRechargecube())
                       //assume payment correct
                       //model.getCurrentPlayer().
                       //else
                       //String error ="Payment invalid";
                       //fare tante stringhe quanti sono i possibili errori
                   }
                }
            }
            else if(runGrabMove.getCardSelection() == 'A'){
                //check che in quella posizione la ammocard NON sia stata usata
                if(!model.isSpawnPoint(movement[0][0], movement[0][1])){
                    //model.getGameBoard().getAmmoTileOnSquare(movement[0][0], movement[0][1]){


                }
                    //aggiornarli le munizioni in base alla ammo
                    //mettere la ammo nel deck
            }
        }
    }

    @Override
    public void visitControllerChooseAction(ChooseActionMove chooseActionMove) {

    }
}
