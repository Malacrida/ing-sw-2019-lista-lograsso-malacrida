package it.polimi.isw2019.message.movemessage;

import it.polimi.isw2019.model.powerupcard.InterfacePowerUpCard;
import it.polimi.isw2019.model.weaponcard.WeaponCardInterface;
import it.polimi.isw2019.network.rmi.VirtualViewVisitorInterface;
import it.polimi.isw2019.view.VisitorView;

import java.util.ArrayList;

public class ChoiceWeaponCard extends MoveMessage {

    private ArrayList<WeaponCardInterface> weaponCards;
    private ArrayList<InterfacePowerUpCard> powerUpCards;

    private boolean grab;
    public ChoiceWeaponCard(String nicknamePlayer, String error, ArrayList<WeaponCardInterface> weaponCards, ArrayList<InterfacePowerUpCard> powerUpCards,boolean grab) {
        super(nicknamePlayer, error);
        this.weaponCards = weaponCards;
        this.powerUpCards = powerUpCards;
        this.grab = grab;
    }

    public ArrayList<WeaponCardInterface> getWeaponCards() {
        return weaponCards;
    }

    public ArrayList<InterfacePowerUpCard> getPowerUpCards(){
        return powerUpCards;
    }

    public boolean isGrab() {
        return grab;
    }

    @Override
    public void accept(VisitorView visitorview) {
            visitorview.weaponCardChoice(this);
    }

    @Override
    public void accept(VirtualViewVisitorInterface virtualView) {
        virtualView.sendWeaponCardChoice(this);
    }

}
