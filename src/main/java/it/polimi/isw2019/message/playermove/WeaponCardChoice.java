package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;
import it.polimi.isw2019.network.socket.MiniController;


public class WeaponCardChoice extends PlayerMove {

    private int indexWeaponCard;
    private int[] payment;


    public WeaponCardChoice(String player, int indexWeaponCard, int[] payment) {
        super(player);
        this.indexWeaponCard = indexWeaponCard;
        this.payment = payment;
    }


    public int getIndexWeaponCard() {
        return indexWeaponCard;
    }

    public int[] getPayment() {
        return payment;
    }


    @Override
    public void accept(VisitorController visitorController) {
            visitorController.visitWeaponCardChoice(this);
    }

    @Override
    public void accept(NetworkHandlerVisitorInterface networkHandler) {
        networkHandler.sendWeaponCardChoice(this);
    }

    @Override
    public void accept(MiniController miniController) {

    }
}
