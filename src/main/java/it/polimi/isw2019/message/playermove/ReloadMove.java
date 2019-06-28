package it.polimi.isw2019.message.playermove;

import it.polimi.isw2019.controller.VisitorController;
import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;

import java.util.ArrayList;

public class ReloadMove  extends PlayerMove{

    private ArrayList<WeaponCardInterface> weaponCard;
    private InterfacePowerUpCard[][] powerUp;
    private int[][] cubes;

    public ReloadMove(String nickName){
        super(nickName);
        weaponCard = new ArrayList<>();
    }

    public void setReloadMove(ArrayList<WeaponCardInterface> weaponCard){
        this.weaponCard = weaponCard;
    }

    public void addWeaponCardToReload(WeaponCardInterface weaponCardInterface){
        weaponCard.add(weaponCardInterface);
    }

    public void setPayment(int[][] cubes){
        this.cubes = cubes;
    }

    public ArrayList<WeaponCardInterface> getWeaponCard() {
        return weaponCard;
    }

    public InterfacePowerUpCard[][] getPowerUp() {
        return powerUp;
    }

    public int[][] getCubes() {
        return cubes;
    }

    @Override
    public void accept(VisitorController visitorController) {
        visitorController.visitReload(this);
    }
}
