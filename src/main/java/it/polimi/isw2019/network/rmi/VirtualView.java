package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.powerupcard.PowerUpCard;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.utilities.Observer;
import it.polimi.isw2019.view.VisitorView;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class VirtualView extends Observable<PlayerMove> implements Observer<MoveMessage>, VirtualViewVisitorInterface {

    private String nickname;
    private NetworkHandlerInterface networkHandler;

    VirtualView (String nickname, NetworkHandlerInterface networkHandler){
        this.nickname=nickname;
        this.networkHandler= networkHandler;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public void update(MoveMessage message) {
        message.accept(this);
    }

    public void createChooseActionMove (String player, int numAction){
        ChooseActionMove chooseActionMove= new ChooseActionMove(player,numAction);
        System.out.println("ricreo la player move di: "+ player);
        notifyObservers(chooseActionMove);


        ActionMessage actionMessage= new ActionMessage(player);
        sendActionView(actionMessage);
    }

    public void createChooseMap(String player, int index, int color){
        ChooseMapMove chooseMapMove= new ChooseMapMove(player, index, color);
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
        FirstMessage firstMessage = new FirstMessage(this, player, actionHero );
        notifyObservers(firstMessage);

        System.out.println("forzo rinvio");

        RegistrationPlayer registrationPlayer= new RegistrationPlayer(player,actionHero, null);
        sendOkRegistration(registrationPlayer);
    }

    public void createReload(String player){
        ReloadMove reloadMove = new ReloadMove(player);
        notifyObservers(reloadMove);
    }

    public void createPowerUpChoice(String player, int idPowerUp){
        PowerUpChoice powerUpChoice= new PowerUpChoice(player,idPowerUp);
        notifyObservers(powerUpChoice);
    }

    public void createUsePowerUpCard(String player, InterfacePowerUpCard powerUpCardInterface){
        UsePowerUpCard usePowerUpCard = new UsePowerUpCard(player,powerUpCardInterface);
        notifyObservers(usePowerUpCard);
    }

    public void createWeaponCardChoice(String player, int indexWeaponCard, String[] payment, ArrayList<InterfacePowerUpCard> powerUpCards, boolean grab){
        WeaponCardChoice weaponCardChoice = new WeaponCardChoice(player,indexWeaponCard,payment,powerUpCards,grab);
        notifyObservers(weaponCardChoice);
    }

    public void createUseWeaponCard (String player, WeaponCardInterface weaponCard){
        UseWeaponCard useWeaponCard = new UseWeaponCard(player,weaponCard);
        notifyObservers(useWeaponCard);
    }


    @Override
    public void sendSetupView(SetUpMessage setUpMessage) {
        try {
            networkHandler.createSetupView(setUpMessage.getNicknamePlayer(), setUpMessage.getColorAvailable());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendActionView(ActionMessage actionMessage) {
        System.out.println("invio la move message");

        try {
            networkHandler.createActionMessage(actionMessage.getNicknamePlayer());
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
            networkHandler.createGrab(grabMessage.getNicknamePlayer());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendReload(ReloadMessage reloadMessage) {

    }

    @Override
    public void sendUpdateView(UpdateMessage updateMessage) {

    }

    @Override
    public void sendOkRegistration(RegistrationPlayer registrationPlayer) {

        try {
            System.out.println("invio conferma");
            networkHandler.createOkRegistration(registrationPlayer.getNicknamePlayer(),registrationPlayer.getActionHero(),registrationPlayer.getColors());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendWaitForStart(EndRegistration endRegistration) {
        try {
            networkHandler.createWaitForStart(endRegistration.getNicknamePlayer());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendWeaponCardChoice(ChoiceWeaponCard choiceWeaponCard) {

    }

    @Override
    public void sendUseWeaponCard(UseWeaponCardMessage useWeaponCardMessage) {

    }

    @Override
    public void sendPowerUpChoice(ChoicePowerUpCard choicePowerUpCard) {

    }

    @Override
    public void sendUsePowerUpCard(UsePowerUpCardMessage usePowerUpCardMessage) {

    }

    @Override
    public void sendFirstPlayerChooseMap(FirstMessageFirstPlayer firstMessageFirstPlayer) {

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
