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


    private Model model;
    private ArrayList<String> colorAvailable;

    private Map<Integer, MainView> views;

    private int numAction;


    private int numIdPlayer;

    public MainController() {
        views = new HashMap<>();
        SetUpGame.setPlayerBoard();

    }

    @Override
    public void update(PlayerMove playerMove){
        playerMove.accept(this);
    }


    @Override
    public void visitControllerSetUpPlayer(SetUpMove setUpMove) {
           // model.checkNickname(setUpMove.getNickname());
    }


    @Override
    public void visitReload(ReloadMove reloadMove) {

    }

    @Override
    public void visitControllerActionChoosen(ChooseActionMove chooseActionMove){
        model.setMoveMessagesToBeSent(chooseActionMove.getNumAction());
    }

    //NUMBER
    public boolean checkInputCorrect(int[][] matrix){

        int numMovement = matrix.length;

        for(int i=0;i<numMovement;i++){
            if(matrix[i][0]<0 || matrix[i][0] >2 || matrix[i][1]<0 || matrix[i][1] >3)
                //model.sendCorrectActionMessage(0, "The index you've inserted are wrong!" + matrix[i][0] + matrix[i][1]);
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
           model.addPlayer(firstMessage.getPlayer(),firstMessage.getActionHero());

    }

    //verifico !!!
    @Override
    public void visitControllerRun(RunMove runMove){

        for(int i=0;i<runMove.getMovement().length;i++) {
            if (runMove.getMovement()[i][0] < 0 || runMove.getMovement()[i][0] > 2 || runMove.getMovement()[i][1] < 0 || runMove.getMovement()[i][1] > 3) {
                //model.getMoveMessagesToBeSent()[model.getMessageToBeSent()].setError("The index you've inserted are wrong!" + runMove.getMovement()[i][0] + runMove.getMovement()[i][1]);
                //model.sendCorrectActionMessage();
            }
        }
    }

    @Override
    public void visitControllerGrab(GrabMove grabMove) {

        if(grabMove.getCardSelection()!= 'A' || grabMove.getCardSelection()!= 'W' || grabMove.getCardSelection() != '0') {
            model.getMoveMessagesToBeSent()[model.getMessageToBeSent()].setError("WRONG INPUT" + grabMove.getCardSelection());
            model.sendCorrectActionMessage();
            return;
        }
        /*if(grabMove.getCardSelection()== 'W'){

                //check if is spawnPoint
                //model.getGameBoard().getGameArena().getSquare()
               // model.grabWeaponCard(model.getGameBoard().getGameArena().getWeaponCardsOnSquares(movement[0][0], movement[0][1]).get(runGrabMove.getPositionWeaponCard()),movement,runGrabMove.getPayment());
         }
         else if(grabMove.getCardSelection() == 'A'){
               // model.grabAmmoCard(grabMove.getCardSelection());
            }*/
        }

    @Override
    public void visitColorChoosen(ColorChoosen colorChoosen){

        for(String color : model.getColorAvailable()){
            if(colorChoosen.getColorChoosen().equals(color))
                    model.assignPlayerBoardToPlayer(model.getCurrentPlayer(), colorChoosen.getColorChoosen());
            return;
            }

        //model.sendErrorMessage(model.getCurrentPlayer(),"the color is not available" + colorChoosen.getColorChoosen());

    }

    @Override
    public void visitControllerChooseAction(ChooseActionMove chooseActionMove) {
        if(chooseActionMove.getNumAction()<0 || chooseActionMove.getNumAction()>model.getMoveMessagesToBeSent().length){
            //send message
        }
        //model.sendMessage(chooseActionMove.getNumAction());
    }

    @Override
    public void powerUpChoice(PowerUpChoice powerUpChoice) {
      /*  if(powerUpChoice.getCardChoosen() > model.getCurrentPlayer().getPowerUpCards().size() || powerUpChoice.getCardChoosen() < 0 ){
            //mex di errore
        }
         if(model.getCurrentPlayer().isRespawn() || model.getCurrentPlayer().isFirstTurn()){
                //fillare con una powerUpCard dal deck (se ce ne sono ancora)
                //aggiungerla al deck
                //handleRespawnPowerUp
               // model.movePlayerToRespawnSquare(model.getCurrentPlayer(),model.);

            }
            //restituita la powerUp con le varie cose da pagare e gli effetti
        else{

        }*/
    }

    @Override
    public void usePowerUpCard(UsePowerUpCard usePowerUpCard) {
        //check input corretto
    }
}