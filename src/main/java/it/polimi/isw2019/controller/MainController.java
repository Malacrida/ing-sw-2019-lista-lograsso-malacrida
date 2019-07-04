package it.polimi.isw2019.controller;

import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.model.*;
import it.polimi.isw2019.model.exception.ColorNotAvailableException;
import it.polimi.isw2019.model.exception.TooManyWeaponCard;
import it.polimi.isw2019.model.weaponcard.AbstractWeaponCard;
import it.polimi.isw2019.utilities.Database;
import it.polimi.isw2019.utilities.Observer;

import java.util.Random;

public class MainController implements Observer<PlayerMove>, VisitorController {


    private Model model;


    public MainController() {
        model = new Model();
        Database db = new Database();


    }

    @Override
    public void update(PlayerMove playerMove){
           // System.out.println("controller : " + playerMove.getPlayer());
            playerMove.accept(this);
    }

    @Override
    public void visitControllerRegisterPlayer(FirstMessage firstMessage) {

        /*
        if (firstMessage.getVirtualViewSocket()==null){
            model.registerObserver(firstMessage.getVirtualViewRmi());
            try{
                model.addPlayer(firstMessage.getPlayer(),firstMessage.getActionHero());
            } catch(IndexOutOfBoundsException e){
                model.unregisterObserver(firstMessage.getVirtualViewRmi());
            }
        }
        if (firstMessage.getVirtualViewRmi()== null){
            model.registerObserver(firstMessage.getVirtualViewSocket());
            try{
                model.addPlayer(firstMessage.getPlayer(),firstMessage.getActionHero());
            } catch(IndexOutOfBoundsException e){
                model.unregisterObserver(firstMessage.getVirtualViewSocket());
            }
        }*/

        model.registerObserver(firstMessage.getVirtualViewRmi());
        try{
            model.addPlayer(firstMessage.getPlayer(),firstMessage.getActionHero());
        } catch(IndexOutOfBoundsException e){
            model.unregisterObserver(firstMessage.getVirtualViewRmi());
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
            model.setGame(chooseMapMove.getIndex(), chooseMapMove.getMod(),chooseMapMove.getTerminator());
            }
            try {
                System.out.println("Index color available" + chooseMapMove.getIndexColor());
                model.setPlayerWithPlayerBoard(model.getCurrentPlayer(), model.getPlayerBoardsAvailable().get(chooseMapMove.getIndexColor()).getColor());
                model.colorAvailable();
                model.changePlayer();
            } catch (ColorNotAvailableException e) {
                //normally impossible!
            }
    }

    @Override
    public void powerUpChoice(PowerUpChoice powerUpChoice) {

        if(powerUpChoice.getIdPowerUpTake() < model.getTmpPowerUpCard().size() && (powerUpChoice.getIdPowerUpTake() >= 0 )){
            if(!model.getCurrentPlayer().isRespawn()) {
                //gestisce quello di due
                model.movePlayerToRespawnSquare(powerUpChoice.getIdPowerUpTake());
            }
            else{
                //gestisce quello di uno
                model.removePowerUpFromPlayer(powerUpChoice.getIdPowerUpTake());
            }
        }
        else if(powerUpChoice.getIdPowerUpTake() == -1){
            model.getGameBoard().addPowerUpCardDiscarded(model.getTmpPowerUpCard().get(0));
            model.getTmpPowerUpCard().clear();
            model.updateCorrectAction();
        }

    }

    @Override
    public void visitControllerActionChoose(ChooseActionMove chooseActionMove){
        model.getCurrentPlayer().setMessagesToBeSent(chooseActionMove.getNumAction());
        model.sendActionMessage();
    }

