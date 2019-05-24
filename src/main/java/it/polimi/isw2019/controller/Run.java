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

            int numMovement = runMove.getMovement().length;
            int[][] matrix = runMove.getMovement();

            for(int i=0;i<numMovement;i++){
                    if(matrix[i][0]<0 && matrix[i][0] >2 && matrix[i][1]<0 && matrix[i][1] >3)
                        model.sendErrorMessage(model.getCurrentPlayer(), "The index you've inserted are wrong!");
            }

            model.run(matrix,true);

    }

    @Override
    public void visitControllerRunGrab(RunGrabMove runGrabMove) {

    }

    @Override
    public void visitControllerChooseAction(ChooseActionMove chooseActionMove) {

    }
}
