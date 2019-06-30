package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.controller.MainController;
import it.polimi.isw2019.message.movemessage.*;
import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.network.network_interface.ClientInterface;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.utilities.Observer;

import java.rmi.RemoteException;

public class VirtualView extends Observable<PlayerMove> implements Observer<MoveMessage>, VirtualViewVisitorInterface {

    private String nickname;
    private ClientInterface networkHandler;

    public VirtualView (String nickname, ClientInterface networkHandler){
        this.nickname=nickname;
        this.networkHandler = networkHandler;
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
        FirstMessage firstMessage = new FirstMessage(this, player, actionHero);
        notifyObservers(firstMessage);

        System.out.println("forzo rinvio");
    }

    public void createReload(String player){
       /* ReloadMove reloadMove = new ReloadMove(player);
        notifyObservers(reloadMove);*/
    }

    public void createPowerUpChoice(String player, int idPowerUp){
        PowerUpChoice powerUpChoice= new PowerUpChoice(player,idPowerUp);
        notifyObservers(powerUpChoice);
    }

    public void createUsePowerUpCard(String player/* InterfacePowerUpCard powerUpCardInterface*/){
       /* UsePowerUpCard usePowerUpCard = new UsePowerUpCard(player/*,powerUpCardInterface);
        notifyObservers(usePowerUpCard);*/
    }

    public void createWeaponCardChoice(String player, int indexWeaponCard, String[] payment,/* ArrayList<InterfacePowerUpCard> powerUpCards, */boolean grab){
        WeaponCardChoice weaponCardChoice = new WeaponCardChoice(player,indexWeaponCard,payment,/*powerUpCards,*/grab);
        notifyObservers(weaponCardChoice);
    }

    public void createUseWeaponCard (String player, int weaponCard){
        UseWeaponCard useWeaponCard = new UseWeaponCard(player,weaponCard);
        notifyObservers(useWeaponCard);
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
      /*  try {
            //networkHandler.createUpdateView(updateMessage.getNicknamePlayer(),updateMessage.getGameBoard(),updateMessage.getPlayers(),updateMessage.isNotifyAll());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }*/
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
    public void sendPowerUpChoice(ChoicePowerUpCard choicePowerUpCard) {

    }

    @Override
    public void sendUsePowerUpCard(UsePowerUpCardMessage usePowerUpCardMessage) {

    }

    @Override
    public void sendFirstPlayerChooseMap(FirstMessageFirstPlayer firstMessageFirstPlayer) {
        try {
            networkHandler.createFirstPlayerChooseMap(firstMessageFirstPlayer.getNicknamePlayer(),firstMessageFirstPlayer.getPossibleMaps(),firstMessageFirstPlayer.getColorAvailable());
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
