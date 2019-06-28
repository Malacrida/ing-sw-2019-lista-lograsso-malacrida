package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.model.PlayerInterface;
import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.network.rmi.NetworkHandlerVisitorInterface;

import java.util.ArrayList;

public class UseWeaponCard extends PlayerMove {

    private int[] effectUsed;

    private int weaponCard;

    private int[][] handleEffectCoordinates;

    private int[][] handleEffectPayment;

    private PlayerInterface[][] peopleToBeShoot;

    public UseWeaponCard(String idPlayer, int weaponCard){
        super(idPlayer);
        this.weaponCard = weaponCard;
    }

    public int getWeaponCard() {
        return weaponCard;
    }

    public int[] getEffectUsed() {
        return effectUsed;
    }

    public void setEffectUsed(int[] effectUsed) {
        this.effectUsed = effectUsed;
    }

    public void setWeaponCard(int weaponCard) {
        this.weaponCard = weaponCard;
    }

    public int[][] getHandleEffectCoordinates() {
        return handleEffectCoordinates;
    }

    public void setHandleEffectCoordinates(int[][] handleEffectCoordinates) {
        this.handleEffectCoordinates = handleEffectCoordinates;
    }

    public int[][] getHandleEffectPayment() {
        return handleEffectPayment;
    }

    public void setHandleEffectPayment(int[][] handleEffectPayment) {
        this.handleEffectPayment = handleEffectPayment;
    }

    public PlayerInterface[][] getPeopleToBeShoot() {
        return peopleToBeShoot;
    }

    public void setPeopleToBeShoot(PlayerInterface[][] peopleToBeShoot) {
        this.peopleToBeShoot = peopleToBeShoot;
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
