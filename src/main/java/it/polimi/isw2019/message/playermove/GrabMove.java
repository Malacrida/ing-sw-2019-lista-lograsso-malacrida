package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;

import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;

/**
 * player move of grab action
 */

public class GrabMove extends PlayerMove {

    private int[][] movement;
    private int positionWeaponCard;
    private int[] payment;

    public GrabMove(String player){
        super(player);
        positionWeaponCard = -1;
    }

    public GrabMove (String player, int positionWeaponCard, int[] payment){
        super(player);
        this.positionWeaponCard= positionWeaponCard;
        this.payment= payment;
    }

    /**
     * setter of movement if player wants to move in another square
     * @param movement coordinates
     */

    public void setMovement(int[][] movement){
        this.movement = movement;
    }

    /**
     * setter of position weapon card that player wants to grab
     * @param positionWeaponCard index of weapon card
     */

    public void setPositionWeaponCard( int positionWeaponCard){
        this.positionWeaponCard = positionWeaponCard;
    }

    /**
     * setter of payment weapon card that player wants to grab
     * @param payment cubes
     */

    public void setPayment(int[] payment){
        this.payment = payment;
    }

    /**
     * getter of position weapon card (0, 1, 2)
     * @return position weapon card
     */

    public int getPositionWeaponCard(){
        return this.positionWeaponCard;
    }

    /**
     * getter of movement
     * @return movement
     */

    public int[][] getMovement(){
        return this.movement;
    }

    /**
     * getter of payment
     * @return payment
     */

    public int[] getPayment(){
        return this.payment;
    }

    @Override
    public void accept(VisitorController visitorController) {
        visitorController.visitControllerGrab(this);
    }

    @Override
    public void accept(NetworkHandlerVisitorInterface networkHandler) {
        networkHandler.sendGrab(this);
    }


}
