package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.ammotile.InterfaceAmmoTile;
import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class GrabMessage extends MoveMessage {

    private ArrayList<WeaponCardInterface> weaponCardAvailable;
    private ArrayList<InterfacePowerUpCard> yourPowerUpCard;
    private InterfacePowerUpCard powerUpCard;
    private InterfaceAmmoTile ammoTile;
    private int[] featuresAvailable;
    private boolean grabWeapon;

    public GrabMessage(String nicknamePlayer) {
        super(nicknamePlayer);
    }

    public GrabMessage(String nicknamePlayer, int[] featuresAvailable) {
        super(nicknamePlayer);
        this.featuresAvailable = featuresAvailable;
    }

    public GrabMessage(String nicknamePlayer, String error) {
        super(nicknamePlayer, error);
    }

    public ArrayList<WeaponCardInterface> getWeaponCardAvailable() {
        return weaponCardAvailable;
    }

    public void setWeaponCardAvailable(ArrayList<WeaponCardInterface> weaponCardAvailable) {
        this.weaponCardAvailable = weaponCardAvailable;
    }

    public InterfaceAmmoTile getAmmoTile() {
        return ammoTile;
    }

    public void setAmmoTile(InterfaceAmmoTile ammoTile) {
        this.ammoTile = ammoTile;
    }

    public boolean isGrabWeapon() {
        return grabWeapon;
    }

    public void setGrabWeapon(Boolean grabWeapon){
        this.grabWeapon = grabWeapon;
    }


    public ArrayList<InterfacePowerUpCard> getYourPowerUpCard() {
        return yourPowerUpCard;
    }

    public void setYourPowerUpCard(ArrayList<InterfacePowerUpCard> yourPowerUpCard) {
        this.yourPowerUpCard = yourPowerUpCard;
    }

    public InterfacePowerUpCard getPowerUpCard() {
        return powerUpCard;
    }

    public void setPowerUpCard(InterfacePowerUpCard powerUpCard) {
        this.powerUpCard = powerUpCard;
    }

    public int[] getFeaturesAvailable() {
        return featuresAvailable;
    }

    public void setFeaturesAvailable(int[] featuresAvailable) {
        this.featuresAvailable = featuresAvailable;
    }

    @Override
    public void accept(VisitorView visitorview) {
                visitorview.visitGrab(this);
    }

    @Override
    public void accept(VirtualViewVisitorInterface virtualView) {
        virtualView.sendGrab(this);
    }


}
