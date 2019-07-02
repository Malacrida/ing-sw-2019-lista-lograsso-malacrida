package it.polimi.isw2019.controller;

import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.model.*;
import it.polimi.isw2019.model.exception.ColorNotAvailableException;
import it.polimi.isw2019.model.weaponcard.AbstractWeaponCard;
import it.polimi.isw2019.utilities.Database;
import it.polimi.isw2019.utilities.Observer;

import java.util.ArrayList;
import java.util.Random;

public class MainController implements Observer<PlayerMove>, VisitorController {


    private Model model;
    private ArrayList<String> colorAvailable;
    private String cubeInserted;


    public MainController() {
        model = new Model();
        Database db = new Database();


    }

    @Override
    public void update(PlayerMove playerMove){
            System.out.println("controller : " + playerMove.getPlayer());
            playerMove.accept(this);
    }

    public ColorPlayer returnColorPlayerFromString(String color){

        switch (color){
            case "blue" :
                return ColorPlayer.BLUE;
            case "red" :
                return  ColorPlayer.VIOLET;
            case "violet":
                return ColorPlayer.YELLOW;
            case"grey":
                return ColorPlayer.GREY;
            case"green":
                return ColorPlayer.GREEN;

        }
    return null;
    }
    @Override
    public void visitControllerRegisterPlayer(FirstMessage firstMessage) {

        model.registerObserver(firstMessage.getVirtualView());

        try{
            model.addPlayer(firstMessage.getPlayer(),firstMessage.getActionHero());
        } catch(IndexOutOfBoundsException e){
            model.unregisterObserver(firstMessage.getCLIView());
        }

    }

    public void startGame(){
        Random rand = new Random();
        System.out.println(model.getPlayers().size());
        model.chooseFirstPlayer(rand.nextInt(model.getPlayers().size()));
        model.firstMessage();

    }

    @Override
    public void chooseMap(ChooseMapMove chooseMapMove) {

        if(model.getCurrentPlayer().isFirstPlayer()) {
            model.setGame(chooseMapMove.getIndex());
            }
            try {
                model.setPlayerWithPlayerBoard(model.getCurrentPlayer(),returnColorPlayerFromString(model.getColorAvailable().get(chooseMapMove.getIndexColor())));
                model.changePlayer();
            } catch (ColorNotAvailableException e) {
                //normally impossible!
            }
    }

    @Override
    public void powerUpChoice(PowerUpChoice powerUpChoice) {

        if(powerUpChoice.getIdPowerUpTake() < model.getTmpPowerUpCard().size() && (powerUpChoice.getIdPowerUpTake() >= 0 )){

            model.movePlayerToRespawnSquare(powerUpChoice.getIdPowerUpTake());
        }

        //che se ne ha piu di tre ( da controllare prima) selezionarne una tfra quelle che ha da scartare
    }

    @Override
    public void visitControllerActionChoose(ChooseActionMove chooseActionMove){
        model.getCurrentPlayer().setMessagesToBeSent(chooseActionMove.getNumAction());
        System.out.println("ok action Move");
        model.sendActionMessage();
    }

