package it.polimi.isw2019.controller;

import it.polimi.isw2019.message.PlayerMove.*;
import it.polimi.isw2019.model.Model;
import it.polimi.isw2019.Utilities.Observer;
import it.polimi.isw2019.model.SetUpGame;
import it.polimi.isw2019.view.MainView;

import java.util.ArrayList;

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