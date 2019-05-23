package it.polimi.isw2019.Controller;

import it.polimi.isw2019.Message.PlayerMove.PlayerMove;
import it.polimi.isw2019.Model.Model;
import it.polimi.isw2019.Utilities.Observer;

//action controller del programma di sten
public class MainController implements Observer<PlayerMove> {

   // TurnController tc;

    /*public ActionController() {
        tc = new TurnController();
    }*/
    Model model;

    public MainController(Model model){
        this.model = model;
    }

    @Override
    public void update(PlayerMove playerMove) {
        playerMove.visitController(new MoveControllerSetup(),new Run(),new RunGrab(model));
    }



}
