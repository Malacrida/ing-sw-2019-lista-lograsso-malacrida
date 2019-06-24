package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;

public class GrabMove extends PlayerMove {

    private int[][] movement;
    private char cardSelection;
    private int positionWeaponCard;
    private String payment;

    //arrayList String!

    public GrabMove(String player){
        super(player);
    }

    public void setMovement(int[][] movement){
        this.movement = movement;
    }

    public void setCardSelection(char cardSelection){
        this.cardSelection= cardSelection;
    }

    public void setPositionWeaponCard( int positionWeaponCard){
        this.positionWeaponCard = positionWeaponCard;
    }

    public void setPayment(String payment){
        this.payment = payment;
    }

    public int getPositionWeaponCard(){
        return this.positionWeaponCard;
    }

    public char getCardSelection(){
        return this.cardSelection;
    }

    public int[][] getMovement(){
        return this.movement;
    }

    public String getPayment(){
        return this.payment;
    }

    @Override
    public void accept(VisitorController visitorController) {
        visitorController.visitControllerGrab(this);
    }

    @Override
    public void accept(NetworkHandlerVisitorInterface networkHandler) {

    }
}
