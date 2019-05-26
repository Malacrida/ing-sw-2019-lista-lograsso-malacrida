package it.polimi.isw2019.controller;

import it.polimi.isw2019.model.ColorRoom;
import it.polimi.isw2019.model.Model;

public abstract class ActionController implements VisitorController{

    //is adiacent
    //messe nel model
    //controller solo l'input!!!!
    Model model;

    public ActionController(Model model){
        this.model = model;
    }

    public boolean checkInputCorrect(int[][] matrix){

        int numMovement = matrix.length;

        for(int i=0;i<numMovement;i++){
            if(matrix[i][0]<0 && matrix[i][0] >2 && matrix[i][1]<0 && matrix[i][1] >3)
                model.sendErrorMessage(model.getCurrentPlayer(), "The index you've inserted are wrong!" + matrix[i][0] + matrix[i][1]);
            return false;
        }
        return true;
    }


    public void useWeaponCard(String weaponCard, Model model) {

    }


    public void reload(String weaponCard,Model model) {
        //check if data are correct
        // if(!model.getCurrentPlayer().getWeaponCard()))
        //create an error message
        //else if(model.getCurrentPlayer().getWeaponCard().getNumCubes()
        //check if all cubes that the player have insert match with the cubes needed by the weapon card
        //or check if the player has the powerUpCard inserted
        //if it's so reload
        //otherwise return an error
    }

    //check

}
