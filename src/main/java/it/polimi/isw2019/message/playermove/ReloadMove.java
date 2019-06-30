package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;

public class ReloadMove  extends PlayerMove{

    private int[] weaponCard;
    private int[][] payment;

    public ReloadMove(String nickName, int[] weaponCard, int[][] payment){
        super(nickName);
        this.weaponCard = weaponCard;
        this.payment = payment;
    }


    public int[] getWeaponCard() {
        return weaponCard;
    }

    public int[][] getPayment() {
        return payment;
    }

    @Override
    public void accept(VisitorController visitorController) {
        visitorController.visitReload(this);
    }

    @Override
    public void accept(NetworkHandlerVisitorInterface networkHandler) {
        networkHandler.sendReload(this);
    }
}
