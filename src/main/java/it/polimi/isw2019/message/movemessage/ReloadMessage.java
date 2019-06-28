package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class ReloadMessage extends MoveMessage{

    //armi scariche
    private ArrayList<WeaponCardInterface> weaponCardInterfaces;
    private ArrayList<InterfacePowerUpCard> powerUpCards;
    private int[] featuresAvailable;

    public ReloadMessage(String nicknamePlayer, ArrayList<WeaponCardInterface> weaponCardInterfaces){
        super(nicknamePlayer);
        this.weaponCardInterfaces = weaponCardInterfaces;
    }

    public ReloadMessage(String nicknamePlayer){
        super(nicknamePlayer);
    }

    public ReloadMessage(String nicknamePlayer, int[] featuresAvailable) {
        super(nicknamePlayer);
        this.featuresAvailable = featuresAvailable;
    }

    public ArrayList<WeaponCardInterface> getWeaponCardInterfaces() {
        return weaponCardInterfaces;
    }

    public ArrayList<InterfacePowerUpCard> getPowerUpCards() {
        return powerUpCards;
    }

    public void setPowerUpCards(ArrayList<InterfacePowerUpCard> powerUpCards) {
        this.powerUpCards = powerUpCards;
    }

    public int[] getFeaturesAvailable() {
        return featuresAvailable;
    }

    public void setFeaturesAvailable(int[] featuresAvailable) {
        this.featuresAvailable = featuresAvailable;
    }

    public void setWeaponCardInterfaces(ArrayList<WeaponCardInterface> weaponCardInterfaces) {
        this.weaponCardInterfaces = weaponCardInterfaces;
    }

    @Override
    public void accept(VisitorView visitorView) {
        visitorView.visitReload(this);
    }
}
