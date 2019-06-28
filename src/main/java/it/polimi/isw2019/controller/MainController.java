package it.polimi.isw2019.controller;

import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.model.*;
import it.polimi.isw2019.model.exception.ColorNotAvailableException;
import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.weaponcard.AbstractWeaponCard;
import it.polimi.isw2019.utilities.Database;
import it.polimi.isw2019.utilities.Observer;

import java.util.ArrayList;
import java.util.Random;

public class MainController implements Observer<PlayerMove>, VisitorController {


    private Model model;
    private ArrayList<String> colorAvailable;

    private PlayerInterface tmpPlayer;
    private InterfacePowerUpCard tmpPowerUp;
    private String cubeInserted;


    public MainController() {
        model = new Model();
        Database db = new Database();


    }

    @Override
    public void update(PlayerMove playerMove){
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
    public void visitControllerSetUpPlayer(SetUpMove setUpMove) {
           // model.checkNickname(setUpMove.getNickname());
    }  // rivedere probabilmente

    @Override
    public void visitControllerRegisterPlayer(FirstMessage firstMessage) {

        model.registerObserver(firstMessage.getCLIView());

        try{
            model.addPlayer(firstMessage.getPlayer(),firstMessage.getActionHero());
        } catch(IndexOutOfBoundsException e){
            model.unregisterObserver(firstMessage.getCLIView());
        }

    }

    public void startGame(){
        Random rand = new Random();

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
        for(int i = 0; i < useWeaponCard.getHandleEffectPayment().length; i ++){
            //if(!model.checkValidityPayment(useWeaponCard.getHandleEffectPayment()[i], firstEffect))
                //error -> not enough cubes , try again
        }



    }

    @Override
    public void visitReload(ReloadMove reloadMove) {

           /* for(int i =0 ; i<reloadMove.getCubes().length; i++){
                if(reloadMove.getCubes()[i]!= null) {
                    if (!checkPayment(reloadMove.getCubes()[i])) {
                        model.getCurrentPlayer().getMessageToBeSent().get(0).setError("the input" + reloadMove.getCubes() + "is wrong!");
                        return;
                    }
                }
            }

            //check poerupcard
            for(int i=0; i< reloadMove.getPowerUp().length; i++){
                if(reloadMove.getPowerUp()[i]!= null){
                    for(int j=0; j< reloadMove.getPowerUp()[i].length; j++) {
                        if(!checkPowerUpCard(reloadMove.getPowerUp()[i][j])){
                            //errore : carta non esiste
                            model.getCurrentPlayer().getMessageToBeSent().get(0).setError("the input" + reloadMove.getPowerUp()[i][j].getPowerUpCardRepresentation() + "is NOT yours!");
                            return;
                        }
                    }
                }
            }



            ArrayList<ColorCube> reload = new ArrayList<>();

*/


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
                model.getCurrentPlayer().getMessageToBeSent().get(0).setError("The index you've inserted are wrong!" + runMove.getMovement()[i][0] + runMove.getMovement()[i][1]);
                model.sendCorrectActionMessage();
                return;
            }
            if(i == runMove.getMovement().length)
                endCycle = true;

            i++;

        }while(!endCycle);

       int[][] coordinates ;

       if(terminateInput){
           coordinates = new int[i+1][2];

           for(int j = 0; j < i + 1 ; j++){
               coordinates[j]= runMove.getMovement()[j];
           }
       }
       else
           coordinates = runMove.getMovement();
        model.run(coordinates);
    }

    @Override
    public void visitControllerGrab(GrabMove grabMove) {

       if(grabMove.getPositionWeaponCard()!= -1){
           AbstractWeaponCard weaponCard = model.getGameBoard().getGameArena().getWeaponCardsOnSquares(model.getCurrentPlayer().getX(),model.getCurrentPlayer().getY())[grabMove.getPositionWeaponCard()];
           System.out.println(weaponCard.getID());
           if(!model.checkValidityPayment(grabMove.getPayment(),weaponCard.getRechargeCube())){
               //mandare un messaggio di errore
           }
           else if(model.getCurrentPlayer().getWeaponCards().size() == 3){
               //choice weapon card tra quelle disponibile
               model.setTmpWeaponCard(weaponCard);
           }
           else{
               model.grabWeaponCard(weaponCard);
           }
       }
    }


    @Override
    public void usePowerUpCard(UsePowerUpCard usePowerUpCard) {

    }



}