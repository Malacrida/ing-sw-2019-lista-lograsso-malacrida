package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;



public class UseWeaponCardMessage extends MoveMessage{

    private int[] weaponCard;

    private int[][] playersToAttack;

    private int[] featuresAvailable;

    //all weapon cards, even if they're discharged
    private int[][] featuresForEffect;

    public UseWeaponCardMessage(String nicknamePlayer) {
        super(nicknamePlayer);
    }

    public UseWeaponCardMessage(String nicknamePlayer,int[] weaponCard, int[][] featuresForEffect, int[] featuresAvailable, int[][] playersToAttack, String error) {
        super(nicknamePlayer,error);
        this.weaponCard = weaponCard;
        this.featuresAvailable = featuresAvailable;
        this.playersToAttack = playersToAttack;
        this.featuresForEffect = featuresForEffect;
    }

    public int[] getWeaponCard() {
        return weaponCard;
    }

    public void setWeaponCard(int[] weaponCard) {
        this.weaponCard = weaponCard;
    }

    public int[][] getPlayersToAttack(){
        return playersToAttack;
    }

    public void setPlayersToAttack(int[][] playersToAttack) {
        this.playersToAttack = playersToAttack;
    }


    public int[] getFeaturesAvailable() {
        return featuresAvailable;
    }

    public void setFeaturesAvailable(int[] featuresAvailable) {
        this.featuresAvailable = featuresAvailable;
    }

    public int[][] getFeaturesForEffect() {
        return featuresForEffect;
    }

    public void setFeaturesForEffect(int[][] featuresForEffect) {
        this.featuresForEffect = featuresForEffect;
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