    @Override
    public void visitWeaponCardChoice(WeaponCardChoice weaponCardChoice) {

        /*if(weaponCardChoice.getIndexWeaponCard() >= 0 ){
            if(weaponCardChoice.isGrabWeapon()){
                if(!model.getGameBoard().getGameArena().isRespawnSquare(model.getCurrentPlayer().getX(), model.getCurrentPlayer().getY())){
                    //setUp error message, you're NOT in a respawn square : or try to grab an ammo card or you'action will be ended!
                }
                else{
                   if(weaponCardChoice.getIndexWeaponCard() < model.getGameBoard().getGameArena().getSquare(model.getCurrentPlayer().getX(), model.getCurrentPlayer().getY()).getWeaponCards().length){
                                if(!checkPayment(weaponCardChoice.getPayment())){
                                    model.getCurrentPlayer().getSingleMessageToBeSent().setError("the input" + weaponCardChoice.getPayment()+"is wrong!");
                                    }
                       //grabWeaponCard
                   }
                   else{
                       //setErrorMessage -> index out of bound
                   }
                }
            }
            else{
                if(weaponCardChoice.getIndexWeaponCard() < model.getCurrentPlayer().getWeaponCards().size()) {
                    if (model.getCurrentPlayer().getWeaponCards().get(weaponCardChoice.getIndexWeaponCard()).getStateCard().equals(StateCard.DISCHARGE)) {
                        //cannot use this weapon card : stop action or reload
                    } else {
    /*
                        try {
                           model.grabWeaponCard(weaponCardChoice.getIndexWeaponCard(), translateInputIntoCubes(weaponCardChoice.getPayment()));
                        } catch (OutOfBoundsException e) {
                            e.printStackTrace();
                        }
                        */
                 /*   }
                }
            }
        }*/
    }
    @Override
    public void useWeaponCard(UseWeaponCard useWeaponCard) {

        int numEffect = model.getCurrentPlayer().getWeaponCards().get(useWeaponCard.getWeaponCard()).getMaxPossibleEffects();

       /* if (numEffect >= 1) {
            if (useWeaponCard.getEffectUsed()[0] == 1) {
                if(!model.checkValidityPayment(useWeaponCard.getHandleEffectPayment()[0], model.getCurrentPlayer().getWeaponCards().get(useWeaponCard.getWeaponCard()).getRechargeCube())){
                    model.updateNotCorrectAction("not valid payment first effect");
                }
            }*/
        // }

        for(int i = 0; i < numEffect; i ++) {
            if (numEffect >= 2 && model.getCurrentPlayer().getWeaponCards().get(useWeaponCard.getWeaponCard()).getPaySecondEffect() != null) {
                if (useWeaponCard.getEffectUsed()[i] == 2) {
                    if (!model.checkValidityPayment(useWeaponCard.getHandleEffectPayment()[1], model.getCurrentPlayer().getWeaponCards().get(useWeaponCard.getWeaponCard()).getPaySecondEffect())) {
                        model.updateNotCorrectAction("not valid payment second effect");
                    }
                }

                if (numEffect >= 3) {
                    if (useWeaponCard.getEffectUsed()[i] == 3) {
                        if (!model.checkValidityPayment(useWeaponCard.getHandleEffectPayment()[2], model.getCurrentPlayer().getWeaponCards().get(useWeaponCard.getWeaponCard()).getPayThirdEffect())) {
                            model.updateNotCorrectAction("not valid payment third effect");
                        }
                    }
                }
            }
        }

        for(int i = 0;i < numEffect-1; i ++){
            for(int j = i+1 ; j < numEffect; j++)
            if(useWeaponCard.getEffectUsed()[i] == useWeaponCard.getEffectUsed()[j] && j != i){
                model.updateNotCorrectAction("cannot insert twice the same effect");
            }
        }

        model.useWeaponCard(useWeaponCard.getWeaponCard(), useWeaponCard.getEffectUsed(),useWeaponCard.getPeopleToBeShoot(), useWeaponCard.getHandleEffectCoordinates(), useWeaponCard.getHandleEffectPayment());

    }

    @Override
    public void disconnectionPlayer(ConnectionMove connectionMove) {
        System.out.println("Disconnessione nel controller");
    }

    @Override
    public void visitReload(ReloadMove reloadMove) {

            for(int i =0 ; i<reloadMove.getPayment().length; i++){
               if(reloadMove.getWeaponCard()[i]== 1){
                   if(!model.checkValidityPayment(reloadMove.getPayment()[i],model.getCurrentPlayer().getWeaponCards().get(i).getRechargeCube())){
                       model.updateNotCorrectAction("not enough cubes");
                   }
               }
            }

            model.reload(reloadMove.getPayment(), reloadMove.getWeaponCard());
    }

    @Override
    public void visitControllerRun(RunMove runMove){
        int i = 0;
        boolean endCycle = false;
        boolean terminateInput = false;
       do {
            if(runMove.getMovement()[i][0] == -1 || runMove.getMovement()[i][1] == -1){
                terminateInput = true;
                endCycle = true;
            }
            else if (runMove.getMovement()[i][0] < 0 || runMove.getMovement()[i][0] > 2 || runMove.getMovement()[i][1] < 0 || runMove.getMovement()[i][1] > 3) {
                model.updateNotCorrectAction("The index you've inserted are wrong!" + runMove.getMovement()[i][0] + runMove.getMovement()[i][1]);
                return;
            }

           i++;

            if(i == runMove.getMovement().length)
                endCycle = true;


        }while(!endCycle);

       int[][] coordinates ;

       if(terminateInput){
           coordinates = new int[3][2];

           for(int j = 0; j < i; j++){
               coordinates[j]= runMove.getMovement()[j];
           }
       }
       else
           coordinates = runMove.getMovement();
        model.run(coordinates);
    }

    @Override
    public void visitControllerGrab(GrabMove grabMove) {
        System.out.println(grabMove.getPositionWeaponCard());
        System.out.println("payment");
        for(int i = 0; i < grabMove.getPayment().length; i++)
            System.out.println(grabMove.getPayment()[i]);
       if(grabMove.getPositionWeaponCard()!= -1){
           AbstractWeaponCard weaponCard = model.getGameBoard().getGameArena().getWeaponCardsOnSquares(model.getCurrentPlayer().getX(),model.getCurrentPlayer().getY())[grabMove.getPositionWeaponCard()];
           System.out.println(weaponCard.getID());
           System.out.println(weaponCard.getRechargeCube().length);
           if(!model.checkValidityPayment(grabMove.getPayment(),weaponCard.getRechargeCube())){
               model.updateNotCorrectAction("payment incorrect");
               return;
           }
           else if(model.getCurrentPlayer().getWeaponCards().size() == 3){
               model.setTmpWeaponCard(weaponCard);
               return;
           }
           else{
               model.grabWeaponCard(weaponCard);
               model.grabWeaponCard(weaponCard);
           }
       }
    }


    @Override
    public void usePowerUpCard(UsePowerUpCard usePowerUpCard) {

    }



}