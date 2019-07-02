package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;
import it.polimi.isw2019.network.socket.MiniController;

public class GrabMove extends PlayerMove {

    private int[][] movement;
    private int positionWeaponCard;
    private int[] payment;

    public GrabMove(String player){
        super(player);
        positionWeaponCard = -1;
    }

    public void setMovement(int[][] movement){
        this.movement = movement;
    }


    public void setPositionWeaponCard( int positionWeaponCard){
        this.positionWeaponCard = positionWeaponCard;
    }

    public void setPayment(int[] payment){
        this.payment = payment;
    }

    public int getPositionWeaponCard(){
        return this.positionWeaponCard;
    }

    public int[][] getMovement(){
        return this.movement;
    }

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

    @Override
    public void accept(MiniController miniController) {

    }
}
