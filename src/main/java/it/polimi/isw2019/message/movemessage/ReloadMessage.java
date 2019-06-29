package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class ReloadMessage extends MoveMessage{

    //armi scariche
    private  int[] weaponYouCanReload;
    private int[] featuresAvailable;

    public ReloadMessage(String nicknamePlayer){
        super(nicknamePlayer);
    }

    public ReloadMessage(String nicknamePlayer, int[] featuresAvailable, int[] weaponYouCanReload) {
        super(nicknamePlayer);
        this.featuresAvailable = featuresAvailable;
        this.weaponYouCanReload = weaponYouCanReload;
    }


    public int[] getFeaturesAvailable() {
        return featuresAvailable;
    }

    public void setFeaturesAvailable(int[] featuresAvailable) {
        this.featuresAvailable = featuresAvailable;
    }

    public int[] getWeaponYouCanReload() {
        return weaponYouCanReload;
    }

    public void setWeaponYouCanReload(int[] weaponYouCanReload) {
        this.weaponYouCanReload = weaponYouCanReload;
    }

    @Override
    public void accept(VisitorView visitorView) {
        visitorView.visitReload(this);
    }

    @Override
    public void accept(VirtualViewVisitorInterface virtualView) {
        virtualView.sendReload(this);
    }
}