    @Override
    public void visitWeaponCardChoice(WeaponCardChoice weaponCardChoice){
        if(weaponCardChoice.getIndexWeaponCard() == -1){
            model.getGameBoard().addWeaponCardToDiscarded(model.getTmpWeaponCard());
            model.setTmpWeaponCard(null);
            model.sendActionUpdateMessage();
        }
        else{
            try {
                model.getCurrentPlayer().takeWeaponCards(model.getTmpWeaponCard(),model.getCurrentPlayer().getWeaponCards().get(weaponCardChoice.getIndexWeaponCard()));
                model.setTmpWeaponCard(null);
            } catch (TooManyWeaponCard tooManyWeaponCard) {
                //tooManyWeaponCard.printStackTrace();
                //non ci dovrebbe mai arrivare
            }
            model.sendActionUpdateMessage();
        }
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
            for(int j = i+1 ; j < numEffect; j++) {
                if (useWeaponCard.getEffectUsed()[i] == useWeaponCard.getEffectUsed()[j] && j != i) {
                    model.updateNotCorrectAction("cannot insert twice the same effect");
                }
            }
        }

        for(int i = 0 ; i < useWeaponCard.getHandleEffectPayment().length; i++)
            model.addCubesFromPowerUp(useWeaponCard.getHandleEffectPayment()[i]);

        model.useWeaponCard(useWeaponCard.getWeaponCard(), useWeaponCard.getEffectUsed(),useWeaponCard.getPeopleToBeShoot(), useWeaponCard.getHandleEffectCoordinates(), useWeaponCard.getHandleEffectPayment());

    }

    @Override
    public void disconnectionPlayer(ConnectionMove connectionMove) {
        if(connectionMove.getConnection() == 0)
            model.getCurrentPlayer().setActive(false);
        else if (connectionMove.getConnection() == 1)
            model.getCurrentPlayer().setActive(true);
    }

    @Override
    public void visitReload(ReloadMove reloadMove) {
        if(reloadMove.getPayment()[0][0] == -1) {
            model.changePlayer();
            return;
        }
        else {
            for (int i = 0; i < reloadMove.getPayment().length; i++) {
                if (reloadMove.getWeaponCard()[i] == 1) {
                    if (!model.checkValidityPayment(reloadMove.getPayment()[i], model.getCurrentPlayer().getWeaponCards().get(i).getRechargeCube())) {
                        model.updateNotCorrectAction("not enough cubes");
                        return;
                    }
                }
            }

            for (int i = 0; i < reloadMove.getPayment().length; i++)
                model.addCubesFromPowerUp(reloadMove.getPayment()[i]);


            model.reload(reloadMove.getPayment(), reloadMove.getWeaponCard());
        }
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
        for(int i = 0; i < grabMove.getPayment().length; i++)
            System.out.println(grabMove.getPayment()[i]);
        if(grabMove.getPositionWeaponCard()!= -1){
           AbstractWeaponCard weaponCard = model.getGameBoard().getGameArena().getWeaponCardsOnSquares(model.getCurrentPlayer().getX(),model.getCurrentPlayer().getY())[grabMove.getPositionWeaponCard()];
           System.out.println(weaponCard.getRechargeCube().length);
           if(!model.checkValidityPayment(grabMove.getPayment(),weaponCard.getRechargeCube())){
               model.updateNotCorrectAction("payment incorrect");
               return;
           }
           else if(model.getCurrentPlayer().getWeaponCards().size() == 3){
               model.setTmpWeaponCard(weaponCard);
               //continuare per la choice
               return;
           }
           else{
               model.addCubesFromPowerUp(grabMove.getPayment());
               model.grabWeaponCard(weaponCard);
           }
       }
    }


    @Override
    public void usePowerUpCard(UsePowerUpCard usePowerUpCard) {

        model.usePowerUpCard(usePowerUpCard.getPositionPowerUp(),usePowerUpCard.getIdPlayer(),usePowerUpCard.getCoordinates());

    }

    @Override
    public void terminatorAction(TerminatorMove terminatorMove) {
       model.terminatorAction(terminatorMove.isShootPeople(), terminatorMove.getCoordinates());
    }
}