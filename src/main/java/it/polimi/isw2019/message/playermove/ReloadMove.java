package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;

import java.util.ArrayList;

public class ReloadMove  extends PlayerMove{

    private String[][] payment;


    public ReloadMove(String nickName, String[][] payment){
        super(nickName);
        this.payment = payment;
    }

    public String[][] getPayment() {
        return payment;
    }

    @Override
    public void accept(VisitorController visitorController) {
        visitorController.visitReload(this);
    }
}
