package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.PlayerInterface;
import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;


public class UseWeaponCardMessage extends MoveMessage{

    private ArrayList<WeaponCardInterface> weaponCard;

    private ArrayList<PlayerInterface> playersToAttack;

    private ArrayList<InterfacePowerUpCard> powerUpCards;

    private int[] featuresAvailable;

    public UseWeaponCardMessage(String nicknamePlayer) {
        super(nicknamePlayer);
    }

    public UseWeaponCardMessage(String nicknamePlayer, int[] featuresAvailable) {
        super(nicknamePlayer);
        this.featuresAvailable = featuresAvailable;
    }

    public ArrayList<WeaponCardInterface> getWeaponCard() {
        return weaponCard;
    }

    public void setWeaponCard(ArrayList<WeaponCardInterface> weaponCard) {
        this.weaponCard = weaponCard;
    }

    public ArrayList<PlayerInterface> getPlayersToAttack(){
        return playersToAttack;
    }

    public void setPlayersToAttack(ArrayList<PlayerInterface> playersToAttack) {
        this.playersToAttack = playersToAttack;
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

    @Override
    public void accept(VisitorView visitorview) {
            visitorview.useWeaponCard(this);
    }

    @Override
    public void accept(VirtualViewVisitorInterface virtualView) {
        virtualView.sendUseWeaponCard(this);
    }


}
