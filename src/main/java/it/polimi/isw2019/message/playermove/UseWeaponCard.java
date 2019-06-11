package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.model.PlayerInterface;

import java.util.ArrayList;

public class UseWeaponCard extends PlayerMove {

    private ArrayList<Integer> effectUsed;
    private ArrayList<PlayerInterface> playerAttackedFirstEffect;
    private ArrayList<Integer> squareToAttackFirstEffect;
    private ArrayList<String> paymentFirstEffect;

    private ArrayList<PlayerInterface> playerAttackedSecondEffect;
    private ArrayList<Integer> squareToAttackSecondEffect;
    private ArrayList<String> paymentSecondEffect;


    private ArrayList<PlayerInterface> playerAttackedThirdEffect;
    private ArrayList<Integer> squareToAttackThirdEffect;
    private ArrayList<String> paymentThirdEffect;

    public UseWeaponCard(String idPlayer){
        super(idPlayer);
    }

    public ArrayList<Integer> getEffectUsed() {
        return effectUsed;
    }

    public ArrayList<PlayerInterface> getPlayerAttackedFirstEffect() {
        return playerAttackedFirstEffect;
    }

    public ArrayList<Integer> getSquareToAttackFirstEffect() {
        return squareToAttackFirstEffect;
    }

    public ArrayList<String> getPaymentFirstEffect() {
        return paymentFirstEffect;
    }

    public ArrayList<PlayerInterface> getPlayerAttackedSecondEffect() {
        return playerAttackedSecondEffect;
    }

    public ArrayList<Integer> getSquareToAttackSecondEffect() {
        return squareToAttackSecondEffect;
    }

    public ArrayList<String> getPaymentSecondEffect() {
        return paymentSecondEffect;
    }

    public ArrayList<PlayerInterface> getPlayerAttackedThirdEffect() {
        return playerAttackedThirdEffect;
    }

    public ArrayList<Integer> getSquareToAttackThirdEffect() {
        return squareToAttackThirdEffect;
    }

    public ArrayList<String> getPaymentThirdEffect() {
        return paymentThirdEffect;
    }

    public void initializeMessage(){
        effectUsed = new ArrayList<>();

        playerAttackedFirstEffect = null;
        paymentFirstEffect = null;
        squareToAttackFirstEffect = null;

        paymentSecondEffect = null;
        playerAttackedSecondEffect = null;
        squareToAttackSecondEffect = null;

        paymentThirdEffect = null;
        playerAttackedThirdEffect = null;
        squareToAttackThirdEffect = null;

    }


    public void setFirstEffect(ArrayList<PlayerInterface> playerAttackedFirstEffect,ArrayList<Integer> squareToAttackFirstEffect, ArrayList<String> paymentFirstEffect){
        effectUsed.add(1);
        this.paymentFirstEffect = paymentFirstEffect;
        this.playerAttackedFirstEffect = playerAttackedFirstEffect;
        this.squareToAttackFirstEffect = squareToAttackFirstEffect;
    }

    public void setSecondEffect(ArrayList<PlayerInterface> playerAttackedSecondEffect,ArrayList<Integer> squareToAttackSecondEffect, ArrayList<String> paymentSecondEffect){
        effectUsed.add(2);
        this.paymentSecondEffect = paymentSecondEffect;
        this.playerAttackedSecondEffect = playerAttackedSecondEffect;
        this.squareToAttackSecondEffect = squareToAttackSecondEffect;
    }

    public void setThirdEffect(ArrayList<PlayerInterface> playerAttackedThirdEffect,ArrayList<Integer> squareToAttackThirdEffect, ArrayList<String> paymentThirdEffect){
        effectUsed.add(3);
        this.paymentThirdEffect = paymentThirdEffect;
        this.playerAttackedThirdEffect = playerAttackedThirdEffect;
        this.squareToAttackThirdEffect = squareToAttackThirdEffect;
    }


    @Override
    public void accept(VisitorController visitorController) {
            visitorController.useWeaponCard(this);
    }
}
