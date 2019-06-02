package it.polimi.isw2019.controller;

import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.model.Model;
import it.polimi.isw2019.utilities.Observer;
import it.polimi.isw2019.model.SetUpGame;
import it.polimi.isw2019.view.MainView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainController implements Observer<PlayerMove>, VisitorController {

    Model model;
    ArrayList<String> colorAvailable;

    Map<Integer, MainView> views;


    private int numIdPlayer;

    public MainController() {
        views = new HashMap<>();
        SetUpGame.setPlayerBoard();

    }

    @Override
    public void update(PlayerMove playerMove) {
        playerMove.accept(this);
    }


    @Override
    public void visitControllerSetUpPlayer(SetUpMove setUpMove) {
           // model.checkNickname(setUpMove.getNickname());
    }

    @Override
    public void visitControllerAction(ActionMove actionMove) {

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
    // rivedere probabilmente
    @Override
    public void visitControllerRegisterPlayer(FirstMessage firstMessage) {
           views.put(numIdPlayer,firstMessage.getMainView());
           numIdPlayer ++;
           model.registerObserver(firstMessage.getMainView());
           model.checkNickname(firstMessage.getNickname(), firstMessage.getActionHero(),numIdPlayer);

    }

    @Override
    public void visitControllerRun(RunMove runMove) {
        if(!checkInputCorrect(runMove.getMovement()))
            return;
        else
            model.run(runMove.getMovement(),true);
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
               // model.grabWeaponCard(model.getGameBoard().getGameArena().getWeaponCardsOnSquares(movement[0][0], movement[0][1]).get(runGrabMove.getPositionWeaponCard()),movement,runGrabMove.getPayment());
            }
            else if(runGrabMove.getCardSelection() == 'A'){
                model.grabAmmoCard(movement);
                //check che in quella posizione la ammocard NON sia stata usata
                //aggiornarli le munizioni in base alla ammo
                //mettere la ammo nel deck
            }
        }
    }

    public void checkCorrectnessInputColor(String color){

    }

    @Override
    public void visitColorChoosen(ColorChoosen colorChoosen){

        for(String color : model.getColorAvailable()){
            if(colorChoosen.getColorChoosen().equals(color))
                    model.assignPlayerBoardToPlayer(model.getCurrentPlayer(), colorChoosen.getColorChoosen());
            return;
            }

        model.sendErrorMessage(model.getCurrentPlayer(),"the color is not available" + colorChoosen.getColorChoosen());

    }

    @Override
    public void visitControllerChooseAction(ChooseActionMove chooseActionMove) {
        //model.sendMessage(chooseActionMove.getNumAction());
    }
}