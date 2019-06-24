package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.model.PlayerInterface;
import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;

import java.util.ArrayList;

public class UseWeaponCard extends PlayerMove {

    private ArrayList<Integer> effectUsed;

    private WeaponCardInterface weaponCard;



    private ArrayList<PlayerInterface> playerAttackedFirstEffect;
    private ArrayList<Integer> squareToAttackFirstEffect;
    private ArrayList<String> paymentFirstEffect;
    private ArrayList<InterfacePowerUpCard> paymentFirstEffectPowerUp;

    private ArrayList<PlayerInterface> playerAttackedSecondEffect;
    private ArrayList<Integer> squareToAttackSecondEffect;
    private ArrayList<String> paymentSecondEffect;
    private ArrayList<InterfacePowerUpCard> paymentSecondEffectPowerUp;

    private ArrayList<PlayerInterface> playerAttackedThirdEffect;
    private ArrayList<Integer> squareToAttackThirdEffect;
    private ArrayList<String> paymentThirdEffect;
    private ArrayList<InterfacePowerUpCard> paymentThirdEffectPowerUp;



    public UseWeaponCard(String idPlayer, WeaponCardInterface weaponCard){
        super(idPlayer);
        this.weaponCard = weaponCard;
    }

    public WeaponCardInterface getWeaponCard() {
        return weaponCard;
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

    public ArrayList<InterfacePowerUpCard> getPaymentFirstEffectPowerUp() {
        return paymentFirstEffectPowerUp;
    }

    public ArrayList<InterfacePowerUpCard> getPaymentSecondEffectPowerUp() {
        return paymentSecondEffectPowerUp;
    }

    public ArrayList<InterfacePowerUpCard> getPaymentThirdEffectPowerUp() {
        return paymentThirdEffectPowerUp;
    }

    public void initializeMessage(){
        effectUsed = new ArrayList<>();

        playerAttackedFirstEffect = null;
        paymentFirstEffect = null;
        squareToAttackFirstEffect = null;
        paymentFirstEffectPowerUp = null;

        paymentSecondEffect = null;
        playerAttackedSecondEffect = null;
        squareToAttackSecondEffect = null;
        paymentSecondEffectPowerUp = null;

        paymentThirdEffect = null;
        playerAttackedThirdEffect = null;
        squareToAttackThirdEffect = null;
        paymentThirdEffectPowerUp = null;

    }


    public void setFirstEffect(ArrayList<PlayerInterface> playerAttackedFirstEffect,ArrayList<Integer> squareToAttackFirstEffect, ArrayList<String> paymentFirstEffect,ArrayList<InterfacePowerUpCard> paymentFirstEffectPowerUp){
        effectUsed.add(1);
        this.paymentFirstEffect = paymentFirstEffect;
        this.playerAttackedFirstEffect = playerAttackedFirstEffect;
        this.squareToAttackFirstEffect = squareToAttackFirstEffect;
        this.paymentThirdEffectPowerUp = paymentFirstEffectPowerUp;
    }

    public void setSecondEffect(ArrayList<PlayerInterface> playerAttackedSecondEffect,ArrayList<Integer> squareToAttackSecondEffect, ArrayList<String> paymentSecondEffect,ArrayList<InterfacePowerUpCard> paymentSecondEffectPowerUp){
        effectUsed.add(2);
        this.paymentSecondEffect = paymentSecondEffect;
        this.playerAttackedSecondEffect = playerAttackedSecondEffect;
        this.squareToAttackSecondEffect = squareToAttackSecondEffect;
        this.paymentSecondEffectPowerUp = paymentSecondEffectPowerUp;
    }

    public void setThirdEffect(ArrayList<PlayerInterface> playerAttackedThirdEffect,ArrayList<Integer> squareToAttackThirdEffect, ArrayList<String> paymentThirdEffect, ArrayList<InterfacePowerUpCard> paymentThirdEffectPowerUp){
        effectUsed.add(3);
        this.paymentThirdEffect = paymentThirdEffect;
        this.playerAttackedThirdEffect = playerAttackedThirdEffect;
        this.squareToAttackThirdEffect = squareToAttackThirdEffect;
        this.paymentThirdEffectPowerUp = paymentThirdEffectPowerUp;
    }


    @Override
    public void accept(VisitorController visitorController) {
            visitorController.useWeaponCard(this);
    }

    @Override
    public void accept(NetworkHandlerVisitorInterface networkHandler) {
        networkHandler.sendUseWeaponCard(this);
    }
}
