package it.polimi.isw2019.Server.Controller;

import it.polimi.isw2019.Server.Message.PlayerMove.PlayerMove;
import it.polimi.isw2019.Server.Utilities.Observer;

//action controller del programma di sten
public class MainController implements Observer<PlayerMove> {

   // TurnController tc;

    /*public ActionController() {
        tc = new TurnController();
    }*/

    @Override
    public void update(PlayerMove playerMove) {
        playerMove.visitController(new MoveControllerSetup(),new Run(),new RunGrab());
    }



}
