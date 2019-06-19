package it.polimi.isw2019.controller;

import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.model.ColorCube;
import it.polimi.isw2019.model.Model;
import it.polimi.isw2019.model.StateCard;
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

    public boolean checkPayment(String[] payment){

        for(int i=0; i< payment.length; i++){
            switch (payment[i]){
                case "ammo-blue":

                case "ammo-red":

                case "ammo-yellow":
                    return true;
            }
        }
        return false;
    }

    public ArrayList<ColorCube> translateInputIntoCubes(String[] payment){
        ArrayList<ColorCube> tmpColorCube = new ArrayList<>();
        for(int i=0;i<payment.length;i++){
            switch (payment[i]){
                case "cube-blue" :
                    tmpColorCube.add(ColorCube.BLUE);
                    break;
                case "cube-red" :
                    tmpColorCube.add(ColorCube.RED);
                    break;
                case "cube-yellow":
                    tmpColorCube.add(ColorCube.YELLOW);
                    break;
            }
        }
        return tmpColorCube;
        }

    @Override
    public void update(PlayerMove playerMove){
        playerMove.accept(this);
    }

    @Override
    public void visitControllerSetUpPlayer(SetUpMove setUpMove) {
           // model.checkNickname(setUpMove.getNickname());
    }  // rivedere probabilmente

    @Override
    public void visitControllerRegisterPlayer(FirstMessage firstMessage) {

        views.put(numIdPlayer,firstMessage.getMainView());
        numIdPlayer ++;
        model.registerObserver(firstMessage.getMainView());
        model.addPlayer(firstMessage.getPlayer(),firstMessage.getActionHero());

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
    public void powerUpChoice(PowerUpChoice powerUpChoice) {
        /*
        if((powerUpChoice.getCardChoosen() > model.getCurrentPlayer().getPowerUpCards().size()) || (powerUpChoice.getCardChoosen() < 0 )){
            //mex di errore
        }
        else if(model.getCurrentPlayer().isRespawn() || model.getCurrentPlayer().isFirstTurn()){
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
    public void visitControllerActionChoosen(ChooseActionMove chooseActionMove){
        model.setMoveMessagesToBeSent(chooseActionMove.getNumAction());
    }

    @Override
    public void visitWeaponCardChoice(WeaponCardChoice weaponCardChoice) {
        /*
        if(weaponCardChoice.getIndexWeaponCard() >= 0 ){
            if(weaponCardChoice.isGrab()){
                if(!model.getGameBoard().getGameArena().isRespawnSquare(model.getCurrentPlayer().getX(), model.getCurrentPlayer().getY())){
                    //setUp error message, you're NOT in a respawn square : or try to grab an ammo card or you'action will be ended!
                }
                else{
                   if(weaponCardChoice.getIndexWeaponCard() < model.getGameBoard().getGameArena().getSquare(model.getCurrentPlayer().getX(), model.getCurrentPlayer().getY()).getWeaponCards().length){
                                if(!checkPayment(weaponCardChoice.getPayment())){
                                    model.getMoveMessagesToBeSent()[model.getMessageToBeSent()].setError("the input" + weaponCardChoice.getPayment()+"is wrong!");
                                    }
                        ArrayList<ColorCube> payment = translateInputIntoCubes(weaponCardChoice.getPayment());
                       //grabWeaponCard
                   }
                   else{
                       //setErrorMessage -> index out of bound
                   }
                }
            }
            else{
                if(weaponCardChoice.getIndexWeaponCard() < model.getCurrentPlayer().getWeaponCards().size()) {
                    if(model.getCurrentPlayer().getWeaponCards().get(weaponCardChoice.getIndexWeaponCard()).getStateCard().equals(StateCard.DISCHARGE)){
                        //cannot use this weapon card : stop action or reload
                    }
                    else{
                        //mex -> UseWeaponCard with all the possibility
                    }

                }
                else{
                    //setErrorMessage -> index out of bound
                }
            }
        }*/
    }

    @Override
    public void useWeaponCard(UseWeaponCard useWeaponCard) {
        for(Integer effectUsed: useWeaponCard.getEffectUsed()){
           /* if(effectUsed == 1){

            }
            else if(effectUsed == 2){

            }
            else if(effectUsed == 3){

            }*/
        }
    }

    @Override
    public void visitReload(ReloadMove reloadMove) {
            for(int i =0 ; i<reloadMove.getPayment().length; i++){
                if(reloadMove.getPayment()[i]!= null) {
                    if (!checkPayment(reloadMove.getPayment()[i])) {
                        model.getMoveMessagesToBeSent()[model.getMessageToBeSent()].setError("the input" + reloadMove.getPayment() + "is wrong!");
                    }
                }
            }
            //invocazione alla reload

    }

    //verifico !!!
    @Override
    public void visitControllerRun(RunMove runMove){

        for(int i=0;i<runMove.getMovement().length;i++) {
            if (runMove.getMovement()[i][0] < 0 || runMove.getMovement()[i][0] > 2 || runMove.getMovement()[i][1] < 0 || runMove.getMovement()[i][1] > 3) {
                model.getMoveMessagesToBeSent()[model.getMessageToBeSent()].setError("The index you've inserted are wrong!" + runMove.getMovement()[i][0] + runMove.getMovement()[i][1]);
                model.sendCorrectActionMessage();
                return;
            }
        }
        model.run(runMove.getMovement());
        return;
    }

    @Override
    public void visitControllerGrab(GrabMove grabMove) {

        if(grabMove.getCardSelection()!= 'A' || grabMove.getCardSelection()!= 'W' || grabMove.getCardSelection() != '0') {
            model.getMoveMessagesToBeSent()[model.getMessageToBeSent()].setError("WRONG INPUT" + grabMove.getCardSelection());
            model.sendCorrectActionMessage();
            return;
        }
        else if(grabMove.getCardSelection()== 'W'){
                //check payment
                //check index/player has that weapon card
                //check if is spawnPoint
                //model.getGameBoard().getGameArena().getSquare()
                //model.grabWeaponCard(model.getGameBoard().getGameArena().getWeaponCardsOnSquares(movement[0][0], movement[0][1]).get(runGrabMove.getPositionWeaponCard()),movement,runGrabMove.getPayment());
         }
         else if(grabMove.getCardSelection() == 'A'){
               // model.grabAmmoCard(grabMove.getCardSelection());
            }
        }

    @Override
    public void visitControllerChooseAction(ChooseActionMove chooseActionMove) {
        if(chooseActionMove.getNumAction()<0 || chooseActionMove.getNumAction() > model.getMoveMessagesToBeSent().length){

        }
    }

    @Override
    public void usePowerUpCard(UsePowerUpCard usePowerUpCard) {
        //check input corretto
    }
}