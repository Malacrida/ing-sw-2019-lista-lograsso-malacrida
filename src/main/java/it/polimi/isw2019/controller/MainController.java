package it.polimi.isw2019.controller;

import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.model.Model;
import it.polimi.isw2019.utilities.Observer;
import it.polimi.isw2019.model.SetUpGame;

public class MainController implements Observer<PlayerMove> {
    MoveControllerSetup moveControllerSetup;
    Model model;



    public MainController() {
        SetUpGame.setPlayerBoard();
        moveControllerSetup = new MoveControllerSetup();

    }

    @Override
    public void update(PlayerMove playerMove) {

        //playerMove.visitController(moveControllerSetup/*,new Run(),new RunGrab(model)*/);
        playerMove.visit(moveControllerSetup);
    }


}