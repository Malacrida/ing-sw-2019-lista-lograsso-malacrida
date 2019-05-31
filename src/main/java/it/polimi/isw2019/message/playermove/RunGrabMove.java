package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;

public class RunGrabMove extends PlayerMove {

    private int[][] movement;
    private char cardSelection;
    private int positionWeaponCard;
    private char payment;

    public void setMovement(int[][] movement){

        this.movement = movement;
    }

    public void setCardSelection(char cardSelection){
        this.cardSelection= cardSelection;
    }

    public void setPositionWeaponCard( int positionWeaponCard){
        this.positionWeaponCard = positionWeaponCard;
    }

    public void setPayment(char payment){
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

    public char getPayment(){
        return this.payment;
    }

    @Override
    public void accept(VisitorController visitorController) {
        visitorController.visitControllerRunGrab(this);
    }
}
