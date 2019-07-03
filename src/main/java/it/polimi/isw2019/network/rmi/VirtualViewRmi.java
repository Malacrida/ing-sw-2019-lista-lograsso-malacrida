package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.controller.MainController;
import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.network.network_interface.ClientInterface;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.utilities.Observer;

import java.rmi.RemoteException;

public class VirtualViewRmi extends Observable<PlayerMove> implements Observer<MoveMessage>, VirtualViewVisitorInterface {

    private String nickname;
    private ClientInterface networkHandler;
    private int idPlayer;
    private boolean active;

    public VirtualViewRmi(String nickname, ClientInterface networkHandler){
        this.nickname=nickname;
        this.networkHandler = networkHandler;
        active=true;
    }

    public void registrationController(MainController controller){
        this.registerObserver(controller);
        //System.out.println("start view di :" +networkHandler);
        //startView();
    }

    public void startView (){
        try {
            networkHandler.startViewClient();
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public void update(MoveMessage message) {
        if (active){
            message.accept(this);
        }
    }

    public void createConnectionPlayer(String nickname, int connection){
        if (connection==0)
            active= false;
        else if (connection==1)
            active=true;
        ConnectionMove disconnectionMove = new ConnectionMove(nickname, connection);
        notifyObservers(disconnectionMove);
    }


    public void createChooseActionMove (String player, int numAction){
        ChooseActionMove chooseActionMove= new ChooseActionMove(player,numAction);
        System.out.println("ricreo la player move di: "+ player);
        notifyObservers(chooseActionMove);
    }

    public void createChooseMap(String player, int index, int color, int mod, int terminator){
        ChooseMapMove chooseMapMove= new ChooseMapMove(player, index, color, mod,terminator);
        notifyObservers(chooseMapMove);
    }

    public void createRun(String player,int[][] movement){
        RunMove runMove = new RunMove(player, movement);
        notifyObservers(runMove);
    }

    public void createGrab(String player){
        GrabMove grabMove= new GrabMove(player);
        notifyObservers(grabMove);
    }

    public void createRegisterPlayer( String player, String actionHero){
        System.out.println("ricreo una registrazione");
        FirstMessage firstMessage = new FirstMessage(this, player, actionHero);
        notifyObservers(firstMessage);

        System.out.println("forzo rinvio");
    }

    public void createReload(String player, int[] weaponCard, int[][] payment){
        ReloadMove reloadMove = new ReloadMove(player, weaponCard, payment);
        notifyObservers(reloadMove);
    }

    public void createPowerUpChoice(String player, int idPowerUp){
        PowerUpChoice powerUpChoice= new PowerUpChoice(player,idPowerUp);
        notifyObservers(powerUpChoice);
    }

    public void createUsePowerUpCard(String player){
        UsePowerUpCard usePowerUpCard = new UsePowerUpCard(player);
        notifyObservers(usePowerUpCard);
    }

    public void createWeaponCardChoice(String player, int indexWeaponCard, int[] payment){
        WeaponCardChoice weaponCardChoice = new WeaponCardChoice(player,indexWeaponCard,payment);
        notifyObservers(weaponCardChoice);
    }

    public void createUseWeaponCard (String player, int weaponCard){
        UseWeaponCard useWeaponCard = new UseWeaponCard(player,weaponCard);
        notifyObservers(useWeaponCard);
    }

    @Override
    public void sendActionView(ActionMessage actionMessage) {
        System.out.println("invio la move message Action a :" + actionMessage.getNicknamePlayer());

        try {
            networkHandler.createActionMessage(actionMessage.getNicknamePlayer(), actionMessage.getActionYouCanPerform(), actionMessage.getActionPlayerCanPerform(), actionMessage.getIntIdAction());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendRun(RunMessage runMessage) {
        try {
            networkHandler.createRun(runMessage.getNicknamePlayer(), runMessage.getError(), runMessage.getNumMovement());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendGrab(GrabMessage grabMessage) {
        try {
            networkHandler.createGrab(grabMessage.getNicknamePlayer(), grabMessage.getError(), grabMessage.getWeaponCardAvailable(), grabMessage.getFeaturesAvailable(), grabMessage.isGrabWeapon(), grabMessage.getAmmoTileDescription(), grabMessage.getPowerUpDescription());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendReload(ReloadMessage reloadMessage) {
        try {
            networkHandler.createReload(reloadMessage.getNicknamePlayer(),reloadMessage.getFeaturesAvailable(),reloadMessage.getWeaponYouCanReload(),reloadMessage.getError());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendUpdateView(UpdateMessage updateMessage) {
        try {
            networkHandler.createUpdateView(updateMessage.getNicknamePlayer(),updateMessage.getGameBoardDescription(),
                    updateMessage.getPlayersDescription(), updateMessage.getFeaturesOfPlayersAvailable(),
                    updateMessage.getWeaponCardDescription(), updateMessage.getPowerUpCardDescription(),updateMessage.isNotifyAll());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendWaitForStart(EndRegistration endRegistration) {
        try {
            System.out.println("ritorno endRegistration");
            networkHandler.createWaitForStart(endRegistration.getNicknamePlayer());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendUseWeaponCard(UseWeaponCardMessage useWeaponCardMessage) {

    }

    @Override
    public void sendPowerUpChoice(ChoiceCard choiceCard) {
        try {
            System.out.println("invio ChoicePower Up a:" + choiceCard.getNicknamePlayer());
            networkHandler.createChoiceCard(choiceCard.getNicknamePlayer(), choiceCard.getDescriptionPowerUp(), choiceCard.getIdPowerUp(), choiceCard.getError(),choiceCard.isDiscardOne(),choiceCard.isPowerUp());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendUsePowerUpCard(UsePowerUpCardMessage usePowerUpCardMessage) {

    }

    @Override
    public void sendFirstPlayerChooseMap(FirstMessageFirstPlayer firstMessageFirstPlayer) {
        try {
            networkHandler.createFirstPlayerChooseMap(firstMessageFirstPlayer.getNicknamePlayer(), firstMessageFirstPlayer.getIdPlayer(), firstMessageFirstPlayer.getPossibleMaps(), firstMessageFirstPlayer.getColorAvailable());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendFailRegistration(FailRegistration failRegistration) {
        try {
            networkHandler.createFailRegistration(failRegistration.getNicknamePlayer());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}