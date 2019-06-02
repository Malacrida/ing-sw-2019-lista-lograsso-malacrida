package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;

import java.util.ArrayList;

public class ReloadMove  extends PlayerMove{
    private String nickName;
    private String[][] payment;
    private int[] weaponCardToReload;

    public int[] getWeaponCardToReload() {
        return weaponCardToReload;
    }

    public ReloadMove(String nickName){
        this.nickName = nickName;
        payment = new String[3][];
        weaponCardToReload = new int[3];
    }


    public String getNickName() {
        return nickName;
    }

    public String[][] getPayment() {
        return payment;
    }

    public void setPayment(String[][] payment) {
        this.payment = payment;
    }

    @Override
    public void accept(VisitorController visitorController) {
        visitorController.visitReload(this);
    }
}
