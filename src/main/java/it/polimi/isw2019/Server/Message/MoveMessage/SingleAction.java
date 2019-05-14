package it.polimi.isw2019.Server.Message.MoveMessage;

import java.util.ArrayList;

public class SingleAction extends MoveMessage {
    private int[] numMovement;
    private boolean weaponCard;
    private boolean powerUpCard;
    private boolean reload;

    public SingleAction(String idMoveMessage, int numMovement, boolean weaponCard, boolean powerUpCard, boolean reload){
        super(idMoveMessage);
        this.numMovement = new int [numMovement];
        this.weaponCard = weaponCard;
        this.powerUpCard = powerUpCard;
        this.reload = reload;
    }

    public SingleAction getSingleAction(){
        return this;
    }

    public int[] getNumMovement(){
        return numMovement;
    }

    public boolean isWeaponCard(){
        return weaponCard;
    }

    public  boolean isPowerUpCard(){
        return weaponCard;
    }

    public boolean isReload(){
        return reload;
    }


}
