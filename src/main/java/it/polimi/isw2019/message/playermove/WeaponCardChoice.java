package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;

import java.util.ArrayList;

public class WeaponCardChoice extends PlayerMove {

    private int indexWeaponCard;
    private String[] payment;
    private ArrayList<InterfacePowerUpCard> powerUpCards;
    private boolean grab;

    public WeaponCardChoice(String player, int indexWeaponCard, String[] payment, ArrayList<InterfacePowerUpCard> powerUpCards, boolean grab) {
        super(player);
        this.indexWeaponCard = indexWeaponCard;
        this.payment = payment;
        this.powerUpCards = powerUpCards;
        this.grab = grab;
    }


    public int getIndexWeaponCard() {
        return indexWeaponCard;
    }

    public String[] getPayment() {
        return payment;
    }

    public boolean isGrab() {
        return grab;
    }

    public ArrayList<InterfacePowerUpCard> getPowerUpCards() {
        return powerUpCards;
    }


    @Override
    public void accept(VisitorController visitorController) {
            visitorController.visitWeaponCardChoice(this);
    }
}
