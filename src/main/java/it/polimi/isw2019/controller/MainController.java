package it.polimi.isw2019.controller;

import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.model.*;
import it.polimi.isw2019.model.exception.ColorNotAvailableException;
import it.polimi.isw2019.model.exception.OutOfBoundsException;
import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.powerupcard.PowerUpCard;
import it.polimi.isw2019.model.weaponcard.AbstractWeaponCard;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.utilities.Observer;

import java.util.ArrayList;

public class MainController implements Observer<PlayerMove>, VisitorController {


    private Model model;
    private ArrayList<String> colorAvailable;

    private PlayerInterface tmpPlayer;
    private InterfacePowerUpCard tmpPowerUp;
    private String cubeInserted;


    private int numAction;

    private int numIdPlayer;

    public MainController() {
        model = new Model();
       // SetUpGame.setPlayerBoard();

    }

    @Override
    public void update(PlayerMove playerMove){
      //  if(playerMove.getPlayer().equals(model.getCurrentPlayer().getName()) ) {
            playerMove.accept(this);
    }

    public boolean checkPayment(String[] payment){

        for(int i=0; i< payment.length; i++){
            switch (payment[i]){
                case "ammo-blue":

                case "ammo-red":

                case "ammo-yellow":
                    return true;
                default:
                    return false;
            }
        }
        return false;
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
    public ArrayList<ColorCube> translateInputIntoCubes(ArrayList<String> payment){
        ArrayList<ColorCube> tmpColorCube = new ArrayList<>();
        for(String singlePayment : payment){
            switch (singlePayment){
                case "blue" :
                    tmpColorCube.add(ColorCube.BLUE);
                    break;
                case "red" :
                    tmpColorCube.add(ColorCube.RED);
                    break;
                case "yellow":
                    tmpColorCube.add(ColorCube.YELLOW);
                    break;
            }
        }
        return tmpColorCube;
        }
    public ColorCube[] translateInputIntoCubes(String[] payment){
        ColorCube[] tmpColorCube = new ColorCube[payment.length];
        for(int i =0 ; i< payment.length; i++){
            switch (payment[i]){
                case "blue" :
                    tmpColorCube[i] = ColorCube.BLUE;
                    break;
                case "red" :
                    tmpColorCube[i] = ColorCube.RED;
                    break;
                case "yellow":
                    tmpColorCube[i] = ColorCube.YELLOW;
                    break;
            }
        }
        return tmpColorCube;
    }
    public boolean checkPlayer(PlayerInterface playerInterface){
        for(PlayerInterface playerInterface1 : model.getPlayersInterface()){
            if(playerInterface1.equals(playerInterface))
                return true;
        }
        return false;
    }

    public boolean checkPlayers(ArrayList<PlayerInterface> playerInterfaces){
        for(PlayerInterface playerInterface: playerInterfaces)
            if(!checkPlayer(playerInterface)) {
                tmpPlayer = playerInterface;
                return false;
            }
        return true;
    }

    public boolean checkPaymentCube(String cube){
        switch (cube){
            case "blue":

            case "red":

            case "yellow":
                return true;
            default:
                cubeInserted = cube;
                return false;
        }
    }

    public boolean checkPaymentCubes(ArrayList<String> cubes){
        for(String cube : cubes){
            if(!checkPaymentCube(cube))
                return false;
        }
        return true;
    }

    public boolean checkPowerUpCard(InterfacePowerUpCard powerUpCard){
        if(model.getCurrentPlayer().getPowerUpCard().contains(powerUpCard)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkPowerUpCards(ArrayList<InterfacePowerUpCard> powerUpCard) {
        for (InterfacePowerUpCard powerUpCard1 : powerUpCard) {
            if(!checkPowerUpCard(powerUpCard1)){
                tmpPowerUp = powerUpCard1;
                return false;
            }
        }
        return true;
    }

    public Player fromPlayerInterfaceToPlayer(PlayerInterface playerInterface){
        for(int i=0; i< model.getPlayers().size(); i++){
            if(model.getPlayers().get(i).getPlayerInterface().equals(playerInterface)){
                return model.getPlayers().get(i);
            }
        }
        return null;
    }

    public AbstractWeaponCard fromInterfaceToAbstractWeaponCard(WeaponCardInterface weaponCardInterface){
        for(AbstractWeaponCard weaponCard: model.getCurrentPlayer().getWeaponCards()){
            if(weaponCard.getWeaponCard().equals(weaponCardInterface)){
                return weaponCard;
            }
        }
        return null;
    }

    public ArrayList<Player> translateInterfacePlayerIntoPlayers(ArrayList<PlayerInterface> playerInterface){
        Player tmp;
        ArrayList<Player> players = new ArrayList<>();
        for(PlayerInterface playerInterface1 : playerInterface){
            tmp = fromPlayerInterfaceToPlayer(playerInterface1);
            if(tmp!= null){
                players.add(tmp);
            }
        }
        return players;
    }

    public PowerUpCard fromSinglePowerUpInterfaceToPowerUp(InterfacePowerUpCard powerUpCard){
        for(PowerUpCard powerUpCard1 : model.getCurrentPlayer().getPowerUpCards()){
            if(powerUpCard1.getPowerUpCard().equals(powerUpCard)){
                return powerUpCard1;
            }
        }
        return null;
    }

    public ArrayList<PowerUpCard> fromPowerUpInterfaceToPowerUp(ArrayList<InterfacePowerUpCard> interfacePowerUpCard){
        ArrayList<PowerUpCard> tmpPowerUpCard = new ArrayList<>();
        PowerUpCard tmpPowerUpCard1;
        for(InterfacePowerUpCard powerUpCard :interfacePowerUpCard){
            tmpPowerUpCard1= fromSinglePowerUpInterfaceToPowerUp(powerUpCard);
            if(tmpPowerUpCard1 != null){
                tmpPowerUpCard.add(tmpPowerUpCard1);
            }
        }
        return tmpPowerUpCard;
    }


    public void insertCoordinate(ArrayList<Integer> coordinates, int[] tmpCoordinates){
        for(int i=0 , j = 0;i<coordinates.size();i++, j++){
            tmpCoordinates[j] = coordinates.get(i);
        }
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

    @Override
    public void chooseMap(ChooseMapMove chooseMapMove) {
        //modify the index of map!

        if(chooseMapMove.getIndex() >= 0 && chooseMapMove.getIndex() <= 4){
            //error
        }

        //colore e' presente!!! fare il check

        else{
            model.associateMapToGameboard(chooseMapMove.getIndex());

            try {
                model.setPlayerWithPlayerBoard(model.getCurrentPlayer(),returnColorPlayerFromString(chooseMapMove.getIndexColor()));
            } catch (ColorNotAvailableException e) {
                //normally impossible!
            }
        }
    }

    @Override
    public void visitColorChoose(ColorChoosen colorChoosen){

        // model.assignPlayerBoardToPlayer(model.getCurrentPlayer(), colorChoosen.getColorChoosen());

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
    public void visitControllerActionChoose(ChooseActionMove chooseActionMove){
        model.getCurrentPlayer().setMessagesToBeSent(chooseActionMove.getNumAction());
    }

    @Override
    public void visitWeaponCardChoice(WeaponCardChoice weaponCardChoice) {

        if(weaponCardChoice.getIndexWeaponCard() >= 0 ){
            if(weaponCardChoice.isGrab()){
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
                    }
                }
            }
        }
    }
    @Override
    public void useWeaponCard(UseWeaponCard useWeaponCard) {

        int[][] coordinates = new int[3][];

        if(useWeaponCard.getPlayerAttackedFirstEffect()!= null) {
            if (!checkPlayers(useWeaponCard.getPlayerAttackedFirstEffect())) {
                model.getCurrentPlayer().getMessageToBeSent().get(0).setError("WRONG INPUT" + tmpPlayer);
                return;
            }
        }
        else if(useWeaponCard.getPlayerAttackedSecondEffect()!= null) {
            if (!checkPlayers(useWeaponCard.getPlayerAttackedSecondEffect())) {
                model.getCurrentPlayer().getMessageToBeSent().get(0).setError("WRONG INPUT" + tmpPlayer);
                return;
            }
        }
        else if(useWeaponCard.getPlayerAttackedThirdEffect()!= null) {
            if ((!checkPlayers(useWeaponCard.getPlayerAttackedThirdEffect()))) {
                model.getCurrentPlayer().getMessageToBeSent().get(0).setError("WRONG INPUT" + tmpPlayer);
                return;
            }
        }

        // check se sono divisibili per due!!
        // unirli in un unico if
        if(useWeaponCard.getSquareToAttackFirstEffect()!= null && useWeaponCard.getSquareToAttackFirstEffect().size()%2 == 0) {
            insertCoordinate(useWeaponCard.getSquareToAttackFirstEffect(), coordinates[0]);

            if (useWeaponCard.getSquareToAttackSecondEffect() != null && useWeaponCard.getSquareToAttackSecondEffect().size() % 2 == 0) {
                insertCoordinate(useWeaponCard.getSquareToAttackSecondEffect(), coordinates[1]);

                if (useWeaponCard.getSquareToAttackThirdEffect() != null && useWeaponCard.getSquareToAttackThirdEffect().size() % 2 == 0) {
                    insertCoordinate(useWeaponCard.getSquareToAttackThirdEffect(), coordinates[2]);
                } else {
                    return;
                }
            } else {
                return;
            }
        }
        else{
            return;
        }

        //input scorretto
        if(!checkPaymentCubes(useWeaponCard.getPaymentFirstEffect()) || !checkPaymentCubes(useWeaponCard.getPaymentSecondEffect()) || !checkPaymentCubes(useWeaponCard.getPaymentThirdEffect()) ){
            model.getCurrentPlayer().getMessageToBeSent().get(0).setError("WRONG INPUT " + cubeInserted);
            return;
        }
        //la persona non ha la powerUpCard
        if(!checkPowerUpCards(useWeaponCard.getPaymentFirstEffectPowerUp()) || !checkPowerUpCards(useWeaponCard.getPaymentSecondEffectPowerUp()) || !checkPowerUpCards(useWeaponCard.getPaymentThirdEffectPowerUp())){
            model.getCurrentPlayer().getMessageToBeSent().get(0).setError("WRONG INPUT " + tmpPowerUp);
            return;
        }

        //ok
        model.useWeaponCard(1, fromInterfaceToAbstractWeaponCard(useWeaponCard.getWeaponCard()), translateInterfacePlayerIntoPlayers(useWeaponCard.getPlayerAttackedFirstEffect()), coordinates[0], translateInputIntoCubes(useWeaponCard.getPaymentFirstEffect()), fromPowerUpInterfaceToPowerUp(useWeaponCard.getPaymentFirstEffectPowerUp()), false);
        if(useWeaponCard.getWeaponCard().getNumMaxEffect()<= 2) {
            model.useWeaponCard(2, fromInterfaceToAbstractWeaponCard(useWeaponCard.getWeaponCard()), translateInterfacePlayerIntoPlayers(useWeaponCard.getPlayerAttackedSecondEffect()), coordinates[1],translateInputIntoCubes(useWeaponCard.getPaymentSecondEffect()), fromPowerUpInterfaceToPowerUp(useWeaponCard.getPaymentSecondEffectPowerUp()), true);
        }
        else{
            if(useWeaponCard.getWeaponCard().getNumMaxEffect()== 2) {
                model.useWeaponCard(2, fromInterfaceToAbstractWeaponCard(useWeaponCard.getWeaponCard()), translateInterfacePlayerIntoPlayers(useWeaponCard.getPlayerAttackedSecondEffect()), coordinates[1], translateInputIntoCubes(useWeaponCard.getPaymentSecondEffect()), fromPowerUpInterfaceToPowerUp(useWeaponCard.getPaymentSecondEffectPowerUp()), false);
                model.useWeaponCard(3, fromInterfaceToAbstractWeaponCard(useWeaponCard.getWeaponCard()), translateInterfacePlayerIntoPlayers(useWeaponCard.getPlayerAttackedThirdEffect()), coordinates[2],translateInputIntoCubes(useWeaponCard.getPaymentThirdEffect()), fromPowerUpInterfaceToPowerUp(useWeaponCard.getPaymentThirdEffectPowerUp()), true);
            }
        }
    }

    @Override
    public void visitReload(ReloadMove reloadMove) {

            for(int i =0 ; i<reloadMove.getCubes().length; i++){
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




    }

    //verifico !!!
    @Override
    public void visitControllerRun(RunMove runMove){

        for(int i=0;i<runMove.getMovement().length;i++) {
            if (runMove.getMovement()[i][0] < 0 || runMove.getMovement()[i][0] > 2 || runMove.getMovement()[i][1] < 0 || runMove.getMovement()[i][1] > 3) {
                model.getCurrentPlayer().getMessageToBeSent().get(0).setError("The index you've inserted are wrong!" + runMove.getMovement()[i][0] + runMove.getMovement()[i][1]);
                model.sendCorrectActionMessage(model.getCurrentPlayer().getSingleMessageToBeSent());
                return;
            }
        }
        model.run(runMove.getMovement());
    }

    @Override
    public void visitControllerGrab(GrabMove grabMove) {

        if(grabMove.getCardSelection()!= 'A' || grabMove.getCardSelection()!= 'W' || grabMove.getCardSelection() != '0') {
            model.getCurrentPlayer().getMessageToBeSent().get(0).setError("WRONG INPUT" + grabMove.getCardSelection());
            model.sendCorrectActionMessage(model.getCurrentPlayer().getSingleMessageToBeSent());
            return;
        }
        else if(grabMove.getCardSelection()== 'W'){
                //model.grabWeaponCard(grabMove.getPositionWeaponCard(), grabMove.getPayment());
                //check payment
                //check index/player has that weapon card
                //check if is spawnPoint
                //model.getGameBoard().getGameArena().getSquare()
                //model.grabWeaponCard(model.getGameBoard().getGameArena().getWeaponCardsOnSquares(movement[0][0], movement[0][1]).get(runGrabMove.getPositionWeaponCard()),movement,runGrabMove.getPayment());
         }
         else if(grabMove.getCardSelection() == 'A'){
                model.grabAmmoCard();
            }
        }

    @Override
    public void visitControllerChooseAction(ChooseActionMove chooseActionMove) {
        if(chooseActionMove.getNumAction()<0 || chooseActionMove.getNumAction() > model.getCurrentPlayer().getMessageToBeSent().size()){

        }
    }

    @Override
    public void usePowerUpCard(UsePowerUpCard usePowerUpCard) {
        //check input corretto
    }

    @Override
    public void firstTurn() {
        model.chooseFirstPlayer();
    }

    @Override
    public void respawnPlayer() {

    }


}