package it.polimi.isw2019.Server.Message.MoveMessage;

import it.polimi.isw2019.Client.View.VisitorView;

import java.util.ArrayList;

public class SingleAction extends MoveMessage {

    private int numMovement;
    private boolean weaponCard;
    private boolean powerUpCard;
    private boolean reload;
    private boolean mustPay;
    private boolean canPay;
    private int idAction;

    public SingleAction(int idAction,String idMoveMessage, int numMovement, boolean weaponCard, boolean powerUpCard, boolean reload, boolean canPay, boolean mustPay){
        super(idMoveMessage);
        this.idAction = idAction;
        this.numMovement = numMovement;
        this.weaponCard = weaponCard;
        this.powerUpCard = powerUpCard;
        this.reload = reload;
        this.canPay = canPay;
        this.mustPay = mustPay;
    }


    public SingleAction getSingleAction(){
        return this;
    }

    public int getNumMovement(){
        return numMovement;
    }

    public boolean isWeaponCard(){
        return weaponCard;
    }

    public  boolean isPowerUpCard(){
        return powerUpCard;
    }

    public boolean isReload(){
        return reload;
    }

    public boolean isMustPay(){
        return  mustPay;
    }

    public  boolean isCanPay(){
        return  canPay;
    }

    public int getIdAction(){
        return this.idAction;
    }

    @Override
    public void visit(VisitorView visitorView) {

    }
}
