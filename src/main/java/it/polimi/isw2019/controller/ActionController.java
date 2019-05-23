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

    public void run(int [][] movement, Model model) {
       //check if data are correct
       //if(model.getCurrentPlayer().isAdiacent(movement[0][0], movement[0][1]) then
        for(int i=0;i<movement.length;i++)
                   //!tmpPosition.isAdiacent(movement[i][0], movement[i][1]) then
                            //create error message
        //see if it can ben introduced in the model instead of the controller
        model.getCurrentPlayer().changePosition(movement[movement.length-1][0],movement[movement.length-1][1], ColorRoom.BLUE);
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
