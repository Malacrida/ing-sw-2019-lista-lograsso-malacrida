package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;

/**
 * player move when player decides to use reload action
 */

public class ReloadMove  extends PlayerMove{

    private int[] weaponCard;
    private int[][] payment;

    public ReloadMove(String nickName, int[] weaponCard, int[][] payment){
        super(nickName);
        this.weaponCard = weaponCard;
        this.payment = payment;
    }

    /**
     * getter of weapon card that he wants reload
     * @return weapon card
     */
    public int[] getWeaponCard() {
        return weaponCard;
    }

    /**
     * getter of payment to reload weapon card
     * @return payment
     */

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
