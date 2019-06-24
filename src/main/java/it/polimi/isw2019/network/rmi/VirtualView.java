package it.polimi.isw2019.network.rmi;

import it.polimi.isw2019.message.movemessage.ActionMessage;
import it.polimi.isw2019.message.movemessage.MoveMessage;
import it.polimi.isw2019.message.playermove.*;
import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.powerupcard.PowerUpCard;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.utilities.Observable;
import it.polimi.isw2019.utilities.Observer;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class VirtualView extends Observable<PlayerMove> implements Observer<MoveMessage> {

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

    }

    public void createChooseActionMove (String player, int numAction){
        ChooseActionMove chooseActionMove= new ChooseActionMove(player,numAction);
        System.out.println("ricreo la player move di: "+ player);
        notifyObservers(chooseActionMove);


        sendActionMessage();
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

    public void createRegisterPlayer(FirstMessage firstMessage){

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


    public void sendActionMessage (){
        System.out.println("invio la move message");
        ActionMessage actionMessage= new ActionMessage (nickname);
        try {
            networkHandler.createActionMessage(actionMessage.getNicknamePlayer());
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
